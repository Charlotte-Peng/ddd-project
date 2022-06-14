package org.pj.metaverse.user.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import de.huxhorn.sulky.ulid.ULID;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.common.constant.SystemConstant;
import org.pj.metaverse.common.constant.SystemDictionaryConstant;
import org.pj.metaverse.common.constant.redis.SystemRedisConstant;
import org.pj.metaverse.common.constant.redis.UserRedisConstant;
import org.pj.metaverse.common.enums.ResponseEnum;
import org.pj.metaverse.common.exception.ServerException;
import org.pj.metaverse.common.utils.AsyncUtils;
import org.pj.metaverse.event.common.annotation.EventLogAnnotation;
import org.pj.metaverse.event.common.enums.EventEnum;
import org.pj.metaverse.user.entity.LoginEntity;
import org.pj.metaverse.user.entity.PermissionEntity;
import org.pj.metaverse.user.entity.UserEntity;
import org.pj.metaverse.user.entity.repvo.LoginRepVO;
import org.pj.metaverse.user.entity.reqvo.RegisteredAccountReqVO;
import org.pj.metaverse.user.entity.reqvo.UserLoginReqVO;
import org.pj.metaverse.user.entity.reqvo.WebRegisteredAccountReqVO;
import org.pj.metaverse.user.entity.reqvo.WebUserLoginReqVO;
import org.pj.metaverse.user.feign.SystemFeign;
import org.pj.metaverse.user.mapper.LoginMapper;
import org.pj.metaverse.user.mapper.UserMapper;
import org.pj.metaverse.user.repository.redis.LoginRepositoryRedis;
import org.pj.metaverse.user.repository.redis.UserRepositoryRedis;
import org.pj.metaverse.user.service.ILoginService;
import org.pj.metaverse.user.service.IPermissionService;
import org.pj.metaverse.user.service.IUserRoleService;
import org.pj.metaverse.common.utils.IdWorker;
import org.pj.metaverse.common.utils.NvlUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-08 14:05:12
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl extends ServiceImpl<LoginMapper, LoginEntity> implements ILoginService {
    private final SystemFeign systemFeign;
    private final IPermissionService permissionService;
    private final StringRedisTemplate stringRedisTemplate;
    private final UserMapper userMapper;
    private final IUserRoleService userRoleService;
    private final LoginRepositoryRedis loginRepositoryRedis;
    private final UserRepositoryRedis userRepositoryRedis;
    private final ULID ulid;
    private final AsyncUtils asyncUtils;

    @Override
    @EventLogAnnotation(EVENT_ENUM = EventEnum.USER_LOGIN)
    public LoginRepVO accountPasswordLoginV1(UserLoginReqVO vo) {
        return this.checkPassword(vo.getUserName(), vo.getPassword(),"APP");
    }

    private LoginRepVO checkPassword (String userName,String passwordTemp, String device){
        // 根据系统信息获取对应的盐
        String saltJson = systemFeign.getDictionary(SystemDictionaryConstant.USER_PASSWORD_SALT_KEY);
        Map map = JSONObject.parseObject(saltJson, Map.class);
        String salt = (String) map.get(SystemDictionaryConstant.USER_PASSWORD_SALT_VALUE_KEY);
        String password = SaSecureUtil.md5BySalt(passwordTemp, salt);
        // 查询对应的用户信息是否正确
        LambdaQueryWrapper<LoginEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LoginEntity::getLoginName,userName)
                .or()
                .eq(LoginEntity::getLoginEmail,userName)
                .or()
                .eq(LoginEntity::getLoginPhone,userName)
                .eq(LoginEntity::getLoginPassword,password);
        LoginEntity loginEntity = this.getOne(wrapper);
        if (NvlUtils.isNull(loginEntity)){
            throw new ServerException(ResponseEnum.WRONG_ACCOUNT_OR_PASSWORD);
        }
        // 将当前的用户信息放到saToken中
        StpUtil.login(loginEntity.getUserId(),device);
        // 查询用户的基本信息
        UserEntity userEntity = userMapper.selectById(loginEntity.getUserId());
        // 将当前用户的角色及权限返回给前端，同时返回token
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        LoginRepVO data = new LoginRepVO();
        data.setUserId(loginEntity.getUserId());
        data.setTokenName(tokenInfo.tokenName);
        data.setTokenValue(tokenInfo.tokenValue);
        data.setUserNickName(userEntity.getUserNickName());
        data.setUserAvatar(userEntity.getUserAvatar());
        // 查询该用户的权限集合
        List<PermissionEntity> permissionList = permissionService.getPermissionList();
        data.setPermissionList(permissionList);
        // 利用redis记录在线人数
        stringRedisTemplate.opsForValue().setBit(UserRedisConstant.USER_ONLINE_NUM,userEntity.getUserNo(),true);
        return data;
    }

    @Override
    public LoginRepVO webAccountPasswordLoginV1(WebUserLoginReqVO vo, String sessionId) {
        // 验证验证码
        String code = stringRedisTemplate.opsForValue().get(SystemRedisConstant.CAPTCHA_KEY + sessionId);
        if (NvlUtils.isBlank(code)){
            // 不存在
            throw new ServerException(ResponseEnum.INCORRECT_VERIFICATION_CODE);
        }
        if (NvlUtils.isBlank(vo.getCode()) || !vo.getCode().equals(code)){
            // 验证码不对
            throw new ServerException(ResponseEnum.INCORRECT_VERIFICATION_CODE);
        }
        return this.checkPassword(vo.getUserName(), vo.getPassword(),"WEB");
    }

    @Override
    public LoginRepVO registeredAccount(RegisteredAccountReqVO vo) {
        // 根据不同类型查询是否有该用户注册了
//        LambdaQueryWrapper<LoginEntity> wrapper = new LambdaQueryWrapper<>();
        LoginEntity loginEntity = null;
        if (vo.getType().equals(RegisteredAccountReqVO.TYPE_EMAIL)){
            loginEntity = loginRepositoryRedis.findByLoginEmail(vo.getLoginEmail());
//            wrapper.eq(LoginEntity::getLoginEmail,vo.getLoginEmail());
        }
        if (vo.getType().equals(RegisteredAccountReqVO.TYPE_PHONE)){
            loginEntity = loginRepositoryRedis.findByLoginPhone(vo.getLoginPhone());
//            wrapper.eq(LoginEntity::getLoginPhone,vo.getLoginPhone());
        }
        if (vo.getType().equals(RegisteredAccountReqVO.TYPE_NAME)){
            loginEntity = loginRepositoryRedis.findByLoginName(vo.getLoginName());
//            wrapper.eq(LoginEntity::getLoginName,vo.getLoginName());
        }
//        wrapper.last("limit 1");
//        long aLong = this.count(wrapper);
        if (loginEntity != null){
            throw new ServerException(ResponseEnum.THIS_ACCOUNT_HAS_BEEN_REGISTERED);
        }
        // 注册到redis中
        LoginEntity saveLogin = new LoginEntity();
        BeanUtils.copyProperties(vo,saveLogin);

        // 用户密码加密
//        saveLogin.setLoginPassword(SaSecureUtil.md5BySalt(vo.getLoginPassword(),""));

        // 异步写入到数据库
        asyncUtils.exec(() -> {
            boolean save = this.save(saveLogin);
            if (!save){
                log.error("注册失败");
            }
        });


        return this.writeToDatabase(saveLogin,SystemDictionaryConstant.USER_ROLE_APP_DEFAULT_TYPE,null);
    }

    private LoginRepVO writeToDatabase(LoginEntity saveLogin,Integer type, Integer roleId){
        String userId = ulid.nextULID();
        // 根据系统信息获取对应的盐
        String saltJson = systemFeign.getDictionary(SystemDictionaryConstant.USER_PASSWORD_SALT_KEY);
        Map map = JSONObject.parseObject(saltJson, Map.class);
        String salt = (String) map.get(SystemDictionaryConstant.USER_PASSWORD_SALT_VALUE_KEY);
        String password = SaSecureUtil.md5BySalt(saveLogin.getLoginPassword(), salt);
        saveLogin.setUserId(userId);
        saveLogin.setLoginPassword(password);
        saveLogin.setCreateTime(LocalDateTime.now());
        loginRepositoryRedis.save(saveLogin);
        // 异步写入到数据库
        asyncUtils.exec(() -> {
            boolean save = this.save(saveLogin);
            if (!save){
                log.error("注册失败");
                throw new ServerException(ResponseEnum.SYSTEM_ERROR);
            }
        });
        // 写入用户的信息
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        // 从系统随机库中获取头像及名称
        Set<String> avatarSet = systemFeign.randomlyObtainTheAvatarOfTheSpecifiedType(SystemConstant.BaseAvatar.TYPE_USER, 1);
        Set<String> nameSet = systemFeign.randomlyObtainTheNameOfTheSpecifiedType(SystemConstant.BaseName.TYPE_USER, 1);
        for (String avatarUrl : avatarSet) {
            userEntity.setUserAvatar(avatarUrl);
        }
        for (String name : nameSet) {
            userEntity.setUserNickName(name);
        }
        // 写入用户信息
        userRepositoryRedis.save(userEntity);
        asyncUtils.exec(() -> {
            int insert = userMapper.insert(userEntity);
            if (insert != 1){
                log.error("注册失败");
                throw new ServerException(ResponseEnum.SYSTEM_ERROR);
            }
        });
        // 绑定角色
        userRoleService.roleBinding(userId,type,roleId);
        // 将当前的用户信息放到saToken中
        StpUtil.login(userId);
        // 将当前用户的角色及权限返回给前端，同时返回token
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        LoginRepVO data = new LoginRepVO();
        data.setUserId(userId);
        data.setTokenName(tokenInfo.tokenName);
        data.setTokenValue(tokenInfo.tokenValue);
        // 查询该用户的权限集合
        List<PermissionEntity> permissionList = permissionService.getPermissionList();
        data.setPermissionList(permissionList);
        return data;
    }

    @Override
    public LoginRepVO webRegisteredAccount(WebRegisteredAccountReqVO vo) {
        LambdaQueryWrapper<LoginEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LoginEntity::getLoginName,vo.getLoginName());
        wrapper.last("limit 1");
        long aLong = this.count(wrapper);
        if (aLong != 0){
            throw new ServerException(ResponseEnum.THIS_ACCOUNT_HAS_BEEN_REGISTERED);
        }
        // 写入到数据库
        LoginEntity saveLogin = new LoginEntity();
        BeanUtils.copyProperties(vo,saveLogin);
        // 查询后台默认分配的角色
        return this.writeToDatabase(saveLogin,SystemDictionaryConstant.USER_ROLE_WEB_DEFAULT_TYPE,null);
    }

    @Override
    public void logOut(String device) {
        String loginId = StpUtil.getLoginIdAsString();
        // 根据userId查询userNo
        UserEntity userEntity = this.userMapper.selectById(loginId);
        // 调用saToken退出
        StpUtil.logout(loginId,device);
        // redis清除该用户的信息
        stringRedisTemplate.opsForValue().setBit(UserRedisConstant.USER_ONLINE_NUM,userEntity.getUserNo(),false);
    }
}

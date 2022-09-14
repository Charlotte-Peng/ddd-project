package org.pj.metaverse.service.impl;

import com.ejlchina.searcher.BeanSearcher;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.constant.DictionaryConstant;
import org.pj.metaverse.entity.TDictionaryEntity;
import org.pj.metaverse.entity.TUserEntity;
import org.pj.metaverse.entity.TUserRoleEntity;
import org.pj.metaverse.entity.TUserRoleInfoEntity;
import org.pj.metaverse.entity.reqvo.UserLoginReqVO;
import org.pj.metaverse.entity.reqvo.UserRegisterReqVO;
import org.pj.metaverse.exception.ServerException;
import org.pj.metaverse.mapper.TUserMapper;
import org.pj.metaverse.mapper.TUserRoleInfoMapper;
import org.pj.metaverse.mapper.TUserRoleMapper;
import org.pj.metaverse.service.CommonService;
import org.pj.metaverse.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pj.metaverse.utils.NvlUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Service
@RequiredArgsConstructor
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUserEntity> implements ITUserService {

    private final BeanSearcher beanSearcher;
    private final TUserRoleMapper userRoleMapper;
    private final TUserRoleInfoMapper userRoleInfoMapper;
    private final CommonService commonService;


    @Override
    public TUserEntity register(UserRegisterReqVO vo) {
        // 根据账号查询是否存在该用户
        TUserEntity user = this.lambdaQuery()
                .eq(TUserEntity::getUserName, vo.getUserName())
                .last("limit 1")
                .one();
        if(NvlUtils.isNotNull(user)){
            throw new ServerException("账号已存在");
        }
        // 写入用户数据
        String userId = this.initUserInfo(vo);
        // 写入初始化角色数据
        this.initUserInfo(userId,vo.getUserNickName());
        return this.getById(userId);
    }

    /**
     * 初始化用户信息
     * @author pengjie
     * @date 2022/8/29 9:25
     * @param vo 注册信息
     * @return java.lang.String
     */
    private String initUserInfo(UserRegisterReqVO vo){
        TUserEntity userEntity = new TUserEntity();
        BeanUtils.copyProperties(vo, userEntity);
        if (NvlUtils.isNull(userEntity.getUserNickName())) {
            // 随机获取名称集合
        }
        if (NvlUtils.isNull(userEntity.getUserAvatar())) {
            // 随机获取头像集合
        }
        if (NvlUtils.isNull(vo.getSex())) {
            userEntity.setSex(2);
        }
        boolean save = this.save(userEntity);
        if (!save) {
            throw new ServerException("注册失败");
        }
        return userEntity.getUserId();
    }
    /**
     * 初始化用户角色数据
     * @author pengjie
     * @date 2022/8/25 15:48
     * @param userId 用户id
     * @param userNickName 用户昵称
     * @param userAvatar 用户头像
     */
    private void initUserInfo(String userId, String userNickName) {
        // 初始化用户角色
        Integer userRoleInfoId = this.initUserRoleInfo(userNickName);
        // 初始化角色跟用户的关系
        this.initUserRole(userId,userRoleInfoId);
    }

    /**
     * 初始化用户角色
     * @author pengjie
     * @date 2022/8/25 15:48
     * @param name 角色名称
     * @return java.lang.String 角色详情id
     */
    private Integer initUserRoleInfo(String name) {
        // 获取字典表中的初始化角色id
        TDictionaryEntity defaultRoleId = commonService.getConstant(DictionaryConstant.Role.DEFAULT_KEY);
        // 后去字典表中的初始化角色类型
        TDictionaryEntity defaultRoleType = commonService.getConstant(DictionaryConstant.Role.DEFAULT_TYPE);
        // 初始话角色用户
        TUserRoleInfoEntity userRoleInfo = new TUserRoleInfoEntity();
        userRoleInfo.setName(name);
        userRoleInfo.setRoleId(Integer.valueOf(defaultRoleId.getValue()));
        userRoleInfo.setType(Integer.valueOf(defaultRoleType.getValue()));
        userRoleInfoMapper.insert(userRoleInfo);
        return userRoleInfo.getId();
    }
    /**
     * 初始化用户角色关联表
     * @author pengjie
     * @date 2022/8/25 15:48
     * @param userId 用户id
     */
    private void initUserRole(String userId,Integer userRoleId) {
        // 初始话角色用户
        // 初始化用户角色关联表
        TUserRoleEntity tUserRoleEntity = new TUserRoleEntity();
        tUserRoleEntity.setUserId(userId);
        tUserRoleEntity.setUserRoleId(userRoleId);
        int insert = userRoleMapper.insert(tUserRoleEntity);
        if (insert != 1) {
            throw new ServerException("初始化用户角色关联表失败");
        }
    }


    @Override
    public TUserEntity login(UserLoginReqVO vo) {
        // 根据账号查询是否存在该用户
        TUserEntity user = this.lambdaQuery()
                .eq(TUserEntity::getUserName, vo.getUserName())
                .eq(TUserEntity::getUserPassword, vo.getUserPassword())
                .one();
        if(NvlUtils.isNull(user)){
            throw new ServerException("账号不存在或密码错误");
        }
        return user;
    }
}

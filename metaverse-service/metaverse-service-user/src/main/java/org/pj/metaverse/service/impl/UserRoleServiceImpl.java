package org.pj.metaverse.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.constant.SystemDictionaryConstant;
import org.pj.metaverse.enums.ResponseEnum;
import org.pj.metaverse.exception.ServerException;
import org.pj.metaverse.feign.SystemFeign;
import org.pj.metaverse.mapper.UserRoleMapper;
import org.pj.metaverse.repository.redis.UserRoleRepositoryRedis;
import org.pj.metaverse.utils.AsyncUtils;
import org.pj.metaverse.entity.UserRoleEntity;
import org.pj.metaverse.service.IUserRoleService;
import org.pj.metaverse.utils.NvlUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements IUserRoleService {
    private final SystemFeign systemFeign;
    private final UserRoleRepositoryRedis userRoleRepositoryRedis;
    private final AsyncUtils asyncUtils;

    @Override
    public void roleBinding(String userId, Integer type, Integer roleId) {
        if (NvlUtils.isNull(roleId)){
            // 给用户绑定默认角色
            String roleJson = systemFeign.getDictionary(SystemDictionaryConstant.USER_ROLE_KEY);
            Map roleMap = JSONObject.parseObject(roleJson, Map.class);
            assert roleMap != null;
            switch (type) {
                case 1 -> roleId = (Integer) roleMap.get(SystemDictionaryConstant.USER_ROLE_WEB_DEFAULT_VALUE_KEY);
                case 2 -> roleId = (Integer) roleMap.get(SystemDictionaryConstant.USER_ROLE_APP_DEFAULT_VALUE_KEY);
                default -> throw new ServerException(ResponseEnum.THE_PARAMETER_IS_WRONG);
            }
        }
        UserRoleEntity saveEntity = new UserRoleEntity();
        saveEntity.setUserId(userId);
        saveEntity.setRoleId(roleId);
        userRoleRepositoryRedis.save(saveEntity);
        asyncUtils.exec(() -> {
            boolean save = this.save(saveEntity);
            if (!save){
                log.error("保存用户角色失败");
                throw new ServerException(ResponseEnum.SYSTEM_ERROR);
            }
        });
    }
}

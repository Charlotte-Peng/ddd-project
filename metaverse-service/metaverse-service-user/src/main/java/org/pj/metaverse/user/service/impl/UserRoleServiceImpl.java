package org.pj.metaverse.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.common.constant.SystemDictionaryConstant;
import org.pj.metaverse.common.enums.ResponseEnum;
import org.pj.metaverse.common.exception.ServerException;
import org.pj.metaverse.user.entity.UserRoleEntity;
import org.pj.metaverse.user.feign.SystemFeign;
import org.pj.metaverse.user.mapper.UserRoleMapper;
import org.pj.metaverse.user.service.IUserRoleService;
import org.pj.metaverse.common.utils.NvlUtils;
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
        boolean save = this.save(saveEntity);
        if (!save){
            throw new ServerException(ResponseEnum.SYSTEM_ERROR);
        }
    }
}

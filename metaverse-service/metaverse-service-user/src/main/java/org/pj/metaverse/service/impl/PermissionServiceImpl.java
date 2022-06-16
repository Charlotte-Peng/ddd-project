package org.pj.metaverse.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.exception.ServerException;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.enums.ResponseEnum;
import org.pj.metaverse.entity.PermissionEntity;
import org.pj.metaverse.entity.RolePermissionEntity;
import org.pj.metaverse.entity.UserRoleEntity;
import org.pj.metaverse.mapper.PermissionMapper;
import org.pj.metaverse.repository.redis.PermissionRepositoryRedis;
import org.pj.metaverse.repository.redis.RolePermissionRepositoryRedis;
import org.pj.metaverse.repository.redis.UserRoleRepositoryRedis;
import org.pj.metaverse.service.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限接口表 服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements IPermissionService {
//    private final PermissionMapper permissionMapper;
    private final PermissionRepositoryRedis permissionRepositoryRedis;
    private final UserRoleRepositoryRedis userRoleRepositoryRedis;
    private final RolePermissionRepositoryRedis rolePermissionRepositoryRedis;

    @Override
    public List<PermissionEntity> getPermissionList() {
        // 获取当前人登录id
        String loginId = StpUtil.getLoginIdAsString();
        List<UserRoleEntity> list = userRoleRepositoryRedis.findUserRoleEntitiesByUserId(loginId);

        List<Integer> roleIds = list.stream().map(UserRoleEntity::getRoleId).toList();
        List<RolePermissionEntity> rolePermissionEntitiesByRoleIdIn = rolePermissionRepositoryRedis.findRolePermissionEntitiesByRoleIdIn(roleIds);
        Set<Integer> permissionIdsSet = new HashSet<>(rolePermissionEntitiesByRoleIdIn.stream().map(RolePermissionEntity::getPermissionId).toList());
        List<PermissionEntity> permissionEntities = permissionRepositoryRedis.findPermissionEntitiesByIdIn(permissionIdsSet);
        if (permissionEntities.isEmpty()) {
            throw new ServerException(ResponseEnum.DATA_DOES_NOT_EXIST);
        }
//        permissionMapper.getPermissionList(loginId)
        return permissionEntities;
    }

    @Override
    public DataResult<Void> addPermission(PermissionEntity entity) {
        // 获取当前人登录id
        String loginId = StpUtil.getLoginIdAsString();
        entity.setCreateBy(loginId);
        boolean save = this.save(entity);
        if (!save){
            throw new ServerException(ResponseEnum.SYSTEM_ERROR);
        }
        return DataResult.success();
    }

    @Override
    public DataResult<Void> addBatchPermission(List<PermissionEntity> entity) {
        // 获取当前人登录id
        String loginId = StpUtil.getLoginIdAsString();
        for (PermissionEntity permissionEntity : entity) {
            permissionEntity.setCreateBy(loginId);
        }
        boolean save = this.saveBatch(entity);
        if (!save){
            throw new ServerException(ResponseEnum.SYSTEM_ERROR);
        }
        return DataResult.success();
    }
}

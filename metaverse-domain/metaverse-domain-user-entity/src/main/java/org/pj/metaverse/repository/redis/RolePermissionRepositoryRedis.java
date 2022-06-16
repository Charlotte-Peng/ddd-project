package org.pj.metaverse.repository.redis;

import org.pj.metaverse.entity.RolePermissionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pengjie
 * @date 15:22 2022/6/13
 **/
@Repository
public interface RolePermissionRepositoryRedis extends CrudRepository<RolePermissionEntity, Integer> {
    List<RolePermissionEntity> findRolePermissionEntitiesByRoleIdIn(List<Integer> roleIds);
}

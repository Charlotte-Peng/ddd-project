package org.pj.metaverse.user.repository.redis;

import org.pj.metaverse.user.entity.LoginEntity;
import org.pj.metaverse.user.entity.PermissionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author pengjie
 * @date 15:22 2022/6/13
 **/
@Repository
public interface PermissionRepositoryRedis extends CrudRepository<PermissionEntity, Integer> {
    List<PermissionEntity> findPermissionEntitiesByIdIn(Set<Integer> ids);

}

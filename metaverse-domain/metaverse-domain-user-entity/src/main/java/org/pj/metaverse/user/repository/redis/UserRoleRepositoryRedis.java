package org.pj.metaverse.user.repository.redis;

import org.pj.metaverse.user.entity.UserEntity;
import org.pj.metaverse.user.entity.UserRoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pengjie
 * @date 15:22 2022/6/13
 **/
@Repository
public interface UserRoleRepositoryRedis extends CrudRepository<UserRoleEntity, Integer> {
    List<UserRoleEntity> findUserRoleEntitiesByUserId(String userId);
}

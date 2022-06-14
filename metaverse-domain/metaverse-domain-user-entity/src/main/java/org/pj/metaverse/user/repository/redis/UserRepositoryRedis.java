package org.pj.metaverse.user.repository.redis;

import org.pj.metaverse.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author pengjie
 * @date 15:22 2022/6/13
 **/
@Repository
public interface UserRepositoryRedis extends CrudRepository<UserEntity, Integer> {
}

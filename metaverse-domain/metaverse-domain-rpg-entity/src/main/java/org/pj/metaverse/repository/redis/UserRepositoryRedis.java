package org.pj.metaverse.repository.redis;

import org.pj.metaverse.entity.TestEntity;
import org.pj.metaverse.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author pengjie
 * @date 15:22 2022/6/13
 **/
@Repository
public interface UserRepositoryRedis extends CrudRepository<UserEntity, String> {
    UserEntity findUserEntityByUserName(String userName);
    UserEntity findUserEntityByUserId(String userId);
    UserEntity findUserEntityByUserNameAndUserPassword(String userName, String userPassword);
}

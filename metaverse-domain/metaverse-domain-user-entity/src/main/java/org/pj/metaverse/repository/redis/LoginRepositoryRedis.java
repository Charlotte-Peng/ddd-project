package org.pj.metaverse.repository.redis;

import org.pj.metaverse.entity.LoginEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author pengjie
 * @date 15:22 2022/6/13
 **/
@Repository
public interface LoginRepositoryRedis extends CrudRepository<LoginEntity, Integer> {
    LoginEntity findByLoginEmail(String loginEmail);
    LoginEntity findByLoginPhone(String loginPhone);
    LoginEntity findByLoginName(String loginName);

}

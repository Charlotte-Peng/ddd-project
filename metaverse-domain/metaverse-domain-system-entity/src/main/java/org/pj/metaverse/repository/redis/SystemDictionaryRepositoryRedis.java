package org.pj.metaverse.repository.redis;

import org.pj.metaverse.entity.SystemDictionaryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author pengjie
 * @date 15:22 2022/6/13
 **/
@Repository
public interface SystemDictionaryRepositoryRedis extends CrudRepository<SystemDictionaryEntity, String> {
    SystemDictionaryEntity findByKeyAndEnable(String key, Integer enable);

}

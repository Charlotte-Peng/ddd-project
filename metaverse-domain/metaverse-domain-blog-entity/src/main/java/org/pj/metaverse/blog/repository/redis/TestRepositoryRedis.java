package org.pj.metaverse.blog.repository.redis;

import org.pj.metaverse.blog.entity.TestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author pengjie
 * @date 15:22 2022/6/13
 **/
@Repository
public interface TestRepositoryRedis extends CrudRepository<TestEntity, String> {
}

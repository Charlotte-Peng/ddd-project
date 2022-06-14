package org.pj.metaverse.blog.controller;

import de.huxhorn.sulky.ulid.ULID;
import io.swagger.annotations.Api;
import org.pj.metaverse.blog.entity.TestEntity;
import org.pj.metaverse.blog.repository.redis.TestRepositoryRedis;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author pengjie
 * @date 16:26 2022/6/1
 **/
@RestController
@Api(tags = "测试")
public class TestController {
    @Resource
    private TestRepositoryRedis testRepositoryRedis;
    @Resource
    private ULID ulid;

    @GetMapping("/testSet")
    public String testSet(){
        TestEntity testEntity = new TestEntity();
        testEntity.setId(ulid.nextULID());
        testEntity.setName("pengjie");
        testEntity.setAge("18");
        testEntity.setSex("男");
        TestEntity save = testRepositoryRedis.save(testEntity);
        return save.toString();
    }
    @GetMapping("/testGet")
    public String testGet(){
        Optional<TestEntity> byId = testRepositoryRedis.findById("97453b71-2cd1-4185-9340-7679acb31350");
        if (byId.isPresent()) {
            return byId.get().toString();
        }else {
            return "没有找到";
        }
    }
}

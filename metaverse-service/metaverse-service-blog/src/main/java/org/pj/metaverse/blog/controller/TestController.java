package org.pj.metaverse.blog.controller;

import org.pj.metaverse.blog.entity.TestEntity;
import org.pj.metaverse.cache.utils.RedisJsonTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author pengjie
 * @date 16:26 2022/6/1
 **/
@RestController
public class TestController {
    @Resource
    private RedisJsonTemplate redisJsonTemplate;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/testSet")
    public String testSet(){
        TestEntity entity = new TestEntity();
        entity.setId(1);
        entity.setName("pengjie");
        redisJsonTemplate.setJson(entity,"testKey:1");
        String s = (String) redisTemplate.opsForValue().get("DTS:MERCHANT_PERMISSION_LIST");
        return s;
    }
    @GetMapping("/testGet")
    public String testGet(){

        TestEntity testKey = redisJsonTemplate.getJson("testKey", redisJsonTemplate.defaultPath, TestEntity.class);
        System.out.println(testKey);
        int i = 0;
        i++;
        String string = redisJsonTemplate.incrBy("testKey:1","$.id",1);
        System.out.println(string);
        return string;
    }
}

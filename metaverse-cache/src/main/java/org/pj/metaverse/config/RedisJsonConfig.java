//package org.pj.metaverse.cache.config;
//
//import org.pj.metaverse.cache.utils.RedisJsonTemplate;
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.ClusterServersConfig;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.RedisSerializer;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
///*
// * @author pengjie
// * @date 15:14 2022/6/1
// */
//
//@Configuration
//public class RedisJsonConfig {
//
//    @Resource
//    private RedisConfigProperties redisConfigProperties;
//
//
//    @Bean
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        return new StringRedisTemplate(redisConnectionFactory);
//    }
//
//@Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setKeySerializer(RedisSerializer.string());
//        redisTemplate.setValueSerializer(RedisSerializer.string());
//        redisTemplate.setHashKeySerializer(RedisSerializer.string());
//        redisTemplate.setHashValueSerializer(RedisSerializer.string());
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//
//    @ConditionalOnProperty(prefix = "spring.redis", name = "database")
//    @Bean
//    public RedisJsonTemplate redisJsonTemplate(RedisConnectionFactory redisConnectionFactory) {
//        Config config = new Config();
//        config.useSingleServer()
//                .setAddress("redis://" + redisConfigProperties.getHost() + ":" + redisConfigProperties.getPort())
//                .setPassword(redisConfigProperties.getPassword());
//        return new RedisJsonTemplate(config,redisConnectionFactory);
//    }
//
//    @ConditionalOnProperty(prefix = "spring.redis.cluster", name = "nodes")
//    @Bean
//    public RedisJsonTemplate redisJsonClusterTemplate(RedisConnectionFactory redisConnectionFactory) {
//        //redisson版本是3.5，集群的ip前面要加上“redis://”，不然会报错，3.2版本可不加
//        List<String> clusterNodes = new ArrayList<>();
//        for (int i = 0; i < redisConfigProperties.getCluster().getNodes().size(); i++) {
//            clusterNodes.add("redis://" + redisConfigProperties.getCluster().getNodes().get(i));
//        }
//        Config config = new Config();
//        // 添加集群地址
//        ClusterServersConfig clusterServersConfig = config.useClusterServers().addNodeAddress(clusterNodes.toArray(new String[clusterNodes.size()]));
//        // 设置密码
//        clusterServersConfig.setPassword(redisConfigProperties.getPassword());
//        return new RedisJsonTemplate(config,redisConnectionFactory);
//    }
//}

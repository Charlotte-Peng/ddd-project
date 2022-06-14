//package org.pj.metaverse.cache.config;
//
//import com.redis.om.spring.RedisModulesConfiguration;
//import com.redis.om.spring.client.RedisModulesClient;
//import io.lettuce.core.cluster.RedisClusterClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.*;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import redis.clients.jedis.JedisPoolConfig;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author pengjie
// * @date 16:58 2022/6/11
// **/
//@Configuration
//public class RedisAutoConfig {
//    @Resource
//    RedisConfigProperties redisConfigProperties;
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPool,
//                                                         RedisClusterConfiguration jedisConfig) {
//        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(jedisConfig);
//        connectionFactory.setPoolConfig(jedisPool);
//        return connectionFactory;
//    }
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        return new JedisConnectionFactory(jedisClusterConfig());
//    }
//    @Value("${spring.redis.jedis.pool.max-idle}")
//    private int maxIdle;
//    @Value("${spring.redis.jedis.pool.min-idle}")
//    private int minIdle;
//    @Value("${spring.redis.jedis.pool.max-active}")
//    private int maxActive;
//    @Value("${spring.redis.jedis.pool.max-wait}")
//    private long maxWait;
//    @Bean
//    public JedisPoolConfig jedisPool() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMaxWaitMillis(maxWait);
//        jedisPoolConfig.setMaxTotal(maxActive);
//        jedisPoolConfig.setMinIdle(minIdle);
//        return jedisPoolConfig;
//    }
//
//    /*@ConditionalOnProperty(prefix = "spring.redis", name = "database")
//    @Bean
//    public RedisStandaloneConfiguration jedisStandaloneConfig() {
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//        config.setHostName(redisConfigProperties.getHost());
//        config.setPort(redisConfigProperties.getPort());
//        config.setDatabase(0);
//        config.setPassword(redisConfigProperties.getPassword());
//        return config;
//    }*/
////    @ConditionalOnProperty(prefix = "spring.redis.cluster", name = "nodes")
//    @Bean
//    public RedisClusterConfiguration jedisClusterConfig() {
//        RedisClusterConfiguration config = new RedisClusterConfiguration();
//        config.setPassword(redisConfigProperties.getPassword());
//        List<RedisNode> list = new ArrayList<>();
//        for (String node : redisConfigProperties.getCluster().getNodes()) {
//            RedisNode redisNode = new RedisNode(node.split(":")[0], Integer.parseInt(node.split(":")[1]));
//            list.add(redisNode);
//        }
//        config.setClusterNodes(list);
//        return config;
//    }
//}

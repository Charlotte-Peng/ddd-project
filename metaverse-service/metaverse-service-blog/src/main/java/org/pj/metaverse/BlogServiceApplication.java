package org.pj.metaverse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author admin
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan("org.pj.metaverse.mapper")
@ComponentScan(value = "org.pj.metaverse.*")
@EnableRedisRepositories(basePackages = "org.pj.metaverse.repository.redis")
@EnableJpaRepositories(basePackages = "org.pj.metaverse.repository.jpa")
public class BlogServiceApplication {
    public static void main(String[] args) {
        System.setProperty("log4j.skipJansi", "false");
        SpringApplication.run(BlogServiceApplication.class, args);
    }
}

package org.pj.metaverse.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author pengjie
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan("org.pj.metaverse.system.mapper")
@ComponentScan("org.pj.metaverse.*")
public class SystemServiceApplication {
    public static void main(String[] args) {
        System.setProperty("log4j.skipJansi", "false");
        SpringApplication.run(SystemServiceApplication.class, args);
    }
}

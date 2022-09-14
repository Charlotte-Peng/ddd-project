package org.pj.metaverse;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.pj.metaverse.init.SystemInit;
import org.pj.metaverse.init.WebsocketInitialization;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.Resource;

/**
 * @author pengjie
 * @date 10:12 2022/8/23
 **/
@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                DruidDataSourceAutoConfigure.class}
)
@EnableFeignClients(basePackages = "org.pj.metaverse.feign")
public class WebSocketApplication {
    public static void main(String[] args) {
        SystemInit.init();
        SpringApplication.run(WebSocketApplication.class, args);
    }
}

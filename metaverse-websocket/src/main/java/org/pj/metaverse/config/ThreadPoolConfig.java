package org.pj.metaverse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ProjectName: dts
 * @Package: com.qiguliuxing.dts.core.config
 * @ClassName: ThreadPoolConfig
 * @Author: yc
 * @Description: 线程池配置
 * @Date: 2021/9/2 17:06
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig implements AsyncConfigurer {

    @Autowired
    private ThreadPoolConfigProperties configProperties;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(configProperties.getCorePoolSize());
        //最大线程数
        executor.setMaxPoolSize(configProperties.getMaxPoolSize());
        //队列容量
        executor.setQueueCapacity(configProperties.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(configProperties.getKeepAliveSeconds());
        //线程名字前缀
        executor.setThreadNamePrefix("async-thread-");

        /*
          当poolSize已达到maxPoolSize，如何处理新任务（是拒绝还是交由其它线程处理）
          CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }


}

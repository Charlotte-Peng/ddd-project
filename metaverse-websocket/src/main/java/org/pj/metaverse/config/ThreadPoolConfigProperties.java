package org.pj.metaverse.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: dts
 * @Package: com.qiguliuxing.dts.core.config
 * @ClassName: TaskThreadPoolConfig
 * @Author: yc
 * @Description: 线程池配置属性
 * @Date: 2021/9/2 17:03
 */
@Data
@Component
@ConfigurationProperties(prefix = "task.pool")
public class ThreadPoolConfigProperties {

    /**
     * 核心线程数
     */
    private int corePoolSize;

    /**
     * 最大线程数
     */
    private int maxPoolSize;

    /**
     * 线程空闲时间
     */
    private int keepAliveSeconds;

    /**
     * 任务队列容量（阻塞队列）
     */
    private int queueCapacity;

}

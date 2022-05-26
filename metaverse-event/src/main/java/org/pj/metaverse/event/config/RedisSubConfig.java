package org.pj.metaverse.event.config;

import org.pj.metaverse.event.listener.RedisListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * redis 发布订阅配置
 * @author pengjie
 * @date 14:58 2022/5/25
 **/
@Configuration
public class RedisSubConfig {
    @Bean
    MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter(new RedisListener());
    }

    @Bean
    RedisMessageListenerContainer redisContainer(RedisConnectionFactory factory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        // 支持多个topic
        container.addMessageListener(listenerAdapter(), new ChannelTopic(RedisListener.REDIS_LISTENER_KEY));
        return container;
    }
}

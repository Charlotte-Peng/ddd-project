package org.pj.metaverse.event.common.utils;

import com.alibaba.fastjson.JSON;
import org.pj.metaverse.event.common.entity.BaseLogEventRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author pengjie
 * @date 16:48 2022/5/25
 **/
@Component
public class RedisPublisher {
    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    public static String REDIS_PUBLISHER_KEY = "meta-verse:event:topic";
    private final ChannelTopic topic = new ChannelTopic(REDIS_PUBLISHER_KEY);

    public void sendMsg(BaseLogEventRecord msg) {
        String messageJson = JSON.toJSONString(msg);
        stringRedisTemplate.convertAndSend(topic.getTopic(),messageJson);
    }
}

package org.pj.metaverse.utlis;

import lombok.RequiredArgsConstructor;
import org.pj.metaverse.common.DefaultSettingConstant;
import org.pj.metaverse.common.RedisConstant;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * websocket redis工具类
 * @author pengjie
 * @date 10:44 2022/8/23
 **/
@Component
@RequiredArgsConstructor
public class RedisWebsocketUtils {
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 根据类型储存websocket的ip地址
     * @author pengjie
     * @date 2022/8/23 10:49
     * @param ipAddress ip地址
     * @param port 端口
     * @param type 类型
     */
    public void saveWebsocketInfo(String ipAddress, String port, String type) {
        // 查询redis中的websocket信息，如果没有，则添加，如果有，则不添加
        String ipAddressAndPort = ipAddress + ":" + port;
        if (Boolean.FALSE.equals(stringRedisTemplate.opsForHash().hasKey(RedisConstant.WEBSOCKET_IP_ADDRESS_BIND_ID, ipAddressAndPort))) {
            // 不存在，则添加
            stringRedisTemplate.opsForHash().put(RedisConstant.WEBSOCKET_IP_ADDRESS_BIND_ID,ipAddressAndPort, getAutoIncrementId().toString());
            stringRedisTemplate.opsForSet().add(type,ipAddressAndPort);
        }
    }

    /**
     * 获取websocket的自增id
     * @author pengjie
     * @date 2022/8/23 11:19
     * @return java.lang.Long
     */
    private Long getAutoIncrementId() {
        RedisAtomicLong counter = new RedisAtomicLong(RedisConstant.WEBSOCKET_AUTO_INCREMENT_KEY, Objects.requireNonNull(stringRedisTemplate.getConnectionFactory()));
        return counter.incrementAndGet();
    }

    /**
     * 移除websocket的ip地址
     * @author pengjie
     * @date 2022/8/23 11:20
     * @param ipAddress ip地址
     * @param port 端口
     * @param type 类型
     */
    public void removeWebsocketInfo(String ipAddress, String port, String type) {
        String ipAddressAndPort = ipAddress + ":" + port;
        stringRedisTemplate.opsForHash().delete(RedisConstant.WEBSOCKET_IP_ADDRESS_BIND_ID, ipAddressAndPort);
        stringRedisTemplate.opsForSet().remove(type, ipAddressAndPort);
        stringRedisTemplate.opsForSet().add(RedisConstant.WEBSOCKET_RPG_TYPE_KEY, port);
    }

    /**
     * 根据类型获取websocket周期任务的时间，单位为秒
     * @author pengjie
     * @date 2022/8/23 11:47
     * @param type 类型
     * @return java.lang.Integer
     */
    public Integer getWebsocketTaskCycleTime(String type) {
        String second = (String) stringRedisTemplate.opsForHash().get(RedisConstant.WEBSOCKET_TASK_CYCLE_TIME_KEY, type);
        if (second == null) {
            // 设置默认时间
            stringRedisTemplate.opsForHash().put(RedisConstant.WEBSOCKET_TASK_CYCLE_TIME_KEY, type, DefaultSettingConstant.WEBSOCKET_TASK_CYCLE_TIME.toString());
            // 返回默认时间
            return DefaultSettingConstant.WEBSOCKET_TASK_CYCLE_TIME;
        }
        return Integer.parseInt(second);
    }

    /**
     * redis随机弹出一个端口号
     */
    public String getRandomPort() {
        return stringRedisTemplate.opsForSet().pop(RedisConstant.WEBSOCKET_PORT_KEY);
    }

}

package org.pj.metaverse.utlis;

import org.pj.metaverse.common.RedisConstant;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * ip地址工具类
 * @author pengjie
 * @date 11:05 2022/8/23
 **/
public class IpAdderUtils {
    /**
     * 查询本机ip地址
     *
     * @author pengjie
     * @date 2022/8/23 11:05
     */
    public static String getLocalIpAddress() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }
}

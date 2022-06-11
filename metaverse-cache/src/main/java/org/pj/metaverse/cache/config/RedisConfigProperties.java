package org.pj.metaverse.cache.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ProjectName: beiming
 * @Package: com.app.test.config
 * @ClassName: RedisConfigProperties
 * @Author: yc
 * @Description:
 * @Date: 2021/5/19 21:16
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigProperties {

    private String host;
    private Integer port;
    private String password;
    private cluster cluster;


    public static class cluster {
        private List<String> nodes;

        public List<String> getNodes() {
            return nodes;
        }

        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RedisConfigProperties.cluster getCluster() {
        return cluster;
    }

    public void setCluster(RedisConfigProperties.cluster cluster) {
        this.cluster = cluster;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}

package org.pj.metaverse.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author pengjie
 * @date 16:28 2022/5/25
 **/
@Component
public class ServerUtils {

    @Value("${spring.application.name}")
    public String serverName;

    public String serverIp;

    public Integer serverPort;

    private void getServerIp() throws UnknownHostException {
        this.serverIp = InetAddress.getLocalHost().getHostAddress();
    }
}

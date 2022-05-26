package org.pj.metaverse.event.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

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

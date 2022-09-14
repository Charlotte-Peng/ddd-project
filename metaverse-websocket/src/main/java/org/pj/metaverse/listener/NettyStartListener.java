package org.pj.metaverse.listener;

import org.pj.metaverse.SocketApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author pengjie
 * @date 16:10 2022/8/23
 **/
@Component
public class NettyStartListener implements ApplicationRunner {

    @Autowired
    private SocketApplication socketApplication;


    @Override
    public void run(ApplicationArguments args) {
        socketApplication.start();
    }
}

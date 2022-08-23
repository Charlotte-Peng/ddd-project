package org.pj.metaverse;

import lombok.extern.slf4j.Slf4j;
import org.pj.metaverse.init.WebsocketInitialization;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author pengjie
 * @date 13:43 2022/8/23
 **/
@Slf4j
@Component
public class SocketApplication {
    @Resource
    private WebsocketInitialization websocketInitialization;

    @PostConstruct
    public void start() {
        try {
            log.info(Thread.currentThread().getName() + ":websocket启动中......");
            websocketInitialization.init();
            log.info(Thread.currentThread().getName() + ":websocket启动成功！！！");
        } catch (Exception e) {
            log.error("websocket发生错误：",e);
        }
    }
}

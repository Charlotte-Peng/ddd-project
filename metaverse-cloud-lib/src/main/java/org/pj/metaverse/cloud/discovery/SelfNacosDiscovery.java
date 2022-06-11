package org.pj.metaverse.cloud.discovery;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author pengjie
 * @date 10:01 2022/6/2
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class SelfNacosDiscovery {
    private final AbstractAutoServiceRegistration abstractAutoServiceRegistration;

    @EventListener(ContextClosedEvent.class)
    public void saveSysLog() {
        log.info("nacos补偿注销流程，开始");
        try {
            abstractAutoServiceRegistration.destroy();
        }catch (Exception e){
            log.error("nacos补偿注销流程，异常",e);
        }
        log.info("nacos补偿注销流程，结束");
    }
}

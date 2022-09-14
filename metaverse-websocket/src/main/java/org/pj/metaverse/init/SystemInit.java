package org.pj.metaverse.init;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pengjie
 * @date 10:09 2022/7/14
 **/
@Slf4j
public class SystemInit {
    public static void init() {
        System.out.println("SystemInit.init");
        log.debug("SystemInit.init");
        System.out.println("写入log4j2彩色设置");
        System.setProperty("log4j.skipJansi", "false");
        log.debug("写入log4j2彩色设置");
        System.out.println("写入log4j全局异步设置");
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        log.debug("写入log4j全局异步设置");
    }
}

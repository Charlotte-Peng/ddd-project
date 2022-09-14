package org.pj.metaverse.listener;

import org.pj.metaverse.service.CommonService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author pengjie
 * @date 16:10 2022/8/23
 **/
@Component
public class StartListener implements ApplicationRunner {

    @Resource
    CommonService commonService;


    @Override
    public void run(ApplicationArguments args) {
        // 初始化常量
        commonService.initConstants();
    }
}

package org.pj.metaverse.workflow.config;

import org.pj.metaverse.workflow.service.CamundaCommonService;
import org.springframework.context.annotation.Bean;

/**
 * Camunda通用工具 - 配置类
 *
 * @author charlotte
 * @date 2022-05-27 12:00
 */
public class CamundaCommonConfig {

    @Bean
    public CamundaCommonService camundaCommonService() {
        return new CamundaCommonService();
    }
}

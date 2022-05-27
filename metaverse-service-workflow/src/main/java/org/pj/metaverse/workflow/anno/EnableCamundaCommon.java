package org.pj.metaverse.workflow.anno;

import org.pj.metaverse.workflow.config.CamundaCommonConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动Camunda通用工具
 *
 * @author charlotte
 * @date 2022-05-27 12:00
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(CamundaCommonConfig.class)
public @interface EnableCamundaCommon {
}

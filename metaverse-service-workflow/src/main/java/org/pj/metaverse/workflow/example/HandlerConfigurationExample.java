package org.pj.metaverse.workflow.example;

/**
 * @author pengjie
 * @date 14:27 2022/5/26
 **/

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Camunda 外部任务，订阅主题
 * @author admin
 */
@Configuration
public class HandlerConfigurationExample {

    protected static final Logger LOG = LoggerFactory.getLogger(HandlerConfigurationExample.class);

    /**
     * ExternalTaskSubscription注解开启订阅，value是任务名及topicName
     * @author pengjie
     * @date 2022/5/26 14:29
     * @return org.camunda.bpm.client.task.ExternalTaskHandler
     */
    @Bean
    @ExternalTaskSubscription(value = "External_Work",autoOpen = false)
    public ExternalTaskHandler invoiceCreatorHandler() {
        return (externalTask, externalTaskService) -> {
            // TODO 任务完成前置操作
            String businessKey = externalTask.getBusinessKey();
            // TODO 业务逻辑
            LOG.info("修改数据库");
            // TODO 向下传递参数
            Map<String, Object> variables = new HashMap<>(1);
            variables.put("author","charlotte");
            // 完成外部任务 参数一固定任务，参数二
            externalTaskService.complete(externalTask, variables);
            // TODO 任务完成后置操作
            LOG.info("The External Task {} has been completed!，businessKey:{}", externalTask.getId(),businessKey);

        };
    }

}

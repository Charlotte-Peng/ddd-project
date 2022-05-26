package org.pj.metaverse.workflow.utils;

import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;

/**
 * @author pengjie
 * @date 11:06 2022/5/26
 **/

@Component
public class WorkFlowUtils {
    @Value("${workFlow.url}")
    private static String url;

    @Value("${workFlow.asyncResponseTimeout}")
    private static Integer asyncResponseTimeout;


    public static ExternalTaskClient getClient(){
        return ExternalTaskClient.create()
                .baseUrl(url)
                // 长轮询超时时间
                .asyncResponseTimeout(asyncResponseTimeout)
                .build();
    }
    /**
     * 订阅指定的外部任务
     * @author pengjie
     * @date 2022/5/26 11:15
     * @param externalTopic 指定变量名字
     */
    public static void subscribeToTheSpecifiedExternalTask(String externalTopic,String ... strings){
        ExternalTaskClient client = getClient();
        client.subscribe(externalTopic)
                .handler((externalTask, externalTaskService) -> {
                    // TODO 将您的业务逻辑写在这

                    // 获取流程变量
                    String item = (String) externalTask.getVariable("item");
                    Long amount = (Long) externalTask.getVariable("amount");


                    try {
                        Desktop.getDesktop().browse(new URI("https://docs.camunda.org/get-started/quick-start/complete"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // 完成任务
                    externalTaskService.complete(externalTask);
                })
                .open();
    }
}

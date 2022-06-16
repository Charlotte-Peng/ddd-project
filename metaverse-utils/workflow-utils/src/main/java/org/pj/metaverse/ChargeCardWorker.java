package org.pj.metaverse;

import org.camunda.bpm.client.ExternalTaskClient;

import java.awt.*;
import java.net.URI;
import java.util.logging.Logger;

/**
 * @author pengjie
 * @date 10:57 2022/5/26
 **/
public class ChargeCardWorker {
    private final static Logger LOGGER = Logger.getLogger(ChargeCardWorker.class.getName());

    public static void main(String[] args) {
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://10.8.8.4:8080/engine-rest")
                // 长轮询超时时间
                .asyncResponseTimeout(10000)
                .build();

        // 订阅指定的外部任务
        client.subscribe("charge-card")
                // 默认锁定时间为20秒，这里修改为1秒
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    // TODO 将您的业务逻辑写在这
                    LOGGER.info("进来了");
                    // 获取流程变量
                    String item = (String) externalTask.getVariable("item");
                    Long amount = (Long) externalTask.getVariable("amount");

                    LOGGER.info("Charging credit card with an amount of '" + amount + "'€ for the item '" + item + "'...");

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

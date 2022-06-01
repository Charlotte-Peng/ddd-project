package org.pj.metaverse.workflow.handle;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.SpringTopicSubscription;
import org.camunda.bpm.client.spring.event.SubscriptionInitializedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author pengjie
 * @date 14:25 2022/5/26
 **/
@Component
@Slf4j
public class Subscriptions implements ApplicationListener<SubscriptionInitializedEvent> {

    @Autowired
    public SpringTopicSubscription invoiceCreatorHandlerSubscription;

    @Autowired
    public SpringTopicSubscription invoiceArchiverHandlerSubscription;

    @PostConstruct
    public void listSubscriptionBeans() {
        log.info("Subscription bean 'invoiceCreatorHandlerSubscription' has topic name: {} ",
                invoiceCreatorHandlerSubscription.getTopicName());
        log.info("Subscription bean 'invoiceArchiverHandlerSubscription' has topic name: {} ",
                invoiceArchiverHandlerSubscription.getTopicName());
    }

    @Override
    public void onApplicationEvent(SubscriptionInitializedEvent event) {
        SpringTopicSubscription springTopicSubscription = event.getSource();
        String topicName = springTopicSubscription.getTopicName();
        log.info("Subscription with topic name '{}' initialized", topicName);

        if (!springTopicSubscription.isOpen()) {
            log.info("Subscription with topic name '{}' not yet opened", topicName);

            // do something before subscription is opened

            springTopicSubscription.open();

            log.info("Subscription with topic name '{}' opened", topicName);

            springTopicSubscription.close();

            log.info("Subscription with topic name '{}' temporarily closed", topicName);

            // do something with subscription temporarily closed

            springTopicSubscription.open();

            log.info("Subscription with topic name '{}' reopened again", topicName);
        }
    }

}

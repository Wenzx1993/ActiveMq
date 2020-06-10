package com.activemq.demo.config;

import com.activemq.demo.domain.EntityMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


/**
 * 消费者配置
 */
@Component
public class MQConsumer {


    /**
     * 字符串队列消费者
     * @param message
     */
    @JmsListener(destination = "activemq.queue.string",containerFactory = "queueContainerFactory")
    public void consumeStringMsg(String message)
    {
        System.out.println("收到消息String:"+message);
    }

    /**
     * 字符串topic消费者
     * @param message
     */
    @JmsListener(destination = "activemq.topic.string",containerFactory = "topicContainerFactory")
    public void consumeStringTopic(String message)
    {
        System.out.println("收到消息topic:"+message);
    }

    /**
     * entity队列消费者
     * @param message
     */
    @JmsListener(destination = "activemq.queue.entity",containerFactory = "queueContainerFactory")
    public void consumeEntityMsg(EntityMessage message)
    {
        System.out.println("收到消息entity:"+message.toString());
    }
}

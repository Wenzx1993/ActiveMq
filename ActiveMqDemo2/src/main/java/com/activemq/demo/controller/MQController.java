package com.activemq.demo.controller;

import com.activemq.demo.domain.EntityMessage;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activemq/msg")
public class MQController {

    @Autowired
    JmsTemplate  jmsTemplate;

    /**
     * 通过queue发送字符串
     * @param queue
     * @param msg
     */
    @PostMapping("/queue/string")
    public void sendQueueStringMsg( String queue,String msg)
    {
        //默认是queue队列消息，可以不封装
        jmsTemplate.convertAndSend(queue,msg);
    }

    /**
     * 通过queue发送对象
     * @param queue
     * @param msg
     */
    @PostMapping("/queue/entity")
    public void sendQueueEntityMsg(String queue, EntityMessage msg)
    {
        //默认是queue队列消息，可以不封装
        jmsTemplate.convertAndSend(queue,msg);
    }

    /**
     * 通过topic广播字符串
     * @param topic
     * @param msg
     */
    @PostMapping("/topic/string")
    public void sendTopicStringMsg(String topic, String msg)
    {
        //封装成topic消息
        ActiveMQTopic activeMQTopic = new ActiveMQTopic(topic);
        jmsTemplate.convertAndSend(activeMQTopic,msg);
    }


}

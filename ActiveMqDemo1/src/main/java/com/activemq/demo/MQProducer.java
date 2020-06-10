package com.activemq.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQProducer {

    public static void main(String[] args) {
        try {
            produceMsg("activemq.topic","activemq 测试通信！");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void produceMsg(String topic,String msg) throws JMSException {
        //配置地址
        String url = "tcp://192.168.239.128:61616";

        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("system","manager",url);

        //创建连接
        Connection connection = activeMQConnectionFactory.createConnection();

        //启动连接
        connection.start();

        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //创建目的地
//        Queue sessionQueue = session.createQueue(queue);

        //创建目的地
        Topic sessionTopic = session.createTopic(topic);

        //创建生产者
        MessageProducer producer = session.createProducer(sessionTopic);

        //创建消息
        TextMessage textMessage = session.createTextMessage(msg);

        //发送消息
        producer.send(textMessage);

        //关闭producer、session、connection
        producer.close();
        session.close();
        connection.close();
    }
}

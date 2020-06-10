package com.activemq.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQConsumer {

    public static void main(String[] args) {
        try {
            consumeMsg("activemq.topic");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static  void consumeMsg(String topic) throws JMSException {
        //通信地址
        String url  = "tcp://192.168.239.128:61616";

        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("system","manager",url);

        //创建工厂
        Connection connection = activeMQConnectionFactory.createConnection();

        //启动连接
        connection.start();

        //创建会话
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        //创建目的地
//        Queue sessionQueue = session.createQueue(queue);

        //创建目的地
        Topic sessionTopic = session.createTopic(topic);

        //创建消费者
        MessageConsumer consumer = session.createConsumer(sessionTopic);

        //创建监听器
        consumer.setMessageListener(message->{
            if(message instanceof TextMessage)
            {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //关闭consumer、session、connection
        /*consumer.close();
        session.close();
        connection.close();*/
    }
}

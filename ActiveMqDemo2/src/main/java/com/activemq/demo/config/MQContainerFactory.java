package com.activemq.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
public class MQContainerFactory {

    /**
     * 配置queue消息监听容器
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory queueContainerFactory(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory)
    {
        //默认容器，符合大多数需求
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        //添加连接工厂
        jmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        return jmsListenerContainerFactory;
    }

    /**
     * 配置topic消息监听容器
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory topicContainerFactory(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory)
    {
        //默认容器，符合大多数需求
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        //添加连接工厂
        jmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        //设置为topic
        jmsListenerContainerFactory.setPubSubDomain(true);
        return jmsListenerContainerFactory;
    }
}


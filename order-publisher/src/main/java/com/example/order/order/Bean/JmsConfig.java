package com.example.order.order.Bean;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.Queue;

@Configuration
@EnableJms
public class JmsConfig {

    @Value("${spring.activemq.broker-url}")
    private String BrokerUrl;

    @Value("${spring.activemq.user}")
    private String username;

    @Value("${spring.activemq.password}")
    private String password;

    @Value("${spring.artemis.embedded.queues}")
    private String QueueDestination;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(BrokerUrl);
        activeMQConnectionFactory.setUser(username);
        activeMQConnectionFactory.setPassword(password);
        return activeMQConnectionFactory;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ConnectionFactory connectionFactory,
            DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setErrorHandler(Throwable::printStackTrace);
        factory.setConcurrency("50-100");
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws JMSException {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory());
        jmsTemplate.setDefaultDestination(queue());
        return jmsTemplate;

    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(QueueDestination);
    }

}

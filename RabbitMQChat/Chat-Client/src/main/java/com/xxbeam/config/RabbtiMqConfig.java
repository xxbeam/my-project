package com.xxbeam.config;

import com.xxbeam.listen.RabbitMqListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbtiMqConfig {
    @Value("${rabbitmq.quene}")
    String quene;
    @Autowired
    RabbitMqListener rabbitMqListener;
    @Bean
    public Queue Queue() {
        return new Queue(quene,true,false,true);
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(Queue());
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            byte[] body = message.getBody();
            if(null != body) {
                try {
                    String msg = new String(body);
                    rabbitMqListener.process(msg);//消费消息
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return container;
    }

}

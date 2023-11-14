package br.com.solutis.squad1.paymentservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.queue.payment.gateway}")
    private String paymentGatewayQueueName;

    @Value("${spring.rabbitmq.exchange.payment.gateway}")
    private String paymentGatewayExchangeName;

    @Value("${spring.rabbitmq.routing-key.payment.gateway}")
    private String paymentGatewayRoutingKey;

    @Value("${spring.rabbitmq.queue.order.status}")
    private String orderStatusQueueName;

    @Value("${spring.rabbitmq.queue.gateway}")
    private String gatewayQueueName;

    @Bean
    public Queue paymentGatewayQueue() {
        return QueueBuilder
                .durable(paymentGatewayQueueName)
                .build();
    }

    @Bean
    DirectExchange paymentGatewayExchange() {
        return new DirectExchange(paymentGatewayExchangeName);
    }

    @Bean
    Binding paymentGatewayBinding() {
        return BindingBuilder
                .bind(paymentGatewayQueue())
                .to(paymentGatewayExchange())
                .with(paymentGatewayRoutingKey);
    }

    @Bean
    public Queue orderStatusQueue() {
        return QueueBuilder
                .durable(orderStatusQueueName)
                .build();
    }

    @Bean
    public Queue gatewayQueue() {
        return QueueBuilder
                .durable(gatewayQueueName)
                .build();
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> listener(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter messageConverter
    ) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}

package br.com.solutis.squad1.paymentservice.producer;

import br.com.solutis.squad1.paymentservice.dto.GatewayDto;
import br.com.solutis.squad1.paymentservice.model.entity.enums.FormPayment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GatewayProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.routing-key.gateway}")
    private String gatewayRoutingKey;

    @Value("${spring.rabbitmq.exchange.gateway}")
    private String gatewayExchange;

    public void produce(Long paymentId, FormPayment formPayment) {
        log.info("Sending message to queue: {}", gatewayRoutingKey);

        rabbitTemplate.convertAndSend(
                gatewayRoutingKey,
                gatewayExchange,
                new GatewayDto(paymentId, formPayment)
        );
    }
}

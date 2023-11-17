package br.com.solutis.squad1.paymentservice.producer;

import br.com.solutis.squad1.paymentservice.dto.UpdateOrderStatusDto;
import br.com.solutis.squad1.paymentservice.model.entity.enums.StatusPayment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderStatusProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.routing-key.order.status}")
    private String orderRoutingKey;

    @Value("${spring.rabbitmq.exchange.order.status}")
    private String orderExchange;

    /**
     * Sends a message to the order queue
     *
     * @param orderId       the order id
     * @param statusPayment the status payment
     */
    public void produce(Long orderId, StatusPayment statusPayment) {
        log.info("Update status of order: {} to: {}", orderId, statusPayment);

        rabbitTemplate.convertAndSend(
                orderExchange,
                orderRoutingKey,
                new UpdateOrderStatusDto(orderId, statusPayment)
        );
    }
}

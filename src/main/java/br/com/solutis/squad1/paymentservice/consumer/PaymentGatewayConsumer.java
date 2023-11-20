package br.com.solutis.squad1.paymentservice.consumer;

import br.com.solutis.squad1.paymentservice.dto.PaymentAnalysisDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentResponseDto;
import br.com.solutis.squad1.paymentservice.model.entity.enums.StatusPayment;
import br.com.solutis.squad1.paymentservice.producer.OrderStatusProducer;
import br.com.solutis.squad1.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentGatewayConsumer {
    private final OrderStatusProducer orderStatusProducer;
    private final PaymentService paymentService;

    @Value("${spring.rabbitmq.queue.payment.gateway}")
    private String paymentGatewayQueueName;

    /**
     * Consume message from payment gateway queue
     * @param paymentAnalysisDto
     */
    @RabbitListener(queues = {"${spring.rabbitmq.queue.payment.gateway}"})
    public void consume(
            @Payload PaymentAnalysisDto paymentAnalysisDto
            ) {
        log.info("Consuming message from queue: {}", paymentGatewayQueueName);
        log.info("Message: {}", paymentAnalysisDto);

        PaymentResponseDto paymentResponseDto = paymentService.findById(paymentAnalysisDto.paymentId());

        StatusPayment statusPayment = paymentAnalysisDto.confirmed() ? StatusPayment.CONFIRMED : StatusPayment.REFUSED;
        orderStatusProducer.produce(paymentResponseDto.orderId(), statusPayment);
    }
}

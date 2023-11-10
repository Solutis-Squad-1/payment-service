package br.com.solutis.squad1.paymentservice.service;

import br.com.solutis.squad1.paymentservice.dto.OrderPutDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentPostDto;
import br.com.solutis.squad1.paymentservice.http.OrderHttpClient;
import br.com.solutis.squad1.paymentservice.mapper.PaymentMapper;
import br.com.solutis.squad1.paymentservice.model.entity.Payment;
import br.com.solutis.squad1.paymentservice.model.entity.enums.FormPayment;
import br.com.solutis.squad1.paymentservice.model.entity.enums.StatusPayment;
import br.com.solutis.squad1.paymentservice.model.repository.PaymentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @InjectMocks
    private PaymentService service;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private OrderHttpClient orderHttpClient;
    @Mock
    private PaymentMapper mapper;

    @Test
    @DisplayName("Must update payment status in order microservice")
    void updatePaymentStatusInOrderService() {
        Long paymentId = 1L;
        OrderPutDto dto = createOrderPutDto();

        when(mapper.StatusPaymentToOrderPutDto(dto.statusPayment())).thenReturn(dto);

        service.updateStatus(paymentId, dto.statusPayment());

        verify(orderHttpClient).update(paymentId, dto);
    }

    @Test
    @DisplayName("Delete payment by id")
    void delete() {
        Long paymentId = 1L;
        Payment payment = createPayment();
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payment));

        service.delete(paymentId);

        verify(paymentRepository).findById(paymentId);
        verify(paymentRepository).delete(payment);
    }

    private Payment createPayment() {
        return mapper.postDtoToEntity(createPaymentPostDto());
    }

    private OrderPutDto createOrderPutDto() {
        return new OrderPutDto(
                StatusPayment.CONFIRMED
        );
    }

    private PaymentPostDto createPaymentPostDto() {
        return new PaymentPostDto(
                1L,
                1L,
                new BigDecimal(200),
                FormPayment.PIX
        );
    }

}
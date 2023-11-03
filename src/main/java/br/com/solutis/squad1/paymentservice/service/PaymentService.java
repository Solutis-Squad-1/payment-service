package br.com.solutis.squad1.paymentservice.service;

import br.com.solutis.squad1.paymentservice.dto.PaymentPostCreditCardDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentPostDto;
import br.com.solutis.squad1.paymentservice.http.OrderHttpClient;
import br.com.solutis.squad1.paymentservice.mapper.PaymentMapper;
import br.com.solutis.squad1.paymentservice.model.entity.Payment;
import br.com.solutis.squad1.paymentservice.model.entity.enums.StatusPayment;
import br.com.solutis.squad1.paymentservice.model.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    private final OrderHttpClient orderHttpClient;

    public void save(PaymentPostDto paymentPostDto) {
        Payment payment = mapper.postDtoToEntity(paymentPostDto);

        paymentRepository.save(payment);

        //Passar a requisição para o mock via comunicação assíncrona
    }

    public void saveCreditCard(PaymentPostCreditCardDto paymentPostCreditCardDto) {
        Payment payment = new Payment(paymentPostCreditCardDto);

        paymentRepository.save(payment);

        //Passar a requisição para o mock via comunicação assíncrona
    }

    public void delete(Long id) {
        Payment payment = paymentRepository.findById(id).get();

        paymentRepository.delete(payment);
    }

    public void updateStatus(Long id, StatusPayment statusPayment) {
        orderHttpClient.update(id, mapper.StatusPaymentToOrderPutDto(statusPayment));
    }
}

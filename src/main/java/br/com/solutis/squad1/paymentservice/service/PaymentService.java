package br.com.solutis.squad1.paymentservice.service;

import br.com.solutis.squad1.paymentservice.dto.PaymentPostDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentPutDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentResponseDto;
import br.com.solutis.squad1.paymentservice.http.OrderHttpClient;
import br.com.solutis.squad1.paymentservice.mapper.PaymentMapper;
import br.com.solutis.squad1.paymentservice.model.entity.Payment;
import br.com.solutis.squad1.paymentservice.model.entity.enums.StatusPayment;
import br.com.solutis.squad1.paymentservice.model.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderHttpClient orderHttpClient;

    public Page<PaymentResponseDto> findAll(Pageable pageable) {
        return paymentRepository.findAllByDeletedFalse(pageable).map(paymentMapper::toResponseDto);
    }

    public PaymentResponseDto save(PaymentPostDto paymentPostDto) {
        Payment payment = paymentMapper.postDtoToEntity(paymentPostDto);

        return paymentMapper.toResponseDto(paymentRepository.save(payment));

        //Passar a requisição para o mock via comunicação assíncrona
    }

    public void update(Long id, PaymentPutDto paymentPutDto) {
        Payment payment = paymentRepository.getReferenceById(id);
        payment.update(paymentMapper.putDtoToEntity(paymentPutDto));
    }

    public void delete(Long id) {
        Payment payment = paymentRepository.getReferenceById(id);
        paymentRepository.delete(payment);
    }

    public void updateStatus(Long id, StatusPayment statusPayment) {
        orderHttpClient.update(id, paymentMapper.StatusPaymentToOrderPutDto(statusPayment));
    }

    public PaymentResponseDto findById(Long id) {
        return paymentMapper.toResponseDto(paymentRepository.findByIdAndDeletedFalse(id).orElseThrow());
    }
}

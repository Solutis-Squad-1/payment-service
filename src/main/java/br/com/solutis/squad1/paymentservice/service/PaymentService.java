package br.com.solutis.squad1.paymentservice.service;

import br.com.solutis.squad1.paymentservice.dto.PaymentPostDto;
import br.com.solutis.squad1.paymentservice.mapper.PaymentMapper;
import br.com.solutis.squad1.paymentservice.model.entity.Payment;
import br.com.solutis.squad1.paymentservice.model.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private PaymentRepository paymentRepository;
    private PaymentMapper mapper;

    public void save(PaymentPostDto paymentPostDto) {
        Payment payment = mapper.postDtoToEntity(paymentPostDto);

        paymentRepository.save(payment);
    }
}

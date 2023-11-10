package br.com.solutis.squad1.paymentservice.dto;

import br.com.solutis.squad1.paymentservice.model.entity.enums.FormPayment;

import java.math.BigDecimal;

public record PaymentResponseDto(
        Long id,
        Long orderId,
        Long userId,
        BigDecimal total,
        FormPayment formPayment
) {
}

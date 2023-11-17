package br.com.solutis.squad1.paymentservice.dto;

import br.com.solutis.squad1.paymentservice.model.entity.enums.FormPayment;

import java.math.BigDecimal;

/**
 * PaymentPutDto
 */
public record PaymentPutDto(
        BigDecimal total,
        FormPayment formPayment
) {
}

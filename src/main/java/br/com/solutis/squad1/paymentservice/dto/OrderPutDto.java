package br.com.solutis.squad1.paymentservice.dto;

import br.com.solutis.squad1.paymentservice.model.entity.enums.StatusPayment;

/**
 * OrderPutDto
 */
public record OrderPutDto(
        StatusPayment statusPayment
) {
}

package br.com.solutis.squad1.paymentservice.dto;

import br.com.solutis.squad1.paymentservice.model.entity.enums.FormPayment;

/**
 * GatewayDto
 */
public record GatewayDto (
        Long paymentId,
        FormPayment formPayment
) {
}

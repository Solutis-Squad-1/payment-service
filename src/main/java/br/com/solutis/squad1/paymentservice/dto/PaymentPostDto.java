package br.com.solutis.squad1.paymentservice.dto;

import br.com.solutis.squad1.paymentservice.model.entity.enums.FormPayment;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * PaymentPostDto
 */
public record PaymentPostDto(
        @NotNull
        Long userId,

        @NotNull
        Long orderId,
        @NotNull
        BigDecimal total,
        @NotNull
        FormPayment formPayment
) {
}

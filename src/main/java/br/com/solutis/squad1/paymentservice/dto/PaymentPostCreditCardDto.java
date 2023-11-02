package br.com.solutis.squad1.paymentservice.dto;

import br.com.solutis.squad1.paymentservice.model.entity.Payment;
import br.com.solutis.squad1.paymentservice.model.entity.enums.FormPayment;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PaymentPostCreditCardDto(
        @NotNull
        Long orderId,
        @NotNull
        BigDecimal total,
        @NotNull
        FormPayment formPayment,
        @NotNull
        String cardNumber,
        @NotNull
        String cvv,
        @NotNull
        String expiryDate
) {
}

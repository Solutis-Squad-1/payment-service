package br.com.solutis.squad1.paymentservice.dto;

public record PaymentAnalysisDto(
        Boolean confirmed,
        Long paymentId
) {
}

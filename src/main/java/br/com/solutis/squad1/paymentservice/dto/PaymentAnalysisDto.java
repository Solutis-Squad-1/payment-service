package br.com.solutis.squad1.paymentservice.dto;

/**
 * PaymentAnalysisDto
 */
public record PaymentAnalysisDto(
        Boolean confirmed,
        Long paymentId
) {
}

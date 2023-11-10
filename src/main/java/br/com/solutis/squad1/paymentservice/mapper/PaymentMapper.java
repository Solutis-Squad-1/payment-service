package br.com.solutis.squad1.paymentservice.mapper;

import br.com.solutis.squad1.paymentservice.dto.OrderPutDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentPostDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentPutDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentResponseDto;
import br.com.solutis.squad1.paymentservice.model.entity.Payment;
import br.com.solutis.squad1.paymentservice.model.entity.enums.StatusPayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentResponseDto toResponseDto(Payment payment);

    @Mapping(target = "id", ignore = true)
    Payment postDtoToEntity(PaymentPostDto paymentPostDto);

    OrderPutDto StatusPaymentToOrderPutDto(StatusPayment statusPayment);

    @Mapping(target = "id", ignore = true)
    Payment putDtoToEntity(PaymentPutDto paymentPutDto);
}

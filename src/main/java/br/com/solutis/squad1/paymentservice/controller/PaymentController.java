package br.com.solutis.squad1.paymentservice.controller;

import br.com.solutis.squad1.paymentservice.dto.PaymentPostDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentPutDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentResponseDto;
import br.com.solutis.squad1.paymentservice.model.entity.enums.StatusPayment;
import br.com.solutis.squad1.paymentservice.producer.GatewayProducer;
import br.com.solutis.squad1.paymentservice.producer.OrderStatusProducer;
import br.com.solutis.squad1.paymentservice.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final OrderStatusProducer orderStatusProducer;
    private final GatewayProducer gatewayProducer;

    /**
     * Find all payments
     * @param pageable
     * @return Page<PaymentResponseDto>
     */
    @Operation(summary = "Find all payments")
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Page<PaymentResponseDto> findAll(Pageable pageable) {
        return paymentService.findAll(pageable);
    }

    /**
     * Find payment by id
     * @param id
     * @return PaymentResponseDto
     */
    @Operation(summary = "Find payment by id")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('payment:read')")
    public PaymentResponseDto findById(@PathVariable Long id) {
        return paymentService.findById(id);
    }

    /**
     * Save payment
     * @param paymentPostDto
     * @return PaymentResponseDto
     */
    @Operation(summary = "Save payment")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('payment:create')")
    public PaymentResponseDto save(
            @RequestBody @Valid PaymentPostDto paymentPostDto
    ) {
        PaymentResponseDto dto = paymentService.save(paymentPostDto);
        orderStatusProducer.produce(dto.orderId(), StatusPayment.IN_PROCESSING);
        gatewayProducer.produce(dto.id(), dto.formPayment());
        return dto;
    }

    /**
     * Update payment
     * @param paymentPutDto
     * @param id
     */
    @Operation(summary = "Update payment")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('payment:update')")
    public void update(
            @RequestBody @Valid PaymentPutDto paymentPutDto,
            @PathVariable Long id
    ) {
        paymentService.update(id, paymentPutDto);
    }

    /**
     * Delete payment
     * @param id
     */
    @Operation(summary = "Delete payment")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('payment:delete')")
    public void delete(
            @PathVariable Long id
    ) {
        paymentService.delete(id);
    }
}

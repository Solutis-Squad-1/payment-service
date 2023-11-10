package br.com.solutis.squad1.paymentservice.controller;

import br.com.solutis.squad1.paymentservice.dto.PaymentPostDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentPutDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentResponseDto;
import br.com.solutis.squad1.paymentservice.service.PaymentService;
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

//    @PutMapping("/{id}/status")
//    @PreAuthorize("hasAuthority('payment:update:status')")
//    public void updateStatus(@PathVariable Long id, @RequestBody StatusPayment statusPayment){
//        paymentService.updateStatus(id, statusPayment);
//    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Page<PaymentResponseDto> findAll(Pageable pageable) {
        return paymentService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('payment:read')")
    public PaymentResponseDto findById(@PathVariable Long id) {
        return paymentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('payment:create')")
    public PaymentResponseDto save(
            @RequestBody @Valid PaymentPostDto paymentPostDto
    ) {
        return paymentService.save(paymentPostDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('payment:update')")
    public void update(
            @RequestBody @Valid PaymentPutDto paymentPutDto,
            @PathVariable Long id
    ) {
        paymentService.update(id, paymentPutDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('payment:delete')")
    public void delete(
            @PathVariable Long id
    ) {
        paymentService.delete(id);
    }
}

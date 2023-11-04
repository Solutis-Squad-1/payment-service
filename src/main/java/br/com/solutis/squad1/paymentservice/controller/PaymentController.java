package br.com.solutis.squad1.paymentservice.controller;

import br.com.solutis.squad1.paymentservice.dto.PaymentPostCreditCardDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentPostDto;
import br.com.solutis.squad1.paymentservice.model.entity.enums.StatusPayment;
import br.com.solutis.squad1.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('payment:update:status')")
    public void updateStatus(@PathVariable Long id, @RequestBody StatusPayment statusPayment){
        paymentService.updateStatus(id, statusPayment);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('payment:create')")
    public void save(
            @RequestBody @Valid PaymentPostDto paymentPostDto
    ){
        paymentService.save(paymentPostDto);
    }

    @PostMapping("/credit-card")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('payment:create:credit-card')")
    public void saveCreditCard(
            @RequestBody @Valid PaymentPostCreditCardDto paymentPostCreditCardDto
    ){
        paymentService.saveCreditCard(paymentPostCreditCardDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('payment:delete')")
    public void delete (
            @RequestBody Long id
    ) {
        paymentService.delete(id);
    }
}

package br.com.solutis.squad1.paymentservice.controller;

import br.com.solutis.squad1.paymentservice.dto.PaymentPostCreditCardDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentPostDto;
import br.com.solutis.squad1.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestBody @Valid PaymentPostDto paymentPostDto
    ){
        paymentService.save(paymentPostDto);
    }

    @PostMapping("/CreditCard")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCreditCard(
            @RequestBody @Valid PaymentPostCreditCardDto paymentPostCreditCardDto
    ){
        paymentService.saveCreditCard(paymentPostCreditCardDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (
            @RequestBody Long id
    ) {
        paymentService.delete(id);
    }
}

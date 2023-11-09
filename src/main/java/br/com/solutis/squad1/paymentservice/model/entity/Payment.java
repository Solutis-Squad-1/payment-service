package br.com.solutis.squad1.paymentservice.model.entity;

import br.com.solutis.squad1.paymentservice.dto.PaymentPostCreditCardDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentPostDto;
import br.com.solutis.squad1.paymentservice.model.entity.enums.FormPayment;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Data
@RequiredArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "form_payment", nullable = false)
    private FormPayment formPayment;

    public Payment(PaymentPostCreditCardDto dto){
        this.orderId = dto.orderId();
        this.total = dto.total();
        this.formPayment = dto.formPayment();
    }

    public Payment(PaymentPostDto dto){
        this.orderId = dto.orderId();
        this.total = dto.total();
        this.formPayment = dto.formPayment();
    }
}

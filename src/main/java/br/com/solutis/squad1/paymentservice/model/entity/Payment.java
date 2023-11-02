package br.com.solutis.squad1.paymentservice.model.entity;

import br.com.solutis.squad1.paymentservice.dto.PaymentPostCreditCardDto;
import br.com.solutis.squad1.paymentservice.model.entity.enums.FormPayment;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
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
}

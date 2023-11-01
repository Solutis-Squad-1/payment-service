package br.com.solutis.squad1.paymentservice.model.repository;

import br.com.solutis.squad1.paymentservice.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

package br.com.solutis.squad1.paymentservice.model.repository;

import br.com.solutis.squad1.paymentservice.model.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Page<Payment> findAllByDeletedFalse(Pageable pageable);

    Optional<Payment> findByIdAndDeletedFalse(Long id);
}

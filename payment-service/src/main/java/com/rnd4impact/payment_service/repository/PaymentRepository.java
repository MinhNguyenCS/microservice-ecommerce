package com.rnd4impact.payment_service.repository;

import com.rnd4impact.payment_service.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
    PaymentEntity findById(int id);
}

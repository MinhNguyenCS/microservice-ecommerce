package com.rnd4impact.payment_service.repository;

import com.rnd4impact.payment_service.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    OrderEntity findById(int id);
}

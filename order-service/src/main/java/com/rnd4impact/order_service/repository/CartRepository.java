package com.rnd4impact.order_service.repository;

import com.rnd4impact.order_service.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    CartEntity findById(int id);
}

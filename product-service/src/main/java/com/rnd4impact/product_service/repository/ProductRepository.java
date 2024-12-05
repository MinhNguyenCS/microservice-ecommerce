package com.rnd4impact.product_service.repository;

import com.rnd4impact.product_service.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    ProductEntity  findProductByProductId(int id);
    List<ProductEntity> findProductByCategoryCategoryId(int id);
}

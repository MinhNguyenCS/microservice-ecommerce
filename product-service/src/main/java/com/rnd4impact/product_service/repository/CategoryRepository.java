package com.rnd4impact.product_service.repository;

import com.rnd4impact.product_service.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    CategoryEntity findById(int id);

}

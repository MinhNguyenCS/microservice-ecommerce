package com.rnd4impact.product_service.entity;

import lombok.Data;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Column(name = "product_title")
    private String productTitle;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price_unit")
    private int priceUnit;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

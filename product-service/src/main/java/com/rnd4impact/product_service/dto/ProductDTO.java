package com.rnd4impact.product_service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductDTO implements Serializable {
    private int productId;
    private int categoryId;
    private String productTitle;
    private String imageUrl;
    private int priceUnit;
    private Integer quantity;

    private String createdAt;
    private String updatedAt;

}

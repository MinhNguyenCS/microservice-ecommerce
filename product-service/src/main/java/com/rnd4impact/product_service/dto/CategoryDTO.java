package com.rnd4impact.product_service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CategoryDTO implements Serializable {
    private int categoryId;
    private String categoryTitle;
    private String imageUrl;
    private Long subCategoryId;
    private List<ProductDTO> products;


    private String createdAt;
    private String updatedAt;
}

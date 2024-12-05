package com.rnd4impact.product_service.service.imp;

import com.rnd4impact.product_service.dto.CategoryDTO;

import java.util.List;

public interface CategoryServiceImp {
    List<CategoryDTO> getAllCategory();
    CategoryDTO getCategoryById(int id);
    void uploadCategory(String title, String imageUrl);
    void updateCategory(int id, String title, String imageUrl);
    void deleteCategory(int id);
}

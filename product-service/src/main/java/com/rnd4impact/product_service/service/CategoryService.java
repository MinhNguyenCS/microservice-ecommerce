package com.rnd4impact.product_service.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rnd4impact.product_service.dto.CategoryDTO;
import com.rnd4impact.product_service.dto.ProductDTO;
import com.rnd4impact.product_service.entity.CategoryEntity;
import com.rnd4impact.product_service.repository.CategoryRepository;
import com.rnd4impact.product_service.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RedisTemplate redisTemplate;
    private Gson gson = new Gson();

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<CategoryDTO> listDTO = new ArrayList<>();
        if (redisTemplate.hasKey("category")) {
           // redisTemplate.delete("category");
            String data = (String) redisTemplate.opsForValue().get("category");
            Type listType = new TypeToken<List<CategoryDTO>>() {}.getType();
            listDTO = gson.fromJson(data, listType);
        } else {
            List<CategoryEntity> list = categoryRepository.findAll();
            for (CategoryEntity item: list) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setCategoryId(item.getCategoryId());
                categoryDTO.setCategoryTitle(item.getCategoryTitle());
                categoryDTO.setImageUrl(item.getImageUrl());
                listDTO.add(categoryDTO);
            }
            String data = gson.toJson(listDTO);
            redisTemplate.opsForValue().set("category", data);
        }
        return listDTO;
    }

    @Override
    public CategoryDTO getCategoryById(int id) {
        CategoryDTO categoryDTO = new CategoryDTO();
        String redisName = (String) "category/" + id;
        if (redisTemplate.hasKey(redisName)) {
            String data = (String) redisTemplate.opsForValue().get(redisName);
            Type listType = new TypeToken<CategoryDTO>() {}.getType();
            categoryDTO = gson.fromJson(data, listType);
        } else {
            CategoryEntity category = categoryRepository.findById(id);
            categoryDTO.setCategoryId(category.getCategoryId());
            categoryDTO.setCategoryTitle(category.getCategoryTitle());
            categoryDTO.setImageUrl(category.getImageUrl());
            String data = gson.toJson(categoryDTO);
            redisTemplate.opsForValue().set(redisName, data);
        }
        return categoryDTO;
    }

    @Transactional
    @Override
    public void uploadCategory(String title, String imageUrl) {
        CategoryEntity category = new CategoryEntity();
        category.setCategoryTitle(title);
        category.setImageUrl(imageUrl);
        try {
            categoryRepository.save(category);
            redisTemplate.delete("category");
        } catch (Exception e) {
            throw new RuntimeException("Upload category error", e);
        }
    }

    @Transactional
    @Override
    public void updateCategory(int id, String title, String imageUrl) {
        CategoryEntity category = categoryRepository.findById(id);
        category.setCategoryTitle(title);
        category.setImageUrl(imageUrl);
        try {
            categoryRepository.save(category);
            redisTemplate.delete("category");
        } catch (Exception e) {
            throw new RuntimeException("Update category error", e);
        }
    }

    @Transactional
    @Override
    public void deleteCategory(int id) {
        CategoryEntity category = categoryRepository.findById(id);
        try {
            categoryRepository.delete(category);
            redisTemplate.delete("category");
        } catch (Exception e) {
            throw new RuntimeException("Delete category error" + e);
        }
    }

}

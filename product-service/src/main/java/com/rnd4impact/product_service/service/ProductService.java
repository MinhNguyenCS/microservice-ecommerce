package com.rnd4impact.product_service.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rnd4impact.product_service.dto.ProductDTO;
import com.rnd4impact.product_service.entity.CategoryEntity;
import com.rnd4impact.product_service.entity.ProductEntity;
import com.rnd4impact.product_service.repository.CategoryRepository;
import com.rnd4impact.product_service.repository.ProductRepository;
import com.rnd4impact.product_service.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceImp {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RedisTemplate redisTemplate;
    private Gson gson = new Gson();

    @Override
    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> dtoList = new ArrayList<>();
        if (redisTemplate.hasKey("product")) {
            Type listType = new TypeToken<List<ProductDTO>>() {}.getType();
            String data = (String) redisTemplate.opsForValue().get("product");
            dtoList = gson.fromJson(data, listType);
        } else {
            List<ProductEntity> list = productRepository.findAll();
            for (ProductEntity item: list) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setProductId(item.getProductId());
                productDTO.setCategoryId(item.getCategory().getCategoryId());
                productDTO.setProductTitle(item.getProductTitle());
                productDTO.setImageUrl(item.getImageUrl());
                productDTO.setPriceUnit(item.getPriceUnit());
                productDTO.setQuantity(item.getQuantity());
                dtoList.add(productDTO);
            }
            String data = gson.toJson(dtoList);
            redisTemplate.opsForValue().set("product",data);
        }

        return dtoList;
    }

    @Override
    public ProductDTO getProductById(int id) {
        ProductDTO productDTO = new ProductDTO();
        String redisName = (String) "product/" + id;
        if (redisTemplate.hasKey(redisName)) {
            String data = (String) redisTemplate.opsForValue().get(redisName);
            Type listType = new TypeToken<ProductDTO>() {}.getType();
            productDTO = gson.fromJson(data, listType);
        } else {
            ProductEntity item = productRepository.findProductByProductId(id);
            productDTO.setProductId(item.getProductId());
            productDTO.setCategoryId(item.getCategory().getCategoryId());
            productDTO.setProductTitle(item.getProductTitle());
            productDTO.setImageUrl(item.getImageUrl());
            productDTO.setPriceUnit(item.getPriceUnit());
            productDTO.setQuantity(item.getQuantity());
            String data = gson.toJson(productDTO);
            redisTemplate.opsForValue().set(redisName, data);
        }

        return productDTO;
    }

    @Override
    public List<ProductDTO> getProductByCategory(int id) {
        List<ProductEntity> list = productRepository.findProductByCategoryCategoryId(id);
        List<ProductDTO> dtoList = new ArrayList<>();
        for (ProductEntity item: list) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(item.getProductId());
            productDTO.setCategoryId(item.getCategory().getCategoryId());
            productDTO.setProductTitle(item.getProductTitle());
            productDTO.setImageUrl(item.getImageUrl());
            productDTO.setPriceUnit(item.getPriceUnit());
            productDTO.setQuantity(item.getQuantity());
            dtoList.add(productDTO);
        }
        return dtoList;
    }


    @Override
    public void uploadProduct(int categoryId, String productTitle,String imageUrl, int priceUnit, int quantity  ) {
        CategoryEntity category = categoryRepository.findById(categoryId);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory(category);
        productEntity.setProductTitle(productTitle);
        productEntity.setImageUrl(imageUrl);
        productEntity.setPriceUnit(priceUnit);
        productEntity.setQuantity(quantity);
        try{
            productRepository.save(productEntity);
            redisTemplate.delete("product");
        } catch(Exception e) {
            throw new RuntimeException("Insert Product Error");
        }
    }

    @Transactional
    @Override
    public void updateProduct(int productId, int categoryId, String productTitle, String imageUrl, int priceUnit, int quantity) {
        ProductEntity existProduct = productRepository.findProductByProductId(productId);
        CategoryEntity category = categoryRepository.findById(categoryId);
        existProduct.setCategory(category);
        existProduct.setProductTitle(productTitle);
        existProduct.setImageUrl(imageUrl);
        existProduct.setPriceUnit(priceUnit);
        existProduct.setQuantity(quantity);
        try{
            productRepository.save(existProduct);
            redisTemplate.delete("product");
        } catch(Exception e) {
            throw new RuntimeException("Update Product Error");
        }
    }

    @Transactional
    @Override
    public void deleteProduct(int id) {
        ProductEntity existProduct = productRepository.findProductByProductId(id);
        try {
            productRepository.delete(existProduct);
            redisTemplate.delete("product");
        } catch (Exception e) {
            throw new RuntimeException("Error deleting product with ID:" + id, e);
        }
    }


}

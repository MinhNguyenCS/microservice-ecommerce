package com.rnd4impact.product_service.service.imp;

import com.rnd4impact.product_service.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductServiceImp {
    List<ProductDTO> getAllProduct();
    ProductDTO getProductById(int id);
    List<ProductDTO> getProductByCategory(int id);
    void uploadProduct(int categoryId, String productTitle,String imageUrl, int priceUnit, int quantity  );
    void updateProduct(int productId, int categoryId, String productTitle, String imageUrl, int priceUnit, int quantity);
    void deleteProduct(int id);
}

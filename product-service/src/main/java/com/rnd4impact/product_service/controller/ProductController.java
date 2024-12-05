package com.rnd4impact.product_service.controller;

import com.google.gson.Gson;
import com.rnd4impact.product_service.dto.ProductDTO;
import com.rnd4impact.product_service.payload.response.BaseResponse;
import com.rnd4impact.product_service.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductServiceImp productServiceImp;



    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {

        List<ProductDTO> list = productServiceImp.getAllProduct();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(list);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        ProductDTO list = productServiceImp.getProductById(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(list);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/by-category/{id}")
    public ResponseEntity<?> getProductByCategory(@PathVariable int id) {
        List<ProductDTO> list = productServiceImp.getProductByCategory(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(list);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?>uploadProduct(@RequestParam int categoryId,
                                          @RequestParam String title,
                                          @RequestParam String imageUrl,
                                          @RequestParam int price,
                                          @RequestParam int quantity) {
        productServiceImp.uploadProduct(categoryId,title, imageUrl, price, quantity);
        return new ResponseEntity<>("Insert Success", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestParam int productId,
                                           @RequestParam int categoryId,
                                           @RequestParam String title,
                                           @RequestParam String imageUrl,
                                           @RequestParam int price,
                                           @RequestParam int quantity) {
        productServiceImp.updateProduct(productId,categoryId,title,imageUrl,price,quantity);
        return new ResponseEntity<>("Updated product", HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        productServiceImp.deleteProduct(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}

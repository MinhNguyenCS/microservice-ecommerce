package com.rnd4impact.product_service.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.rnd4impact.product_service.dto.CategoryDTO;
import com.rnd4impact.product_service.payload.response.BaseResponse;
import com.rnd4impact.product_service.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllCategory() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(categoryServiceImp.getAllCategory());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable int id) {
        CategoryDTO categoryDTO = categoryServiceImp.getCategoryById(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(categoryDTO);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadCategory(@RequestParam String title,
                                            @RequestParam String imageUrl) {
        categoryServiceImp.uploadCategory(title,imageUrl);
        return new ResponseEntity<>("Upload Category Sucessfully", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestParam int id,
                                            @RequestParam String title,
                                            @RequestParam String imageUrl) {
        categoryServiceImp.updateCategory(id, title,imageUrl);
        return new ResponseEntity<>("Update Category Sucessfully", HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        categoryServiceImp.deleteCategory(id);
        return new ResponseEntity<>("Delete Category Sucessfully", HttpStatus.OK);
    }
}

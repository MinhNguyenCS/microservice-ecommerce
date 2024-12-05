package com.rnd4impact.order_service.controller;

import com.rnd4impact.order_service.DTO.CartDTO;
import com.rnd4impact.order_service.payload.response.BaseResponse;
import com.rnd4impact.order_service.service.imp.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartServiceImp cartServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllCart() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(cartServiceImp.getAllCart());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(@PathVariable int id) {
        CartDTO cartDTO = cartServiceImp.getCartById(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(cartDTO);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadCategory(@RequestParam int userId) {
        cartServiceImp.uploadCart(userId);
        return new ResponseEntity<>("Upload Cart Sucessfully", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestParam int id,
                                            @RequestParam int userId) {
        cartServiceImp.updateCart(id, userId);
        return new ResponseEntity<>("Update Cart Sucessfully", HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        cartServiceImp.deleteCart(id);
        return new ResponseEntity<>("Delete Cart Sucessfully", HttpStatus.OK);
    }
}

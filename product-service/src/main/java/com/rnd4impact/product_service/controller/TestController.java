package com.rnd4impact.product_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping("")
    public ResponseEntity<?> login() {
        return new ResponseEntity<>("Hello login page", HttpStatus.OK);
    }
}

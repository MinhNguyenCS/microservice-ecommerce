package com.rnd4impact.user_service.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;
    private Gson gson;

    @PostMapping("")
    public ResponseEntity<?> test() {

        String data = "Test Redis";
        redisTemplate.opsForValue().set("test", data);
        return new ResponseEntity<>("Test Server", HttpStatus.OK);
    }
}

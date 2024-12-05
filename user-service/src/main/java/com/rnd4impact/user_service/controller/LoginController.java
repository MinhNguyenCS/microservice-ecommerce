package com.rnd4impact.user_service.controller;

import com.google.gson.Gson;
import com.rnd4impact.user_service.jwt.JwtHelper;
import com.rnd4impact.user_service.service.imp.LoginServiceImp;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoder;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private Gson gson = new Gson();

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private LoginServiceImp loginServiceImp;

    @PostMapping("")
    public ResponseEntity<?> login() {
        /*//SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        SecretKey key = Jwts.SIG.HS256.key().build();
        String strKey = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("TestKey:" + strKey);*/
        String data = jwtHelper.decodeToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZWxsbyBqd3QifQ.x7wS2uBTQA7sLtBBosHkApeBpzKOA1qdcZMeJkz40OU");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }



    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        String json = gson.toJson(authentication.getAuthorities());
        String jwtToken = jwtHelper.generateToken(json);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }


}

package com.rnd4impact.product_service.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtHelper {

    @Value("${token.key}")
    private String strKey;

    public String decodeToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        return Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}

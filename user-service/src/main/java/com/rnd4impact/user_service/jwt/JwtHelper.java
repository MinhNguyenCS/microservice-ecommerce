package com.rnd4impact.user_service.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHelper {

    //Get value from Application file
    // @value only work when class on IOC
    @Value("${token.key}")
    private String strKey;

    private int expriredTime = 8 * 60 * 60 * 1000;

    public String generateToken(String data) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        Date date = new Date();
        long futureMilis = date.getTime() + expriredTime;
        Date futureDate = new Date(futureMilis);
        return Jwts.builder().subject(data).expiration(futureDate).signWith(key).compact();
    }

    public String decodeToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        return Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}

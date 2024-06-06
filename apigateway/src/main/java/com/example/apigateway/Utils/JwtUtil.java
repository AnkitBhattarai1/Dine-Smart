package com.example.apigateway.Utils;

import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

    @Autowired
    private Environment env;

    String secret;

    @PostConstruct
    public void init() {
        this.secret = env.getProperty("secret");
    }

    public Key getSignKey() {
        byte[] keybytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keybytes);
    }

    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

}

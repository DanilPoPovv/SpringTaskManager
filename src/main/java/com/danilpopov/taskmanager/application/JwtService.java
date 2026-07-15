package com.danilpopov.taskmanager.application;

import com.danilpopov.taskmanager.Domain.Entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Service
public class JwtService {
    private SecretKey key;
    @Value("${jwt.tokenExpirationSeconds}")
    private Long expiration;
    public JwtService(@Value("${jwt.secret}") String secret){
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    public String generateJwt(User user){
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId",user.getId())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration * 9999))
                .signWith(key)
                .compact();
    }
    public Long extractId(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("userId", Long.class);
    }
}

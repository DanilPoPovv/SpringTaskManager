package com.danilpopov.taskmanager.application;

import com.danilpopov.taskmanager.Domain.Entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
    private final Long accessTokenDuration;
    private final Long refreshTokenDuration;
    private final SecretKeySpec keySpec;

    public JwtService(@Value("${jwt.accessTokenDuration}") Long accessTokenDuration,
                      @Value("${jwt.refreshTokenDuration}") Long refreshTokenDuration,
                      @Value("${jwt.secret}") String jwtSecret) {
        this.accessTokenDuration = accessTokenDuration;
        this.refreshTokenDuration = refreshTokenDuration;
        keySpec = new SecretKeySpec(Decoders.BASE64URL.decode(jwtSecret), "HmacSHA256");
    }

    public String generateAccessToken(Long userId, Role role ) {
        Instant now = Instant.now();

        return Jwts.builder().subject(userId.toString())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(accessTokenDuration)))
                .claim("role", "ROLE_" + role.name())
                .claim("type", "ACCESS")
                .signWith(keySpec)
                .compact();
    }
    public String generateRefreshToken(Long userId) {
        Instant now = Instant.now();

        return Jwts.builder().subject(userId.toString())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(refreshTokenDuration)))
                .claim("type", "REFRESH")
                .signWith(keySpec)
                .compact();
    }
    public Claims verifyJwt(String jwt) {
        return Jwts.parser().verifyWith(keySpec).build().parseSignedClaims(jwt).getPayload();
    }
}

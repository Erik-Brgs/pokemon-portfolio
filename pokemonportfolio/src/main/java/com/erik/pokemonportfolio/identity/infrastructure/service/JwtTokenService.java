package com.erik.pokemonportfolio.identity.infrastructure.service;

import com.erik.pokemonportfolio.identity.application.service.TokenService;
import com.erik.pokemonportfolio.identity.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

public class JwtTokenService implements TokenService {

    private final SecretKey secretKey;
    private final long expirationMillis;

    public JwtTokenService(String secret, long expirationMillis) {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);;
        this.expirationMillis = expirationMillis;
    }

    @Override
    public String generateToken(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMillis);

        return  Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("displayName", user.getDisplayName())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String extractUserId(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

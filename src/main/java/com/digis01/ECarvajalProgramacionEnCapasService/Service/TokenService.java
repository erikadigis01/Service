/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.ECarvajalProgramacionEnCapasService.Service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenService {
    
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 5 * 60 * 1000; // 5 minutos
    
    // Almacén en memoria para tokens y su información
    private final Map<String, TokenInfo> tokenStore = new ConcurrentHashMap<>();
    
    public String generateToken(String username, Integer userId) {
        String token = Jwts.builder()
            .setSubject(username)
            .claim("userId", userId)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SECRET_KEY)
            .compact();
        
        // Guardar información del token
        tokenStore.put(token, new TokenInfo(username, userId));
        
        return token;
    }
    
    public boolean validateToken(String token) {
        if (!tokenStore.containsKey(token)) {
            return false;
        }
        
        TokenInfo tokenInfo = tokenStore.get(token);
        
        // Verificar expiración
        if (isTokenExpired(tokenInfo)) {
            tokenStore.remove(token);
            return false;
        }
        
        // Verificar límite de operaciones
        if (tokenInfo.getOperationCount() >= 5) {
            tokenStore.remove(token);
            return false;
        }
        
        return true;
    }
    
    public String getUsernameFromToken(String token) {
        TokenInfo tokenInfo = tokenStore.get(token);
        return tokenInfo != null ? tokenInfo.getUsername() : null;
    }
    
    public Integer getUserIdFromToken(String token) {
        TokenInfo tokenInfo = tokenStore.get(token);
        return tokenInfo != null ? tokenInfo.getUserId() : null;
    }
    
    public void incrementOperationCount(String token) {
        TokenInfo tokenInfo = tokenStore.get(token);
        if (tokenInfo != null) {
            tokenInfo.incrementOperationCount();
        }
    }
    
    public void invalidateToken(String token) {
        tokenStore.remove(token);
    }
    
    private boolean isTokenExpired(TokenInfo tokenInfo) {
        return System.currentTimeMillis() - tokenInfo.getCreatedAt() > EXPIRATION_TIME;
    }
    
    // Clase interna para almacenar información del token
    private static class TokenInfo {
        private String username;
        private Integer userId;
        private int operationCount;
        private long createdAt;
        
        public TokenInfo(String username, Integer userId) {
            this.username = username;
            this.userId = userId;
            this.operationCount = 0;
            this.createdAt = System.currentTimeMillis();
        }
        
        public String getUsername() { return username; }
        public Integer getUserId() { return userId; }
        public int getOperationCount() { return operationCount; }
        public long getCreatedAt() { return createdAt; }
        
        public void incrementOperationCount() {
            this.operationCount++;
        }
    }
}
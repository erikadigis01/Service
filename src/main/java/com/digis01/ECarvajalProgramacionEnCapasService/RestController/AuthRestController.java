/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.ECarvajalProgramacionEnCapasService.RestController;


import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;
import com.digis01.ECarvajalProgramacionEnCapasService.Service.CustomUserDetailsService;
import com.digis01.ECarvajalProgramacionEnCapasService.Service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody Map<String, String> credentials) {
        Result result = new Result();
        
        try {
            String username = credentials.get("username");
            String password = credentials.get("password");
            
            // Autenticar usuario
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Obtener UserDetails
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            
            // Obtener ID del usuario
            Integer userId = userDetailsService.getUserIdByUsername(username);
            
            // Generar token
            String token = tokenService.generateToken(username, userId);
            
            // Obtener rol
            String rol = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("");
            
            // Preparar respuesta
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("token", token);
            responseData.put("username", username);
            responseData.put("userId", userId);
            responseData.put("rol", rol);
            
            result.object = responseData;
            result.correct = true;
            result.status = 200;
            result.errorMessage = "Login exitoso";
            
        } catch (Exception e) {
            result.correct = false;
            result.status = 401;
            result.errorMessage = "Credenciales incorrectas";
        }
        
        return ResponseEntity.status(result.status).body(result);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Result> logout(@RequestHeader("Authorization") String token) {
        Result result = new Result();
        
        if (token != null && token.startsWith("Bearer ")) {
            String cleanToken = token.substring(7);
            tokenService.invalidateToken(cleanToken);
            
            result.correct = true;
            result.status = 200;
            result.errorMessage = "Logout exitoso";
        } else {
            result.correct = false;
            result.status = 400;
            result.errorMessage = "Token no proporcionado";
        }
        
        return ResponseEntity.status(result.status).body(result);
    }
}
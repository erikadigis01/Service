package com.digis01.ECarvajalProgramacionEnCapasService.RestController;

import com.digis01.ECarvajalProgramacionEnCapasService.Component.JwtUtil;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.JwtResponse;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtUtil jwtUtil;
    
    @Autowired
    UserDetails userDetails;
   
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UsuarioJPA loginRequest) {
        
        final String jwt = jwtUtil.generateToken(userDetails);
        
        
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getAuthorities()));
    }

}

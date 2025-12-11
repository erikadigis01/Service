/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.ECarvajalProgramacionEnCapasService.Service;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.VerificationToken;
import com.digis01.ECarvajalProgramacionEnCapasService.Repository.VerificationTokenRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public VerificationToken createVerificationToken(UsuarioJPA usuario) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUsuario(usuario);
        verificationToken.setExpiryDate(LocalDateTime.now().plusHours(24));
        return verificationTokenRepository.save(verificationToken);
    }
}

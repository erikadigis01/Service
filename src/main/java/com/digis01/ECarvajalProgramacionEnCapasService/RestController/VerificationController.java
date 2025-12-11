
package com.digis01.ECarvajalProgramacionEnCapasService.RestController;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.VerificationToken;
import com.digis01.ECarvajalProgramacionEnCapasService.Repository.UsuarioRepository;
import com.digis01.ECarvajalProgramacionEnCapasService.Repository.VerificationTokenRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {

    @Autowired
    private VerificationTokenRepository tokenRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/verify")
    public String verifyAccount(@RequestParam("token") String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null || verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return "Token inválido o expirado";
        }

        UsuarioJPA usuario = verificationToken.getUsuario();
        usuario.setIsVerified(1);
        usuarioRepository.save(usuario);

        return "Cuenta verificada exitosamente";
    }
}

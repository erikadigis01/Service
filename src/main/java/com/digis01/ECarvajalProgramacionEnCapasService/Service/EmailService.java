package com.digis01.ECarvajalProgramacionEnCapasService.Service;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(UsuarioJPA usuario, String token) throws MessagingException {
        String url = "http://localhost:8080/verify?token=" + token;

        String htmlBody = String.format("""
            <html>
              <body style="font-family: Arial, sans-serif; color: #333;">
                <h2 style="color:#2a9d8f;">¡Bienvenido a nuestra aplicación!</h2>
                <p>Gracias por registrarte, <b>%s</b>.</p>
                <p>Haz clic en el siguiente enlace para verificar tu cuenta:</p>
                <a href="%s" style="background:#2a9d8f; color:white; padding:10px 15px; text-decoration:none; border-radius:5px;">
                  Verificar cuenta
                </a>
                <hr>
                <p style="font-size:12px; color:#777;">Este es un correo automático, por favor no respondas.</p>
              </body>
            </html>
            """, usuario.getNombre(), url);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(usuario.getEmail());
        helper.setSubject("Verifica tu cuenta");
        helper.setText(htmlBody, true);

        mailSender.send(message);
    }
}

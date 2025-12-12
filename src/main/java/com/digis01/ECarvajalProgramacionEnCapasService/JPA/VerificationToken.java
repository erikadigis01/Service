package com.digis01.ECarvajalProgramacionEnCapasService.JPA;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import static jakarta.persistence.GenerationType.UUID;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;


@Table(name = "VERIFICATIONTOKEN")
@Entity
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtoken")
    private Long IdToken;
    
    @Column(name = "token")
    private String token;

    @OneToOne
    @JoinColumn(name = "idusuario")
    private UsuarioJPA Usuario;
    
    @Column(name = "expirydate")
    private LocalDateTime ExpiryDate;
    
    public VerificationToken(){}
    
    public VerificationToken(Long IdToken, String token, LocalDateTime ExpiryDate){
        this.IdToken = IdToken;
        this.token = token;
        this.ExpiryDate = ExpiryDate;
    }
    
    public void setIdToken(Long IdToken) {
        this.IdToken = IdToken;
    }
    public Long getIdToken(){
        return IdToken;
    }
    
    public void setToken(String token){
        this.token = token;
    }
    
    public String getToken(){
        return token;
    }
    
    public void setExpiryDate(LocalDateTime ExpiryDate){
        this.ExpiryDate = ExpiryDate;
    }
    public LocalDateTime getExpiryDate(){
        return ExpiryDate;
    }
    public void setUsuario(UsuarioJPA Usuario){
        this.Usuario = Usuario;
    }
    public UsuarioJPA getUsuario(){
        return Usuario;
    }
}

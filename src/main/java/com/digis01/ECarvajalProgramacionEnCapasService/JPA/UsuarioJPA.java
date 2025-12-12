package com.digis01.ECarvajalProgramacionEnCapasService.JPA;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USUARIO")
public class UsuarioJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int IdUsuario;
    
    @Column(name = "username", nullable = false, unique = true)
    private String userName;
    
    @Column(name = "nombre", nullable = false)
    private String Nombre;
    
    @Column(name = "apellidopaterno", nullable = false)
    private String ApellidoPaterno;
    
    @Column(name = "apellidomaterno", nullable = true)
    private String ApellidoMaterno;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String Password;
    
    @Column(name = "fechanacimiento", nullable =  false)
    private Date FechaNacimiento;
    
    @Column(name = "sexo", nullable = false)
    private char Sexo;
    
    @Column(name = "telefono", nullable = false)
    private String telefono;
    
    @Column(name = "celular", nullable = true)
    private String Celular;
    
    
    
    @Column(name = "curp", nullable = true)
    private String Curp;
    
    @Lob
    @Column(name = "imagen", nullable = true)
    private String Imagen;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idroll", nullable = true)
    public RollJPA Roll;
    
    
    @OneToMany(mappedBy = "UsuarioJPA", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<DireccionJPA> Direccion = new ArrayList<>();
    
    @Column(name = "status", nullable = true)
    private int Status;
    
    @Column(name = "isverified", nullable = true)
    private int IsVerified;
    
    //Constructores 
    public UsuarioJPA(){
    
    }
    
    public UsuarioJPA( int IdUsuario, String userName, String Nombre, String ApellidoPaterno, String ApellidoMaterno, String email, 

        String Password, Date FechaNacimiento, char Sexo, String telefono, String Celular, String Curp, String Imagen, int Status, int IsVerified){
        
        this.IdUsuario = IdUsuario;
        this.userName = userName;
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;      
        this.email = email;
        this.Password = Password;
        this.FechaNacimiento = FechaNacimiento;
        this.Sexo = Sexo;
        this.telefono = telefono;
        this.Celular = Celular;
        this.Curp = Curp;
        this.Imagen = Imagen;
        this.Status = Status;
        this.IsVerified = IsVerified;
      
    }
    //Getters y setters
    
    public void setIdUsuario (int IdUsuario){
        this.IdUsuario = IdUsuario;
    }
    public int getIdUsuario(){
        return IdUsuario;
    }
    
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    public String getNombre(){
        return Nombre;
    }
    
    public void setApellidoPaterno(String ApellidoPaterno){
        this.ApellidoPaterno = ApellidoPaterno;
    }
    public String getApellidoPaterno(){
        return ApellidoPaterno;
    }
    
    public void setApellidoMaterno(String ApellidoMaterno){
        this.ApellidoMaterno = ApellidoMaterno;
    }
    public String getApellidoMaterno(){
        return ApellidoMaterno;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setPassword(String Password){
        this.Password = Password;
    }
    public String getPassword(){
        return Password;
    }
    
    public void setFechaNacimiento(Date FechaNacimiento){
        this.FechaNacimiento = FechaNacimiento;
    }
    public Date getFechaNacimiento(){
        return FechaNacimiento;
    }
    
    public void setSexo(char Sexo){
        this.Sexo = Sexo;
    }
    public char getSexo(){
        return Sexo;
    }
    
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    public String getTelefono(){
        return telefono;
    }
    
    public void setCelular(String Celular){
        this.Celular = Celular;
    }
    public String getCelular(){
        return Celular;
    }
    
    public void setCurp(String Curp){
        this.Curp = Curp;
    }
    public String getCurp(){
        return Curp;
    }
    
    public void setImagen(String Imagen){
        
        this.Imagen = Imagen;
    
    }
    public String getImagen(){
    
        return Imagen;
        
    }
    
    public void setStatus(int Status){
        
        this.Status = Status;
    
    }
    public int getStatus(){
    
        return Status;
        
    }
    
    public void setIsVerified(int IsVerified){
        
        this.IsVerified = IsVerified;
    
    }
    public int getIsVerified(){
    
        return IsVerified;
        
    }
     
}

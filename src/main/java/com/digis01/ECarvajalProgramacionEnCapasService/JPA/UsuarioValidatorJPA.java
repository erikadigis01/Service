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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@Valid
@Entity
@Table(name = "USUARIO")
public class UsuarioValidatorJPA {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int IdUsuario;
    
    @Pattern(regexp="(?=.*[a-zA-ZñÑ])(?=.*[\\d])(?=.*[._-])^[a-zA-ZñÑ][a-zA-Z0-9_.-]+?$", message = "Su username debe contener Letras, numeros y caracteres especiales")
    @NotNull(message = "El campo no puede ser nulo")
    @NotBlank(message = "El campo debe contener datos")
    @Size(min = 5, max = 17, message = "Limite de letras excedido, entre 5 y 20")
    @Column(name = "username", nullable = false, unique = true)
    private String UserName;
    
    @NotNull(message = "El campo no puede ser nulo")
    @NotBlank(message = "El campo debe contener datos")
    @Size(min = 2, max = 20, message = "Limite de letras excedido, entre 2 y 20")
    @Pattern(regexp = "^[a-zA-ZñÑ]+?$", message = "Solo se permiten letras")
    @Column(name = "nombre", nullable = false)
    private String Nombre;
    
    @NotNull(message = "El campo no puede ser nulo")
    @NotBlank(message = "El campo debe contener datos")
    @Size(min = 2, max = 20, message = "Limite de letras excedido, entre 2 y 20")
    @Pattern(regexp = "^[a-zA-ZñÑ]+?$", message = "Solo se permiten letras")
    @Column(name = "apellidopaterno", nullable = false)
    private String ApellidoPaterno;
    
    @NotNull(message = "El campo no puede ser nulo")
    @NotBlank(message = "El campo debe contener datos")
    @Size(min = 2, max = 20, message = "Limite de letras excedido, entre 2 y 20")
    @Pattern(regexp = "^[a-zA-ZñÑ]+?$", message = "Solo se permiten letras")
    @Column(name = "apellidomaterno", nullable = true)
    private String ApellidoMaterno;
    
    @NotNull(message = "El campo no puede ser nulo")
    @NotBlank(message = "El campo debe contener datos")
    @Pattern(regexp = "(^[^\\s]+@+[a-zA-Z0-9_-]+[\\.]+[a-zA-Z0-9_-]+[\\.]+[a-zA-Z0-9_-]+$)", message = "Direccion de correo invalida")
    @Column(name = "email", nullable = false, unique = true)
    private String Email;
    
    @NotNull(message = "El campo no puede ser nulo")
    @NotBlank(message = "El campo debe contener datos")
    @Column(name = "password", nullable = false)
    private String Password;
    
    @NotNull(message = "El campo no puede ser nulo")
    @Past(message = "La fecha debe ser anterior al dia actual")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fechanacimiento", nullable =  false)
    private Date FechaNacimiento;
    
    @NotNull(message = "El campo no puede ser vacio")
    @Column(name = "sexo", nullable = false)
    private char Sexo;
    
    @NotNull(message = "El campo no puede ser nulo")
    @Pattern(regexp = "^\\+*([0-9]{2,3})*[\\s{1}]?[0-9]{10}$", message = "Numero de telefono no valido")
    @Column(name = "telefono", nullable = false)
    private String Telefono;
    
    @NotNull(message = "El campo no puede ser nulo")
    @Pattern(regexp = "^\\+*([0-9]{2,3})*[\\s{1}]?[0-9]{10}$", message = "Numero de celular no valido")
    @Column(name = "celular", nullable = true)
    private String Celular;
    
    
    @NotNull(message = "El campo no puede ser nulo")
    @NotBlank(message = "El campo debe contener datos")
    @Column(name = "curp", nullable = true)
    private String Curp;
    
    @Lob
    @Column(name = "imagen", nullable = true)
    private String Imagen;
    
    @NotNull(message = "El campo no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idroll", nullable = true)
    public RollJPA Roll;
    
    
    @OneToMany(mappedBy = "UsuarioJPA", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<DireccionJPA> Direccion = new ArrayList<>();
    
    @NotNull(message = "El campo no puede ser nulo")
    @Column(name = "status", nullable = true)
    private int Status;
    
    //Constructores 
    public UsuarioValidatorJPA(){
    
    }
    
    public UsuarioValidatorJPA( int IdUsuario, String UserName, String Nombre, String ApellidoPaterno, String ApellidoMaterno, String Email, 

        String Password, Date FechaNacimiento, char Sexo, String Telefono, String Celular, String Curp, String Imagen, int Status){
        
        this.IdUsuario = IdUsuario;
        this.UserName = UserName;
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;      
        this.Email = Email;
        this.Password = Password;
        this.FechaNacimiento = FechaNacimiento;
        this.Sexo = Sexo;
        this.Telefono = Telefono;
        this.Celular = Celular;
        this.Curp = Curp;
        this.Imagen = Imagen;
        this.Status = Status;
      
    }
    //Getters y setters
    
    public void setIdUsuario (int IdUsuario){
        this.IdUsuario = IdUsuario;
    }
    public int getIdUsuario(){
        return IdUsuario;
    }
    
    public void setUserName(String UserName){
        this.UserName = UserName;
    }
    public String getUserName(){
        return UserName;
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
    
    public void setEmail(String Email){
        this.Email = Email;
    }
    
    public String getEmail(){
        return Email;
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
    
    public void setTelefono(String Telefono){
        this.Telefono = Telefono;
    }
    public String getTelefono(){
        return Telefono;
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
    

}

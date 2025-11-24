package com.digis01.ECarvajalProgramacionEnCapasService.JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name  = "DIRECCION")
public class DireccionUsuarioAddUpdateDTO {
    
   
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddireccion", nullable = false)
    @JsonProperty("idDireccion")
    private int IdDireccion;
    
    @Column(name = "calle", nullable = false)
    @JsonProperty("calle")
    private String Calle;
    
    @Column(name = "numerointerior", nullable = true)
    @JsonProperty("numeroInterior")
    private String NumeroInterior;
    
    @Column(name = "numeroexterior", nullable = false)
    @JsonProperty("numeroExterior")
    private String NumeroExterior;
    
    
    @ManyToOne
    @JoinColumn(name = "idcolonia")
    @JsonProperty("Colonia")
    public ColoniaJPA Colonia;
    
    
    
    @ManyToOne()
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "idusuario")
    @JsonProperty("UsuarioJPA")
    
    public UsuarioJPA UsuarioJPA;
    
    //Constructor 
    public DireccionUsuarioAddUpdateDTO(){
    
    }
    
    public DireccionUsuarioAddUpdateDTO(int IdDireccion, String Calle, String NumeroInterior, String NumeroExterior){
        this.IdDireccion = IdDireccion;
        this.Calle = Calle;
        this.NumeroInterior = NumeroInterior;
        this.NumeroExterior = NumeroExterior;
    }
    //Getters y setters
     public void setIdDireccion(int IdDireccion){
        this.IdDireccion = IdDireccion;
    }
    public int getIdDireccion(){
        return IdDireccion;
    }
    
    public void setCalle(String Calle){
        this.Calle = Calle;
    }
    public String getCalle(){
        return Calle;
    }
    public void setNumeroInterior(String NumeroInterior){
        this.NumeroInterior = NumeroInterior;
    }
    public String getNumeroInterior(){
        return NumeroInterior;
    }
    
    public void setNumeroExterior(String NumeroExterior){
        this.NumeroExterior = NumeroExterior;
    }
    public String getNumeroExterior(){
        return NumeroExterior;
    }
    

}

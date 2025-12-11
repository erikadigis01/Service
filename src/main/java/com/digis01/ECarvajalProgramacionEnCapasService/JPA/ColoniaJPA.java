package com.digis01.ECarvajalProgramacionEnCapasService.JPA;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "COLONIA")
public class ColoniaJPA {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcolonia", nullable = false)
    @JsonProperty("idColonia")
    private int IdColonia;
    
    @Column(name = "nombre", nullable = false)
    @JsonProperty("nombre")
    private String Nombre;
    
    @Column(name = "codigopostal", nullable = false)
    @JsonProperty("codigoPostal")
    private String CodigoPostal;
    
    
    
    @ManyToOne
    @JoinColumn(name = "idmunicipio")
    @JsonProperty("Municipio")
    public MunicipioJPA Municipio;
    
    //Constructor 
    
    public ColoniaJPA(){
    
    }
    
    public ColoniaJPA(int IdColonia, String Nombre, String CodigoPostal){
        this.IdColonia = IdColonia;
        this.Nombre = Nombre;
        this.CodigoPostal = CodigoPostal;
    }
    //getters y setters 
    public void setIdColonia(int IdColonia){
        this.IdColonia = IdColonia;
    }
    public int getIdColonia(){
        return IdColonia;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    public String getNombre(){
        return Nombre;
    }
    
    public void setCodigoPostal(String CodigoPostal){
        this.CodigoPostal = CodigoPostal;
    }
    public String getCodigoPostal(){
        return CodigoPostal;
    }
    
    
}

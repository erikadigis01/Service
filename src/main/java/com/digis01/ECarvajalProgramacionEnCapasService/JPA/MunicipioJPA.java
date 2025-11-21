package com.digis01.ECarvajalProgramacionEnCapasService.JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "MUNICIPIO")
public class MunicipioJPA {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmunicipio", nullable = false)
    @JsonProperty("idMunicipio")
    private int IdMunicipio;
    
    @Column(name = "nombre", nullable = false)
    @JsonProperty("nombre")
    private String Nombre;
    
    
    
    @ManyToOne
    @JoinColumn(name = "idestado")
    @JsonProperty("Estado")
    public EstadoJPA Estado;

    public MunicipioJPA(){
    
    }
    
    public MunicipioJPA(int IdMunicipio, String Nombre){
    
        this.IdMunicipio = IdMunicipio;
        this.Nombre = Nombre;
    }
    
    public void setIdMunicipio(int IdMunicipio){
        this.IdMunicipio = IdMunicipio;
    }
    public int getIdMunicipio(){
        return IdMunicipio;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    public String getNombre(){
        return Nombre;
    }

    
}

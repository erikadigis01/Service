/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.ECarvajalProgramacionEnCapasService.Repository;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 @Repository
    public interface UsuarioRepository extends JpaRepository<UsuarioJPA, Long> {

    UsuarioJPA findByEmail(String email);

    boolean existsByEmail(String email);
    
    boolean existsByTelefono(String Telefono);
}
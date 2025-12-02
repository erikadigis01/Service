package com.digis01.ECarvajalProgramacionEnCapasService.DAO;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;
import org.springframework.data.jpa.repository.JpaRepository;


public interface  IUsuarioRepositoryDAO extends JpaRepository< UsuarioJPA , Integer> {   

    UsuarioJPA findByUserName(String userName);//metodo de consulta automatica

}
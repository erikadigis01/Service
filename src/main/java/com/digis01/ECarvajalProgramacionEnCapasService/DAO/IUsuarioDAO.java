package com.digis01.ECarvajalProgramacionEnCapasService.DAO;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;


public interface IUsuarioDAO {
    
    Result GetAll();
    Result Add(UsuarioJPA usuario);

}

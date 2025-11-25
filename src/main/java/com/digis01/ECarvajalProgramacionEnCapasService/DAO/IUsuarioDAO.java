package com.digis01.ECarvajalProgramacionEnCapasService.DAO;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;


public interface IUsuarioDAO {
    
    Result GetAll();
    Result Add(UsuarioJPA usuario);
    Result Update(UsuarioJPA usuario);
    Result Delete(int idUsuario);
    Result GetById(int idUsuario);
    Result UpdateImagen(String imagen, int idUsuario);
    Result GetAllDinamico(UsuarioJPA usuario);

}

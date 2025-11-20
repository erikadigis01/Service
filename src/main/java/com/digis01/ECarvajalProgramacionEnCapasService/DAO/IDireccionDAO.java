package com.digis01.ECarvajalProgramacionEnCapasService.DAO;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.DireccionJPA;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;


public interface IDireccionDAO {
    
    Result GetById(int idDireccion);
    Result Add(DireccionJPA direccion);
    Result Delete(int idDireccion);
    Result Update(DireccionJPA direccion);

}

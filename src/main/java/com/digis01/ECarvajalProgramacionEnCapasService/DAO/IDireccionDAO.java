package com.digis01.ECarvajalProgramacionEnCapasService.DAO;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.DireccionJPA;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.DireccionUsuarioAddUpdateDTO;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;


public interface IDireccionDAO {
    
    Result GetById(int idDireccion);
    Result Add(DireccionUsuarioAddUpdateDTO direccion);
    Result Delete(int idDireccion);
    Result Update(DireccionUsuarioAddUpdateDTO direccion);

}

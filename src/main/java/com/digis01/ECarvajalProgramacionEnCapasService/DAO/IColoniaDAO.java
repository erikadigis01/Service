package com.digis01.ECarvajalProgramacionEnCapasService.DAO;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;


public interface IColoniaDAO {
    
    Result GetByIdMunicipio(int idMunicipio);
    Result GetByCodigoPostal(String codigoPostal);

}

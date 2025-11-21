package com.digis01.ECarvajalProgramacionEnCapasService.DAO;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.MunicipioJPA;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class MunicipioDAOImplementation  implements IMunicipioDAO{
    
    @Autowired
    EntityManager entityManager;

    @Override
    public Result GetByIdEstado(int idEstado) {
        
        Result result = new Result();
        
        try {
            
            TypedQuery<MunicipioJPA> queryMunicipio = entityManager.createQuery("FROM MunicipioJPA WHERE Estado.IdEstado =:idEstado", MunicipioJPA.class);
            queryMunicipio.setParameter("idEstado", idEstado);
            
            List<MunicipioJPA> municipios = queryMunicipio.getResultList();
            
            if( municipios.size() > 0 ) {
            
                result.correct = true;
                result.objects = new ArrayList<>();
                
                result.objects = municipios.stream()
                                        .map(MunicipioJPA -> (Object) MunicipioJPA)
                                        .collect(Collectors.toList());
             
            
            } else {
                
                result.correct = false;
                result.errorMessage = "No se encontraron municipios con ese id";
            
            
            }
        
        
        } catch (Exception ex) {
        
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            
        }
        
        return result;
        
    }

}

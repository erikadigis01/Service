package com.digis01.ECarvajalProgramacionEnCapasService.DAO;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.EstadoJPA;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoDAOImplementation  implements IEstadoDAO{
    
    @Autowired
    EntityManager entityManager;

    @Override
    public Result GetByIdPais(int idPais) {
            
         Result result = new Result();
        
        try {
         
            TypedQuery<EstadoJPA> queryEstado = entityManager.createQuery("FROM EstadoJPA WHERE Pais.IdPais =:idPais", EstadoJPA.class);
            queryEstado.setParameter("idPais", idPais);
            
            List<EstadoJPA> estados = queryEstado.getResultList();
            
            if( !estados.isEmpty() ) {
            
                result.correct = true;
                result.objects = new ArrayList<>();
                
                result.objects = estados.stream()
                                        .map(EstadoJPA -> (Object) EstadoJPA)
                                        .collect(Collectors.toList());
             
            
            } 
        
        
        } catch (Exception ex) {
        
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            
        }
        
        return result;
        
    }

}

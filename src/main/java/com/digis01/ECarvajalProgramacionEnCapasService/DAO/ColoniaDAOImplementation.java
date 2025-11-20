package com.digis01.ECarvajalProgramacionEnCapasService.DAO;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.ColoniaJPA;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ColoniaDAOImplementation  implements IColoniaDAO{
    
    @Autowired
    EntityManager entityManager;

    @Override
    public Result GetByIdMunicipio(int idMunicipio) {
        
        
        Result result = new Result();
        
        
        try {
            
            TypedQuery<ColoniaJPA> queryColonia = entityManager.createQuery("FROM ColoniaJPA WHERE Municipio.IdMunicipio = :idMunicipio", ColoniaJPA.class);
            
            queryColonia.setParameter("idMunicipio", idMunicipio);
            
            List<ColoniaJPA> colonias = queryColonia.getResultList();
            
            if(!colonias.isEmpty()){
                result.objects = new ArrayList<>();
                result.objects = colonias;
                result.correct = true;
            } 
        
        
        } catch(Exception ex) {
        
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        
        
        }
        
        return result;
    }

    @Override
    public Result GetByCodigoPostal(String codigoPostal) {
        
        Result result = new Result();
        
        
        try {
            
            TypedQuery<ColoniaJPA> queryColonia = entityManager.createQuery("FROM ColoniaJPA WHERE CodigoPostal = :codigoPostal", ColoniaJPA.class);
            
            queryColonia.setParameter("codigoPostal", codigoPostal);
            
            List<ColoniaJPA> colonias = queryColonia.getResultList();
            
            if(!colonias.isEmpty()){
                result.objects = new ArrayList<>();
                result.objects = colonias;
                result.correct = true;
            } 
        
        
        } catch(Exception ex) {
        
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        
        
        }
        
        return result;
        
    }

}

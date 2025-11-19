package com.digis01.ECarvajalProgramacionEnCapasService.DAO;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImplementation implements IUsuarioDAO{

     @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result GetAll() {
        
        Result result = new Result();
        
        try {
            
             TypedQuery<UsuarioJPA> queryUsuario = entityManager.createQuery("FROM UsuarioJPA", UsuarioJPA.class);
            
             List<UsuarioJPA> usuariosJPA = queryUsuario.getResultList();
             
             result.objects = new ArrayList<>();
             
             result.objects = usuariosJPA;
             
             result.correct = true;
        
        
        
        } catch (Exception ex) {
        
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        
        }
        
        return result;
        
    }

}

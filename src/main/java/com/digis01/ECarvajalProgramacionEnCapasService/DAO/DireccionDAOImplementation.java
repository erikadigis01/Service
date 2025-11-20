package com.digis01.ECarvajalProgramacionEnCapasService.DAO;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.DireccionJPA;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DireccionDAOImplementation implements IDireccionDAO {
    
    @Autowired
    EntityManager entityManager;

    @Override
    public Result GetById(int idDireccion) {
        
        
        Result result = new Result();
        
        try{
            
            
            DireccionJPA direccionJPA = entityManager.find(DireccionJPA.class, idDireccion);
            
            if(direccionJPA != null){
                
                try {
                    
                    result.object = direccionJPA;
                    result.correct = true;
                
                } catch (PersistenceException ex) {
                    
                    result.correct = false;
                    result.errorMessage = ex.getLocalizedMessage();
                
                }
            }
        
        
        } catch (Exception ex) {
        
            result.correct =  false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
        
    }
    
    @Transactional
    @Override
    public Result Add(DireccionJPA direccion) {
        
        Result result = new Result();
        
        try {
        
            
            entityManager.persist(direccion);
            result.correct =  true;
            
        } catch (Exception ex) {
        
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            
        }
        
        return result;
        
    }
    
    @Transactional
    @Override
    public Result Delete(int idDireccion) {
        
        Result result = new Result();
        
        try {
            
            DireccionJPA direccionJPA = entityManager.find(DireccionJPA.class, idDireccion);
            
            
            if(direccionJPA != null){
                    
                try {
                    
                    entityManager.remove(direccionJPA);
                    
                    result.correct = true;
                    
                
                } catch (PersistenceException ex) {
                
                    result.correct = false;
                    
                    result.errorMessage = ex.getLocalizedMessage();
                    
                }
            
            } else {
            
                result.correct =  false;
                
                result.errorMessage = "Direccion no encontrada";
            }
        
        } catch (Exception ex) {
        
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            
        }
        
        return result;
    }
    
    @Transactional
    @Override
    public Result Update(DireccionJPA direccion) {
       Result result = new Result();
        
        try {
            
            
            DireccionJPA direccionfind = entityManager.find(DireccionJPA.class, direccion.getIdDireccion());
            
           
            if(direccionfind == null) {
            
                //se agrega
                Add(direccion);
            
            } else {
            
                //se actualiza
                
                entityManager.merge(direccion);
            
            }
        
        
        } catch (Exception ex) {
        
           result.correct =  false;
           result.errorMessage = ex.getLocalizedMessage();
           result.ex = ex;
        
        }
        
        return result;
    }

}

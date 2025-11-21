package com.digis01.ECarvajalProgramacionEnCapasService.DAO;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
             
             result.objects = usuariosJPA.stream()
                                        .map(UsuarioJPA -> (Object) UsuarioJPA)
                                        .collect(Collectors.toList());
             
             result.correct = true;
        
        
        
        } catch (Exception ex) {
        
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        
        }
        
        return result;
        
    }
    
    @Transactional
    @Override
    public Result Add(UsuarioJPA usuario) {
       Result result = new Result();
        
       try{
           
           usuario.Direccion.get(0).UsuarioJPA  = usuario;
           entityManager.persist(usuario);
        
        
           result.correct =  true;
         
       
       
       } catch (Exception ex) {
           
           result.correct =  false;
           result.errorMessage = ex.getLocalizedMessage();
           result.ex = ex;
           
       }
       
       return result;
    }
    
    @Transactional
    @Override
    public Result Update(UsuarioJPA usuario) {
        Result result = new Result();
        
        try {
        
            UsuarioJPA usuariofind = entityManager.find(UsuarioJPA.class, usuario.getIdUsuario());
            
            
            if(usuariofind == null) {
            
                //se agrega
                Add(usuario);
            
            } else {
            
                //se actualiza
                
                entityManager.merge(usuario);
            
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
    public Result Delete(int idUsuario) {
        
        Result result = new Result();
        
        try{
            
            
            UsuarioJPA usuarioJPA = entityManager.find(UsuarioJPA.class, idUsuario);
            
            if(usuarioJPA != null){
                
                try {
                    
                    entityManager.remove(usuarioJPA);
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
    public Result GetById(int idUsuario) {
        
       Result result = new Result();
        
        try{
            
            
            UsuarioJPA usuarioJPA = entityManager.find(UsuarioJPA.class, idUsuario);
            
            if(usuarioJPA != null){
                
                try {
                    
                    result.object = usuarioJPA;
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
    public Result UpdateImagen(String imagen, int idUsuario) {
        
        Result result = new Result();
        
        try {
        
            UsuarioJPA usuarioJPA = entityManager.find(UsuarioJPA.class, idUsuario);
            usuarioJPA.setImagen(imagen);
            
            try {
                
                entityManager.flush();
                result.correct = true;
            
            
            } catch (PersistenceException ex) {
            
                 result.correct = false;
                 result.errorMessage = ex.getLocalizedMessage();
            
            }
            
        
        } catch (Exception ex) {
        
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            
        }
        
        return result;
        
    }

    @Override
    public Result GetAllDinamico(String campo, String valor) {
       Result result = new Result();
       
       try {
           
           TypedQuery<UsuarioJPA> queryUsuario = entityManager.createQuery("FROM UsuarioJPA WHERE " + campo + " LIKE :valor", UsuarioJPA.class)
                    .setParameter("valor", "%" + valor + "%");
            
            //Resultado del JPA
            List<UsuarioJPA> usuariosJPA = queryUsuario.getResultList();
            
            result.objects = new ArrayList<>();
            
            result.objects = usuariosJPA.stream()
                                        .map(UsuarioJPA -> (Object) UsuarioJPA)
                                        .collect(Collectors.toList());
           
            result.correct = true;
       
       
       } catch (Exception ex) {
       
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
       
       }
       
       return result;
    }
}

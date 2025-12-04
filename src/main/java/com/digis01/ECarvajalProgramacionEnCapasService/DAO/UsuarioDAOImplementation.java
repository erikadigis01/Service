package com.digis01.ECarvajalProgramacionEnCapasService.DAO;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.DireccionJPA;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.RollJPA;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;
import com.digis01.ECarvajalProgramacionEnCapasService.Service.PasswordEncoderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImplementation implements IUsuarioDAO{

    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private PasswordEncoderService passwordEncoderService;
    
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
           //se agrega por defecto con 1
           String encryptedPassword = passwordEncoderService.encodePassword(usuario.getPassword());
           usuario.setPassword(encryptedPassword);
           usuario.setStatus(1);
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
//                usuario.Roll = new RollJPA();
//                usuario.Roll.setIdRoll(usuariofind.Roll.getIdRoll());
//                usuario.Roll.setNombreRoll(usuariofind.Roll.getNombreRoll());
                usuario.setStatus(usuariofind.getStatus());
                usuario.setImagen(usuariofind.getImagen());
                usuario.setPassword(usuariofind.getPassword());
                usuario.Direccion = new ArrayList<DireccionJPA>();
                usuario.Direccion = usuariofind.Direccion;
                
                entityManager.merge(usuario);
                result.correct = true;
                result.object = usuario;
            
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
            } else {
            
                result.correct = false;
            
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
            System.out.print(imagen);
            usuarioJPA.setImagen(imagen);
            
            try {
                
                entityManager.merge(usuarioJPA);
                result.correct = true;
                result.object = usuarioJPA;
            
            
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
    public Result GetAllDinamico(UsuarioJPA usuario) {
        Result result = new Result();

        try {
            String consulta = "SELECT u FROM UsuarioJPA u";
            List<String> condiciones = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();

            if (usuario.getNombre() != null && !usuario.getNombre().isEmpty()) {
                condiciones.add("LOWER(u.Nombre) LIKE LOWER(:nombre)");
                params.put("nombre", "%" + usuario.getNombre() + "%");
            }

            if (usuario.getApellidoPaterno() != null && !usuario.getApellidoPaterno().isEmpty()) {
                condiciones.add("LOWER(u.ApellidoPaterno) LIKE LOWER(:apaterno)");
                params.put("apaterno", "%" + usuario.getApellidoPaterno() + "%");
            }

            if (usuario.getApellidoMaterno() != null && !usuario.getApellidoMaterno().isEmpty()) {
                condiciones.add("LOWER(u.ApellidoMaterno) LIKE LOWER(:amaterno)");
                params.put("amaterno", "%" + usuario.getApellidoMaterno() + "%");
            }

            if (usuario.Roll != null && usuario.Roll.getNombreRoll() != null && !usuario.Roll.getNombreRoll().isEmpty()) {
                condiciones.add("LOWER(u.Roll.NombreRoll) LIKE LOWER(:roll)");
                params.put("roll", "%" + usuario.Roll.getNombreRoll() + "%");
            }

            if (usuario.getStatus() == 1 || usuario.getStatus() == 0) {
                condiciones.add("u.Status = :status");
                params.put("status", usuario.getStatus());
            }

            String queryString = consulta;
            if (!condiciones.isEmpty()) {
                queryString += " WHERE " + String.join(" AND ", condiciones);
            }

            TypedQuery<UsuarioJPA> queryUsuario = entityManager.createQuery(queryString, UsuarioJPA.class);
            params.forEach(queryUsuario::setParameter);

            List<UsuarioJPA> usuariosJPA = queryUsuario.getResultList();

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
    public Result UpdateStatus(UsuarioJPA usuario) {
        Result result = new Result();
        
        try {
        
            int status = usuario.getStatus();
            int idUsuario = usuario.getIdUsuario();

            UsuarioJPA usuarioBuscar = entityManager.find(UsuarioJPA.class,idUsuario);

            if(usuarioBuscar != null) {

                usuarioBuscar.setStatus(status);
                entityManager.merge(usuarioBuscar);
                result.correct = true;
                result.object = usuarioBuscar;

            }else {

                result.correct = false;
                result.errorMessage = "usuario no encontrado";
            }
        
        } catch(Exception ex) {
            
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        
        }
        
        return result;
    }
}

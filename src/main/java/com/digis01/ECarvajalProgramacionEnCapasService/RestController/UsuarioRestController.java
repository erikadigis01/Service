package com.digis01.ECarvajalProgramacionEnCapasService.RestController;

import com.digis01.ECarvajalProgramacionEnCapasService.DAO.UsuarioDAOImplementation;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class UsuarioRestController {
    
    @Autowired
    UsuarioDAOImplementation usuarioDAOImplementation;
    
    @GetMapping
    public ResponseEntity GetAll() {
    
        Result result = new Result();
        
        try {
            
            result = usuarioDAOImplementation.GetAll();
            result.correct = true;
            result.errorMessage = "Se obtuvieron usuarios";
            result.status = 200;
        
        
        
        } catch (Exception ex) {//definir error en el servidor
        
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        
        }
    
        return ResponseEntity.status(result.status).body(result);
    }
    
    @PostMapping 
    public ResponseEntity Add(@RequestBody  UsuarioJPA usuario) {
        
         Result result = new Result();
         
         try {
             
            result = usuarioDAOImplementation.Add(usuario);
            result.correct = true;
            result.errorMessage = "Se agrego un nuevo usuario correctamente";
            result.status = 201;
         
         
         } catch (Exception ex) {
         
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
         
         }
         
    
         return ResponseEntity.status(result.status).body(result);
      
    
    }
    
    @PutMapping("/update")
     public ResponseEntity Update(@RequestBody  UsuarioJPA usuario) {
         
         Result result = new Result();
         
        try {
             
            result = usuarioDAOImplementation.Update(usuario);
            result.correct = true;
            result.errorMessage = "Se actualizo el usuario correctamente";
            result.status = 202;
         
         
         } catch (Exception ex) {
         
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
         
         }

         return ResponseEntity.status(result.status).body(result);
     }
     
     @DeleteMapping("/{idUsuario}")
     public ResponseEntity Delete(@PathVariable("idUsuario") int idUsuario) {
         
         Result result = new Result();
         
         try {
             
            result = usuarioDAOImplementation.Delete(idUsuario);
            result.correct = true;
            result.errorMessage = "Se elimino el usuario correctamente";
            result.status = 204;
         
         
         } catch (Exception ex) {
         
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
         
         }
         
         return ResponseEntity.status(result.status).body(result);
     
     }
     
     @GetMapping("/{idUsuario}")
     public ResponseEntity GetById(@PathVariable("idUsuario") int idUsuario) {
     
         Result result = new Result();
         
         try {
             
            result = usuarioDAOImplementation.GetById(idUsuario);
            
            if(result.object != null){
                
                result.errorMessage = "Se encontro un usuario con ese Id";
                result.status = 200;
                
            } else {
            
                result.correct = false;
                result.status = 404;
                result.errorMessage = "No se encontro un usuario";
            
            }
         
         } catch (Exception ex) {
         
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
         
         }
         
         return ResponseEntity.status(result.status).body(result);
     
     }
     
     @PatchMapping("/{idUsuario}")
     public ResponseEntity UpdateImagen(@RequestBody HashMap<String, Object> imagen,
             @PathVariable("idUsuario") int idUsuario) {
     
         Result result = new Result();
         
         try {
            String imagenUpdate = (String) imagen.get("imagen"); 
            result = usuarioDAOImplementation.UpdateImagen(imagenUpdate, idUsuario);
            result.correct = true;
            result.errorMessage = "Se actualizo correctamente la imagen";
            result.status = 200;
         
         
         } catch (Exception ex) {
         
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 404;
         
         }
         
         return ResponseEntity.status(result.status).body(result);
     
     }
     
    @GetMapping("/dinamico")
    public ResponseEntity GetAllDinamico(@RequestBody  UsuarioJPA usuario) {
    
        Result result = new Result();
        
        try {
            
            result = usuarioDAOImplementation.GetAllDinamico(usuario);
            result.correct = true;
            
            
            result.errorMessage = "Se obtuvieron usuarios";
            result.status = 200;
        
        
        
        } catch (Exception ex) {//definir error en el servidor
        
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        
        }
    
        return ResponseEntity.status(result.status).body(result);
    }
    

}

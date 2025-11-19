package com.digis01.ECarvajalProgramacionEnCapasService.RestController;

import com.digis01.ECarvajalProgramacionEnCapasService.DAO.UsuarioDAOImplementation;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

}

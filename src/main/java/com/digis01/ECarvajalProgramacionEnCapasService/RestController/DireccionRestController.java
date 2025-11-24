package com.digis01.ECarvajalProgramacionEnCapasService.RestController;

import com.digis01.ECarvajalProgramacionEnCapasService.DAO.DireccionDAOImplementation;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.DireccionJPA;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.DireccionUsuarioAddUpdateDTO;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("direccion")
public class DireccionRestController {
    
    @Autowired
    DireccionDAOImplementation direccionDAOImplementation;
    
    @GetMapping("/{idDireccion}")
     public ResponseEntity GetById(@PathVariable("idDireccion") int idDireccion) {
     
         Result result = new Result();
         
         try {
             
            result = direccionDAOImplementation.GetById(idDireccion);
            
            if(result.correct == true){
                result.correct = true;
                result.errorMessage = "Se encontro una direccion con ese Id";
                result.status = 200;
            } else {
                
                result.correct = false;
                result.errorMessage = "Se  no encontro una direccion con ese Id";
                result.status = 404;
            
            }
         
         
         } catch (Exception ex) {
         
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 404;
         
         }
         
         return ResponseEntity.status(result.status).body(result);
     
     }
     
     
    @PostMapping
    public ResponseEntity Add(@RequestBody DireccionUsuarioAddUpdateDTO direccion) {
        
         Result result = new Result();
         
         try {
            
            
            result = direccionDAOImplementation.Add(direccion);
            result.correct = true;
            result.errorMessage = "Se agrego una nueva direccion correctamente";
            result.status = 201;
            result.object = direccion;
         
         
         } catch (Exception ex) {
         
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
         
         }
         
    
         return ResponseEntity.status(result.status).body(result);
      
    
    }
    
    @DeleteMapping("/{idDireccion}")
    public ResponseEntity Delete(@PathVariable("idDireccion") int idDireccion) {

        Result result = new Result();
        
        try {
             
            result = direccionDAOImplementation.Delete(idDireccion);
            result.correct = true;
            result.errorMessage = "Se elimino la direccion correctamente";
            result.status = 200;
         
         
         } catch (Exception ex) {
         
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
         
         }
       
        return ResponseEntity.status(result.status).body(result);
    }
    
    @PutMapping("/update")
     public ResponseEntity Update(@RequestBody  DireccionUsuarioAddUpdateDTO direccion) {
         
         Result result = new Result();
         
        try {
            
            result = direccionDAOImplementation.Update(direccion);
            result.correct = true;
            result.errorMessage = "Se actualizo la direccion correctamente";
            result.status = 202;
            result.object = direccion;
         
         
         } catch (Exception ex) {
         
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
         
         }

         return ResponseEntity.status(result.status).body(result);
     }

}

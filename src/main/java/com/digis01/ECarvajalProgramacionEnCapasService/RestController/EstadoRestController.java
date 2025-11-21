package com.digis01.ECarvajalProgramacionEnCapasService.RestController;

import com.digis01.ECarvajalProgramacionEnCapasService.DAO.EstadoDAOImplementation;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("estado")
public class EstadoRestController {
    
    @Autowired
    EstadoDAOImplementation  estadoDAOImplementation;
    
    
    @GetMapping("/pais/{idPais}")
    public ResponseEntity GetByIdPais(@PathVariable("idPais") int idPais) {
    
        Result result = new Result();
        
        try {
             
            result = estadoDAOImplementation.GetByIdPais(idPais);
            result.correct = true;
            
            if(result.objects != null){

                result.errorMessage = "Se encontraron estados con ese id de pais";
                result.status = 200;

            } else {

                result.errorMessage = "No hay estados con ese id de pais";
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

}

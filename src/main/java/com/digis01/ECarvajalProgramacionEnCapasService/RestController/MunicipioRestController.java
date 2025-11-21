package com.digis01.ECarvajalProgramacionEnCapasService.RestController;

import com.digis01.ECarvajalProgramacionEnCapasService.DAO.MunicipioDAOImplementation;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("municipio")
public class MunicipioRestController {
    
    @Autowired
    MunicipioDAOImplementation municipioDAOImplementation;
    
    @GetMapping("/estado/{idEstado}")
    public ResponseEntity GetByIdEstado(@PathVariable("idEstado") int idEstado) {
    
        Result result = new Result();
        
        try {
             
            result = municipioDAOImplementation.GetByIdEstado(idEstado);
            result.correct = true;
            
            if(result.objects != null){

                result.errorMessage = "Se encontraron municipios con ese id de estado";
                result.status = 200;

            } else {

                result.errorMessage = "No hay municipios con ese id de estado";
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

package com.digis01.ECarvajalProgramacionEnCapasService.RestController;

import com.digis01.ECarvajalProgramacionEnCapasService.DAO.ColoniaDAOImplementation;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("colonia")
@RestController
public class ColoniaRestController {
    
    @Autowired
    ColoniaDAOImplementation coloniaDAOImplementation;
    
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/municipio/{idMunicipio}")
    public ResponseEntity GetByIdMunicipio(@PathVariable("idMunicipio") int idMunicipio) {
     
         Result result = new Result();
         
         try {
             
            result = coloniaDAOImplementation.GetByIdMunicipio(idMunicipio);
            result.correct = true;
            
            if(result.objects != null){

                result.errorMessage = "Se encontraron colonias en ese municipio";
                result.status = 200;

            } else {

                result.errorMessage = "No hay colonias en ese municipio";
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
    
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/codigoPostal/{codigoPostal}")
    public ResponseEntity GetByIdCodigoPostal(@PathVariable("codigoPostal") String codigoPostal) {
     
         Result result = new Result();
         
         try {
             
            result = coloniaDAOImplementation.GetByCodigoPostal(codigoPostal);
            result.correct = true;
            
            if(result.objects != null){

                result.errorMessage = "Se encontraron colonias en ese codigo postal";
                result.status = 200;

            } else {

                result.errorMessage = "No hay colonias con ese codigo postal";
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

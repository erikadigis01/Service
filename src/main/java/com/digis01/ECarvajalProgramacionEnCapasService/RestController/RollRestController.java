package com.digis01.ECarvajalProgramacionEnCapasService.RestController;

import com.digis01.ECarvajalProgramacionEnCapasService.DAO.RollDAOImplementation;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roll")
public class RollRestController {
    
    @Autowired
    RollDAOImplementation rollDAOImplementation;
    
    
    @GetMapping
    public ResponseEntity GetAll(){
    
         Result result = new Result();
        
        try {
            
            result = rollDAOImplementation.GetAll();
            result.correct = true;
            result.errorMessage = "Se obtuvieron rolles";
            result.status = 200;
        
        
        
        } catch (Exception ex) {//definir error en el servidor
        
            result.correct = false;
            result.errorMessage = "no se obtuvieron rolles";
            result.ex = ex;
            result.status = 500;
        
        }
    
        return ResponseEntity.status(result.status).body(result);
    
    }
    

}

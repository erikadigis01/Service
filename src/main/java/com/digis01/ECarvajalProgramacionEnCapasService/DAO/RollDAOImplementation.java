package com.digis01.ECarvajalProgramacionEnCapasService.DAO;

import com.digis01.ECarvajalProgramacionEnCapasService.JPA.Result;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.RollJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RollDAOImplementation  implements IRollDAO{

    @Autowired
    EntityManager entityManager;
    
    @Override
    public Result GetAll() {
        
        Result result = new Result();
        
        try {
            
             TypedQuery<RollJPA> queryRoll = entityManager.createQuery("FROM RollJPA", RollJPA.class);
            
             List<RollJPA> rollesJPA = queryRoll.getResultList();
             
             result.objects = new ArrayList<>();
             
             result.objects = rollesJPA.stream()
                                        .map(RollJPA -> (Object) RollJPA)
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

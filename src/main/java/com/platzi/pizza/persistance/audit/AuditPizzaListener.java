package com.platzi.pizza.persistance.audit;

import com.platzi.pizza.persistance.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

import java.sql.SQLOutput;

public class AuditPizzaListener {
    private PizzaEntity currentValue;

    @PostLoad
    public void postLoad(PizzaEntity entity){
        System.out.println("POST LOAD");
        this.currentValue = SerializationUtils.clone(entity);
    }
    @PostPersist//Cuando sea nueva pizza
    @PostUpdate //Cuando sea pizza existente
    public void onPostPersist(PizzaEntity entity){
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: "+ this.currentValue);/*.toString();*/
        System.out.println("NEW VALUE: "+ entity.toString());
    };

    @PreRemove //Este metodo se ejecutará antes de hacer el proceso de eliminación en la base de datos
    public void onPreDelete(PizzaEntity entity){
        System.out.println(entity.toString());
    }
}

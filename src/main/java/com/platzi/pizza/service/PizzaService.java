package com.platzi.pizza.service;

import com.platzi.pizza.persistance.entity.PizzaEntity;
import com.platzi.pizza.persistance.repository.PizzaRepository;
import com.platzi.pizza.web.controller.PizzaController;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.beans.BeanProperty;
import java.util.List;

@Service
public class PizzaService {
    //Para crear consultas dentro de nuestro servicio
    private final PizzaRepository pizzaRepository;


    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository ;
    }

    public List<PizzaEntity> getAll()
    {
        return this.pizzaRepository.findAll();
    }

    public List<PizzaEntity> getAvailable(){
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity get(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public  List<PizzaEntity>  getWith(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public PizzaEntity save(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }

    public boolean exist(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }

    public void delete(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }
}

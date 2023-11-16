package com.platzi.pizza.persistance.repository;

import com.platzi.pizza.persistance.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity>  findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
}

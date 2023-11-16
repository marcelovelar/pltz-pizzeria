package com.platzi.pizza.persistance.repository;

import com.platzi.pizza.persistance.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

}

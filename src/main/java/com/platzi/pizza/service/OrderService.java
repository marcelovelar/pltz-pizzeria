package com.platzi.pizza.service;

import com.platzi.pizza.persistance.entity.OrderEntity;
import com.platzi.pizza.persistance.projection.OrderSummary;
import com.platzi.pizza.persistance.repository.OrderRepository;
import com.platzi.pizza.service.dto.RandomOrderDto;
import jakarta.persistence.criteria.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.ls.LSException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private static final String DELIVERY =  "D";
    private static final String CARRYOUT =  "C";
    private static final String ON_SITE =  "S";
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll(){
        List<OrderEntity> orders = this.orderRepository.findAll();
        orders.forEach(o -> System.out.println(o.getCustomer().getName()));
        return orders;
    }
     public List<OrderEntity> getTodayOrders(){
        //Que se carge hora y minuto 0 a la fecha actual
         LocalDateTime today = LocalDate.now().atTime(0,0);
         return this.orderRepository.findByDateAfter(today);
     }

     public List<OrderEntity> getOutsideOrders(){
        List<String> methods = Arrays.asList(DELIVERY,CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
     }

     public List<OrderEntity> getCustomerOrders(String idCustomer){
        return this.orderRepository.findCustomersOrders(idCustomer);
     }

     public OrderSummary getSummary(int orderId){
        return this.orderRepository.findSummary(orderId);
     }

     /*@Transactional
     public boolean saveRandomOrder(RandomOrderDto randomOrderDto){
        return this.orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
     }*/

    @Transactional
    @Modifying(clearAutomatically = true)
    public Boolean saveRandomOrder(RandomOrderDto randomOrderDto) {
        return this.orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
    }

}

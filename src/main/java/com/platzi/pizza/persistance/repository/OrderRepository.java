package com.platzi.pizza.persistance.repository;

import com.platzi.pizza.persistance.entity.OrderEntity;
import com.platzi.pizza.persistance.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findByDateAfter(LocalDateTime date);
    List<OrderEntity> findAllByMethodIn(List<String> methods);

    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomersOrders(@Param("id")String idCustomer);

    @Query(value =
            "SELECT po.id_order AS idOrder, cu.name AS customerName, po.date AS orderDate, " +
            "       po.total AS orderTotal, STRING_AGG(PI.name, ', ') AS pizzaNames " +
            "FROM pizza_order po " +
            "   INNER JOIN customer cu ON po.id_customer = cu.id_customer " +
            "   INNER JOIN order_item oi ON po.id_order = oi.id_order " +
            "   INNER JOIN pizza PI ON oi.id_pizza = PI.id_pizza " +
            "WHERE po.id_order = :orderId " +
            "GROUP BY po.id_order, cu.id_customer, po.date, po.total ", nativeQuery = true)
    OrderSummary findSummary(@Param("orderId")int orderId);

    @Procedure(value = "take_random_pizza_order", outputParameterName = "order_taken")
    boolean saveRandomOrder(@Param("id_customer") String idCustomer,@Param("method") String method);

/*    @Procedure(value = "take_random_pizza_order")
    Boolean saveRandomOrder(@Param("id_customer") String idCustomer, @Param("method") String method);*/

}

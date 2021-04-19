package com.Polsoftex.ZdroweJedzenieOrders.repositories;

import com.Polsoftex.ZdroweJedzenieOrders.model.OrderDAO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrdersRepository extends CrudRepository<OrderDAO, UUID> {
	
    List<OrderDAO> getOrdersByUserId(UUID userId);
    
    @Query("FROM order o WHERE o.id = ?1 AND o.state != 'cart'")
    OrderDAO findOrderById(UUID id);
    
    @Query("FROM order o WHERE o.id = ?1 AND o.state != 'cart'")
    Iterable<OrderDAO> findAllUserOrders(UUID userId);

    @Query("FROM order o WHERE o.userId = ?1 AND o.state = 'cart'")
    OrderDAO findCart(UUID userId);

    @Query("FROM order o WHERE o.userId = ?1 AND o.state = 'cart'")
    OrderDAO findCartByUserId(UUID userId);

    @Query("SELECT id FROM order o WHERE o.userId = ?1 AND o.state = 'cart'")
    UUID findCartIdByUserId(UUID userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM order WHERE userId = ?1 AND state = 'cart'")
    void deleteCart(UUID userId);
}

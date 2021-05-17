package com.Polsoftex.ZdroweJedzenieOrders.repositories;

import com.Polsoftex.ZdroweJedzenieOrders.model.OrderedProductDAO;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderedProductId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface OrderedProductsRepository extends CrudRepository<OrderedProductDAO, OrderedProductId> {

    List<OrderedProductDAO> findAllByOrderId(UUID orderId);

    @Transactional
    @Modifying
    void deleteAllByOrderId(UUID orderId);

    @Query("FROM ordered_product o WHERE o.orderId = ?1 AND o.productId = ?2")
    OrderedProductDAO getOrderedProduct(UUID orderId, UUID productId);

    @Transactional
    @Modifying
    @Query("UPDATE ordered_product o SET o.quantity = ?3 WHERE o.orderId = ?1 AND o.productId = ?2")
    void modifyProductQuantity(UUID orderId, UUID productId, int quantity);

    @Transactional
    @Modifying
    @Query("DELETE FROM ordered_product o WHERE o.orderId = ?1 AND o.productId = ?2")
    void deleteProductFromCart(UUID orderId, UUID productId);
}

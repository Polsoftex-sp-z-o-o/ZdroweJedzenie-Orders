package com.Polsoftex.ZdroweJedzenieOrders.repositories;

import com.Polsoftex.ZdroweJedzenieOrders.model.OrderedProductDAO;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderedProductId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface OrderedProductsRepository extends CrudRepository<OrderedProductDAO, OrderedProductId> {

    List<OrderedProductDAO> findAllByOrderId(UUID orderId);

    @Transactional
    @Modifying
    void deleteAllByOrderId(UUID orderId);
}
package com.Polsoftex.ZdroweJedzenieOrders.controllers;

import com.Polsoftex.ZdroweJedzenieOrders.feignClients.ProductsClient;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderDTO;
import com.Polsoftex.ZdroweJedzenieOrders.repositories.OrderedProductsRepository;
import com.Polsoftex.ZdroweJedzenieOrders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    //TODO This should be drawn from token
    private UUID userId = new UUID(1, 1);

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderedProductsRepository orderedProductsRepository;
    @Autowired
    private ProductsClient productsClient;

    @GetMapping("")
    List<OrderDTO> getOrders() {
        // TODO
        return new ArrayList<>();
    }

    @PostMapping("")
    OrderDTO createOrder() {
        // TODO
        return new OrderDTO();
    }

    @GetMapping("/{id}")
    OrderDTO getOrder(@PathVariable String id) {
        // TODO
        return new OrderDTO();
    }

    @PutMapping("/{id}")
    void updateOrder(@PathVariable String id, @RequestBody OrderDTO orderDTO) {
        // TODO
    }

    @DeleteMapping("/{id}")
    void deleteOrder(@PathVariable String id) {
        // TODO
    }
}

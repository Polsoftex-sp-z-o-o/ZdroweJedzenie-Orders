package com.Polsoftex.ZdroweJedzenieOrders.controllers;

import com.Polsoftex.ZdroweJedzenieOrders.feignClients.ProductsClient;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderDTO;
import com.Polsoftex.ZdroweJedzenieOrders.repositories.OrderedProductsRepository;
import com.Polsoftex.ZdroweJedzenieOrders.repositories.OrdersRepository;
import com.Polsoftex.ZdroweJedzenieOrders.services.CartService;
import com.Polsoftex.ZdroweJedzenieOrders.services.OrdersService;

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
    private OrdersService ordersService;

    @GetMapping("")
    List<OrderDTO> getOrders() {
        return ordersService.getOrders();
    }

    @PostMapping("")
    OrderDTO createOrder() {
        return ordersService.createOrder(userId);
    }

    @GetMapping("/{id}")
    OrderDTO getOrder(@PathVariable String id) {
        return ordersService.getOrderById(id);
    }

    @PutMapping("/{id}")
    void updateOrder(@PathVariable String id, @RequestBody OrderDTO orderDTO) {
        // TODO
    	ordersService.updateOrder(id, orderDTO);
    }

    @DeleteMapping("/{id}")
    void deleteOrder(@PathVariable String id) {
    	ordersService.deleteOrder(id);
    }
}

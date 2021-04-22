package com.Polsoftex.ZdroweJedzenieOrders.controllers;

import com.Polsoftex.ZdroweJedzenieOrders.model.OrderDTO;
import com.Polsoftex.ZdroweJedzenieOrders.services.OrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("")
    List<OrderDTO> getOrders(@RequestParam("userid") UUID userId) {
        return ordersService.getOrders(userId);
    }

    @PostMapping("")
    OrderDTO createOrder(@RequestParam("userid") UUID userId) {
        return ordersService.createOrder(userId);
    }

    @GetMapping("/{id}")
    OrderDTO getOrder(@PathVariable String id) {
        return ordersService.getOrderById(id);
    }

    @PutMapping("/{id}")
    void updateOrder(@PathVariable String id, @RequestBody OrderDTO orderDTO) {
    	ordersService.updateOrder(id, orderDTO);
    }

    @DeleteMapping("/{id}")
    void deleteOrder(@PathVariable String id) {
    	ordersService.deleteOrder(id);
    }
}

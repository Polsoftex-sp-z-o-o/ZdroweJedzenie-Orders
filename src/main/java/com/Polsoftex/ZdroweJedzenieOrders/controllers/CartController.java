package com.Polsoftex.ZdroweJedzenieOrders.controllers;

import com.Polsoftex.ZdroweJedzenieOrders.model.OrderedProductDTO;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderDTO;
import com.Polsoftex.ZdroweJedzenieOrders.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("")
    OrderDTO getCart(@RequestParam("userid") UUID userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("")
    OrderDTO addItemToCart(@RequestParam("userid") UUID userId, @RequestBody OrderedProductDTO orderedProductDTO) {
        return cartService.addProductToCart(userId, orderedProductDTO);
    }

    @PutMapping("")
    void updateCart(@RequestParam("userid") UUID userId, @RequestBody OrderDTO orderDTO) {
        cartService.updateCart(userId, orderDTO);
    }

    @DeleteMapping("")
    void clearCart(@RequestParam("userid") UUID userId) {
        cartService.deleteCart(userId);
    }
}

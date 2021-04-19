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

    //TODO This should be drawn from token
    private UUID userId = UUID.fromString("00000000-0000-0001-0000-000000000001");
    @Autowired
    private CartService cartService;

    @GetMapping("")
    OrderDTO getCart() {
        return cartService.getCart(userId);
    }

    @PostMapping("")
    OrderDTO addItemToCart(@RequestBody OrderedProductDTO orderedProductDTO) {
        return cartService.addProductToCart(userId, orderedProductDTO);
    }

    @PutMapping("")
    void updateCart(@RequestBody OrderDTO orderDTO) {
        cartService.updateCart(userId, orderDTO);
    }

    @DeleteMapping("")
    void clearCart() {
        cartService.deleteCart(userId);
    }
}

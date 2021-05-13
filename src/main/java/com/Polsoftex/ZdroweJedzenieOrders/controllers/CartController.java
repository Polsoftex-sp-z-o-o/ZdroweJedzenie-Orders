package com.Polsoftex.ZdroweJedzenieOrders.controllers;

import com.Polsoftex.ZdroweJedzenieOrders.exceptions.CartNotFoundException;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderedProductDTO;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderDTO;
import com.Polsoftex.ZdroweJedzenieOrders.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "" , produces = MediaType.APPLICATION_JSON_VALUE)
    OrderDTO getCart(@RequestParam("userid") UUID userId) throws CartNotFoundException {
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

    @DeleteMapping("/{productId}")
    void deleteItemFromCart(@PathVariable UUID productId, @RequestParam("userid") UUID userId) {
        cartService.deleteProductFromCart(userId, productId);
    }
}

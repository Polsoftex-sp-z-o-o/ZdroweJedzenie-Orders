package com.Polsoftex.ZdroweJedzenieOrders.services;

import com.Polsoftex.ZdroweJedzenieOrders.exceptions.CartNotFoundException;
import com.Polsoftex.ZdroweJedzenieOrders.feignClients.ProductsClient;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderDAO;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderDTO;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderedProductDAO;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderedProductDTO;
import com.Polsoftex.ZdroweJedzenieOrders.repositories.OrderedProductsRepository;
import com.Polsoftex.ZdroweJedzenieOrders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {
    //TODO: Transactions

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderedProductsRepository orderedProductsRepository;
    @Autowired
    private ProductsClient productsClient;

    public OrderDTO getCart(UUID userId) throws CartNotFoundException {
        OrderDAO cartDAO = ordersRepository.findCart(userId);
        if (cartDAO == null) {
            throw new CartNotFoundException();
        }
        return prepareOrderDTO(cartDAO);
    }

    public OrderDTO addProductToCart(UUID userId, OrderedProductDTO orderedProductDTO) {
        OrderDAO orderDAO = ordersRepository.findCart(userId);
        if (orderDAO == null) {
            // TODO: Builder?
            orderDAO = new OrderDAO();
            orderDAO.setUserId(userId);
            orderDAO.setState("cart");
            orderDAO = ordersRepository.save(orderDAO);
        }
        OrderedProductDAO newlyOrderedProductDAO = new OrderedProductDAO(orderDAO.getId(), orderedProductDTO.getProductId(), orderedProductDTO.getQuantity());
        newlyOrderedProductDAO = orderedProductsRepository.save(newlyOrderedProductDAO);
        return prepareOrderDTO(orderDAO);
    }

    public void updateCart(UUID userId, OrderDTO orderDTO) {
        OrderDAO orderDAO = ordersRepository.findCartByUserId(userId);
        orderedProductsRepository.deleteAllByOrderId(orderDAO.getId());
        List<OrderedProductDAO> orderedProductDAOs = new ArrayList<>();
        for (OrderedProductDTO orderedProductDTO : orderDTO.getOrderedProductDTOs()) {
            orderedProductDAOs.add(new OrderedProductDAO(orderDAO.getId(), orderedProductDTO.getProductId(), orderedProductDTO.getQuantity()));
        }
        orderedProductsRepository.saveAll(orderedProductDAOs);
    }

    public void deleteCart(UUID userId) {
        ordersRepository.deleteCart(userId);
    }

    public void deleteProductFromCart(UUID userId, UUID productId) {
        OrderDAO orderDAO = ordersRepository.findCartByUserId(userId);
        orderedProductsRepository.deleteProductFromCart(orderDAO.getId(), productId);
    }

    private OrderDTO prepareOrderDTO(OrderDAO orderDAO) {
        List<OrderedProductDAO> orderedProductDAOs = orderedProductsRepository.findAllByOrderId(orderDAO.getId());
        return new OrderDTO(orderDAO, orderedProductDAOs);
    }
}

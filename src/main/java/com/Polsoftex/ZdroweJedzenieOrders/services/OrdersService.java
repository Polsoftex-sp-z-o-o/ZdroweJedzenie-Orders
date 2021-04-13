package com.Polsoftex.ZdroweJedzenieOrders.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.Polsoftex.ZdroweJedzenieOrders.feignClients.ProductsClient;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderDAO;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderDTO;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderedProductDAO;
import com.Polsoftex.ZdroweJedzenieOrders.repositories.OrderedProductsRepository;
import com.Polsoftex.ZdroweJedzenieOrders.repositories.OrdersRepository;

public class OrdersService {
    //TODO: All business logic related to handling data in response to REST requests.
	
	@Autowired
	private OrdersRepository ordersRepository;
    @Autowired
    private OrderedProductsRepository orderedProductsRepository;
    @Autowired
    private ProductsClient productsClient;
    
    public List<OrderDTO> getOrders() {
    	List<OrderDTO> orders = new ArrayList<>();
        Iterable<OrderDAO> orderDAOs = ordersRepository.findAll();
        for(OrderDAO order: orderDAOs) {
        	orders.add(prepareOrderDTO(order));
        }
        return orders;
    }
    
    public OrderDTO getOrderById(String id) {
    	UUID orderId = UUID.fromString(id);
    	OrderDAO order = ordersRepository.findById(orderId).get();
    	return prepareOrderDTO(order);
    }
    
    public void deleteOrder(String id) {
    	UUID orderId = UUID.fromString(id);
    	ordersRepository.deleteById(orderId);
    }
    
    private OrderDTO prepareOrderDTO(OrderDAO orderDAO) {
        List<OrderedProductDAO> orderedProductDAOs = orderedProductsRepository.findAllByOrderId(orderDAO.getId());
        return new OrderDTO(orderDAO, orderedProductDAOs);
    }
}

package com.Polsoftex.ZdroweJedzenieOrders.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Polsoftex.ZdroweJedzenieOrders.feignClients.ProductsClient;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderDAO;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderDTO;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderedProductDAO;
import com.Polsoftex.ZdroweJedzenieOrders.model.OrderedProductDTO;
import com.Polsoftex.ZdroweJedzenieOrders.repositories.OrderedProductsRepository;
import com.Polsoftex.ZdroweJedzenieOrders.repositories.OrdersRepository;

@Service
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
    
    public OrderDTO createOrder(UUID userId) {
    	OrderDAO order = ordersRepository.findCart(userId);
    	order = ordersRepository.save(order);
    	return prepareOrderDTO(order);
    }
    
    public void updateOrder(String id, OrderDTO orderDTO) {
    	UUID orderId = UUID.fromString(id);
    	OrderDAO updOrder = ordersRepository.findById(orderId).get();
    	updOrder.setUserId(orderDTO.getUserId());
    	updOrder.setPlacementDate(orderDTO.getPlacementDate());
    	updOrder.setState(orderDTO.getState());
    	orderedProductsRepository.deleteAllByOrderId(updOrder.getId());
        List<OrderedProductDAO> orderedProductDAOs = new ArrayList<>();
        for (OrderedProductDTO orderedProductDTO : orderDTO.getOrderedProductDTOs()) {
            orderedProductDAOs.add(new OrderedProductDAO(updOrder.getId(), orderedProductDTO.getProductId(), orderedProductDTO.getQuantity()));
        }
        orderedProductsRepository.saveAll(orderedProductDAOs);
        ordersRepository.save(updOrder);
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

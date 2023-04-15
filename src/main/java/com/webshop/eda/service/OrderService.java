package com.webshop.eda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.stereotype.Service;

import com.poc.OrderRegistered;
import com.webshop.eda.entity.Order;
import com.webshop.eda.infrastructure.OrderInfrastructure;
import com.webshop.eda.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final OrderInfrastructure orderInfrastructure;
	
	public OrderService(
			OrderRepository orderRepository,
			OrderInfrastructure orderInfrastructure
	) {
		this.orderRepository = orderRepository;
		this.orderInfrastructure = orderInfrastructure;
	}
	
	public List<Order> getOrders() {
		return orderRepository.getOrders();
	}
	
	public Order getOrderById(int orderID) {
		return orderRepository.getOrderById(orderID);
	}
	
	public void postOrder(OrderRegistered order) {
		orderRepository.postOrder(order);
	}
	
	public void publishOrder(String order) {
		orderInfrastructure.publishOrder(order);
	}
}

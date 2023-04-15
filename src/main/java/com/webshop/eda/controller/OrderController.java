package com.webshop.eda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.eda.entity.Inventory;
import com.webshop.eda.entity.Order;
import com.webshop.eda.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("")
	public List<Order> getOrders() {
		return orderService.getOrders();
	}
	
	@GetMapping("/{orderID}")
	public Order getOrderById(@PathVariable int orderID) {
		return orderService.getOrderById(orderID);
	}
	
	@PostMapping("")
	public void postOrder(@RequestBody String body) {
		orderService.publishOrder(body);
	}	
}

package com.webshop.eda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.eda.entity.Order;
import com.webshop.eda.entity.Payment;
import com.webshop.eda.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private final PaymentService paymentService;
	
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@GetMapping("")
	public List<Payment> getPayments() {
		return paymentService.getPayments();
	}
	
	@GetMapping("/{orderID}")
	public Payment getPaymentById(@PathVariable int orderID) {
		return paymentService.getPaymentById(orderID);
	}
	
	@PostMapping("")
	public void postPayment(@RequestBody String body) {
		paymentService.publishPayment(body);
	}		
}

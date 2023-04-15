package com.webshop.eda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poc.PaymentRegistered;
import com.webshop.eda.entity.Inventory;
import com.webshop.eda.entity.Payment;
import com.webshop.eda.infrastructure.PaymentInfrastructure;
import com.webshop.eda.repository.PaymentRepository;

@Service
public class PaymentService {

	private final PaymentRepository paymentRepository;
	private final PaymentInfrastructure paymentInfrastructure;
	
	public PaymentService(
			PaymentRepository paymentRepository,
			PaymentInfrastructure paymentInfrastructure
	) {
		this.paymentRepository = paymentRepository;
		this.paymentInfrastructure = paymentInfrastructure;
	}
	
	
	public List<Payment> getPayments() {
		return paymentRepository.getPayments();
	}
	
	public Payment getPaymentById(int orderID) {
		return paymentRepository.getPaymentById(orderID);
	}
	
	public void postPayment(PaymentRegistered payment) {
		paymentRepository.postPayment(payment);
	}
	
	public void publishPayment(String payment) {
		paymentInfrastructure.publishPayment(payment);
	}
}

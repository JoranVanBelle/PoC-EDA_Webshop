package com.webshop.eda.consumer;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificData;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.poc.PaymentRegistered;
import com.webshop.eda.service.PaymentService;

@Service
public class PaymentConsumer {

private final PaymentService paymentService;
	
	public PaymentConsumer(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@DependsOn("paymentDataSourceProperties")
	@KafkaListener(id = "payment", topics = "Webshop.Payed")
	public void consume(GenericRecord message) {
		
		PaymentRegistered payment = (PaymentRegistered) SpecificData.get().deepCopy(message.getSchema(), message);
		
		paymentService.postPayment(payment);
	}
}

package com.webshop.eda.consumer;

import org.apache.avro.generic.GenericRecord;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

	@DependsOn("orderDataSourceProperties")
	@KafkaListener(id = "order", topics = "Webshop.Order")
	public void consume(GenericRecord message) {
		
	}
	
}

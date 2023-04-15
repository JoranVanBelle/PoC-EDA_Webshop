package com.webshop.eda.consumer;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificData;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.poc.TransportUpserted;
import com.webshop.eda.service.TransportService;

@Service
public class TransportConsumer {

private final TransportService transportService;
	
	public TransportConsumer(TransportService transportService) {
		this.transportService = transportService;
	}

	@DependsOn("transportDataSourceProperties")
	@KafkaListener(id = "transport", topics = "Webshop.Transport")
	public void consume(GenericRecord message) {
		
		TransportUpserted transport = (TransportUpserted) SpecificData.get().deepCopy(message.getSchema(), message);
		transportService.postTransport(transport);
	}
}

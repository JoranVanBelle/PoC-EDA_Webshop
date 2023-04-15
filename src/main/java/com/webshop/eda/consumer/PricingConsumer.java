package com.webshop.eda.consumer;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificData;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.poc.PricingUpserted;
import com.webshop.eda.service.PricingService;

@Service
public class PricingConsumer {

private final PricingService pricingService;
	
	public PricingConsumer(PricingService pricingService) {
		this.pricingService = pricingService;
	}

	@DependsOn("pricingDataSourceProperties")
	@KafkaListener(id = "pricing", topics = "Webshop.Pricing")
	public void consume(GenericRecord message) {
		
		PricingUpserted pricing = (PricingUpserted) SpecificData.get().deepCopy(message.getSchema(), message);
		
		pricingService.postPricing(pricing);
	}
}

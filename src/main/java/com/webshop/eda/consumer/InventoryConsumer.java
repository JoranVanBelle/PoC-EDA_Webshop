package com.webshop.eda.consumer;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.poc.InventoryUpserted;
import com.webshop.eda.service.InventoryService;

@Service
public class InventoryConsumer {

	private final InventoryService inventoryService;
	
	public InventoryConsumer(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@DependsOn("inventoryDataSourceProperties")
	@KafkaListener(id = "inventory", topics = "Webshop.Inventory")
	public void consume(GenericRecord message) {
		InventoryUpserted inv = (InventoryUpserted) SpecificData.get().deepCopy(message.getSchema(), message);
		
		inventoryService.postInventory(inv);
	}
}

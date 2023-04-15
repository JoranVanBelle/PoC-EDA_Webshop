package com.webshop.eda.infrastructure;

import org.json.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.poc.InventoryUpserted;
import com.webshop.eda.properties.kafka.KafkaTopicProperties;

@Service
public class InventoryInfrastructure {

	private final KafkaTemplate<String, InventoryUpserted> kafkaTemplate;
	private final KafkaTopicProperties kafkaProperties;
	
	public InventoryInfrastructure(
			KafkaTemplate<String, InventoryUpserted> kafkaTemplate,
			KafkaTopicProperties kafkaProperties
			) {
		this.kafkaTemplate = kafkaTemplate;
		this.kafkaProperties = kafkaProperties;
	}

	
	public void publishInventory(String body) {
		JSONObject json = new JSONObject(body);
		JSONObject inventory = json.getJSONObject("inventory");
		int productID= inventory.getInt("productid");
		String productname = inventory.getString("productname");
		int quantity = inventory.getInt("quantity");
		long timestamp = System.currentTimeMillis();
		
		InventoryUpserted upserted = new InventoryUpserted();
		upserted.setProductID(productID);
		upserted.setProductname(productname);
		upserted.setQuantity(quantity);
		upserted.setTimestamp(timestamp);

		kafkaTemplate.send(kafkaProperties.getInventoryTopic(), String.format("%d", upserted.getProductID()), upserted);	
	}
}

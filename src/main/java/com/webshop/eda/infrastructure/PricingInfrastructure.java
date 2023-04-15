package com.webshop.eda.infrastructure;

import org.json.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.poc.InventoryUpserted;
import com.poc.PricingUpserted;
import com.webshop.eda.properties.kafka.KafkaTopicProperties;

@Service
public class PricingInfrastructure {

	private final KafkaTemplate<String, PricingUpserted> kafkaTemplate;
	private final KafkaTopicProperties kafkaProperties;
	
	public PricingInfrastructure(
			KafkaTemplate<String, PricingUpserted> kafkaTemplate,
			KafkaTopicProperties kafkaProperties
			) {
		this.kafkaTemplate = kafkaTemplate;
		this.kafkaProperties = kafkaProperties;
	}
	
	public void publishPricing(String body) {
		JSONObject json = new JSONObject(body);
		JSONObject inventory = json.getJSONObject("pricing");
		int productID= inventory.getInt("productid");
		String productname = inventory.getString("productname");
		double price = inventory.getInt("price");
		long timestamp = System.currentTimeMillis();
		
		PricingUpserted upserted = new PricingUpserted();
		upserted.setProductID(productID);
		upserted.setProductname(productname);
		upserted.setPrice(price);
		upserted.setTimestamp(timestamp);

		kafkaTemplate.send(kafkaProperties.getPricingTopic(), String.format("%d", upserted.getProductID()), upserted);	
	}
	
}

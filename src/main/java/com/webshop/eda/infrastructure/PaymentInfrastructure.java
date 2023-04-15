package com.webshop.eda.infrastructure;

import org.json.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.poc.InventoryUpserted;
import com.poc.PaymentRegistered;
import com.webshop.eda.properties.kafka.KafkaTopicProperties;

@Service
public class PaymentInfrastructure {

	private final KafkaTemplate<String, PaymentRegistered> kafkaTemplate;
	private final KafkaTopicProperties kafkaProperties;
	
	public PaymentInfrastructure(
			KafkaTemplate<String, PaymentRegistered> kafkaTemplate,
			KafkaTopicProperties kafkaProperties
			) {
		this.kafkaTemplate = kafkaTemplate;
		this.kafkaProperties = kafkaProperties;
	}
	
	public void publishPayment(String body) {
		JSONObject json = new JSONObject(body);
		JSONObject payment = json.getJSONObject("payment");
		int orderID= payment.getInt("orderid");
		double orderPrice = payment.getDouble("orderprice");
		int inventoryID = payment.getInt("inventoryid");
		int quantity = payment.getInt("quantity");
		String username = payment.getString("username");
		int transportID = payment.getInt("transportid");
		long timestamp = System.currentTimeMillis();
		
		PaymentRegistered registered = new PaymentRegistered();
		registered.setOrderID(orderID);
		registered.setOrderPrice(orderPrice);
		registered.setInventoryID(inventoryID);
		registered.setQuantity(quantity);
		registered.setUsername(username);
		registered.setTransportID(transportID);
		registered.setTimestamp(timestamp);

		kafkaTemplate.send(kafkaProperties.getPayedTopic(), String.format("%d", registered.getOrderID()), registered);
		
	}
}

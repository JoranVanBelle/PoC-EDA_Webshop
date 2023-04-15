package com.webshop.eda.infrastructure;

import org.json.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.poc.OrderRegistered;
import com.poc.PaymentRegistered;
import com.webshop.eda.properties.kafka.KafkaTopicProperties;

@Service
public class OrderInfrastructure {

	private final KafkaTemplate<String, OrderRegistered> kafkaTemplate;
	private final KafkaTopicProperties kafkaProperties;
	
	public OrderInfrastructure(
			KafkaTemplate<String, OrderRegistered> kafkaTemplate,
			KafkaTopicProperties kafkaProperties
			) {
		this.kafkaTemplate = kafkaTemplate;
		this.kafkaProperties = kafkaProperties;
	}
	
	public void publishOrder(String body) {
		JSONObject json = new JSONObject(body);
		JSONObject order = json.getJSONObject("order");
		int orderID= order.getInt("orderid");
		double orderPrice = order.getDouble("orderprice");
		int inventoryID = order.getInt("inventoryid");
		int quantity = order.getInt("quantity");
		String username = order.getString("username");
		int transportID = order.getInt("transportid");
		long timestamp = System.currentTimeMillis();
		
		OrderRegistered registered = new OrderRegistered();
		registered.setOrderID(orderID);
		registered.setOrderPrice(orderPrice);
		registered.setInventoryID(inventoryID);
		registered.setQuantity(quantity);
		registered.setUsername(username);
		registered.setTransportID(transportID);
		registered.setTimestamp(timestamp);

		kafkaTemplate.send(kafkaProperties.getOrderTopic(), String.format("%d", registered.getOrderID()), registered);
	}
}

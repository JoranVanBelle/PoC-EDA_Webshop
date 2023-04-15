package com.webshop.eda.properties.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="spring.kafka")
public class KafkaTopicProperties {
	private String inventoryTopic;
	private String pricingTopic;
	private String transportTopic;
	private String orderTopic;
	private String payedTopic;
	
	public String getInventoryTopic() {
		return inventoryTopic;
	}
	
	public void setInventoryTopic(String inventoryTopic) {
		this.inventoryTopic = inventoryTopic;
	}
	
	public String getPricingTopic() {
		return pricingTopic;
	}
	
	public void setPricingTopic(String pricingTopic) {
		this.pricingTopic = pricingTopic;
	}
	
	public String getTransportTopic() {
		return transportTopic;
	}
	
	public void setTransportTopic(String transportTopic) {
		this.transportTopic = transportTopic;
	}
	
	public String getOrderTopic() {
		return orderTopic;
	}
	
	public void setOrderTopic(String orderTopic) {
		this.orderTopic = orderTopic;
	}
	
	public String getPayedTopic() {
		return payedTopic;
	}
	
	public void setPayedTopic(String payedTopic) {
		this.payedTopic = payedTopic;
	}
}

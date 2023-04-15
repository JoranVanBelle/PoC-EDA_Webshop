package com.webshop.eda.properties.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="orderedtopaid")
public class OrderedToPaidStreamProperties {

	private String applicationId;
	private String bootstrapServer;
	private String keySerDes;
	private String valueSerDes;
	private String schemaRegistry;
	private String input;
	private String topic;

	public String getApplicationId() {
		return applicationId;
	}
	
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	
	public String getBootstrapServer() {
		return bootstrapServer;
	}
	
	public void setBootstrapServer(String bootstrapServer) {
		this.bootstrapServer = bootstrapServer;
	}
	
	public String getKeySerDes() {
		return keySerDes;
	}
	
	public void setKeySerDes(String keySerDes) {
		this.keySerDes = keySerDes;
	}
	
	public String getValueSerDes() {
		return valueSerDes;
	}
	
	public void setValueSerDes(String valueSerDes) {
		this.valueSerDes = valueSerDes;
	}
	
	public String getSchemaRegistry() {
		return schemaRegistry;
	}
	
	public void setSchemaRegistry(String schemaRegistry) {
		this.schemaRegistry = schemaRegistry;
	}
	
	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
}

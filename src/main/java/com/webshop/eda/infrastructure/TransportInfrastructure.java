package com.webshop.eda.infrastructure;

import org.json.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.poc.PricingUpserted;
import com.poc.TransportUpserted;
import com.webshop.eda.properties.kafka.KafkaTopicProperties;

@Service
public class TransportInfrastructure {

	private final KafkaTemplate<String, TransportUpserted> kafkaTemplate;
	private final KafkaTopicProperties kafkaProperties;
	
	public TransportInfrastructure(
			KafkaTemplate<String, TransportUpserted> kafkaTemplate,
			KafkaTopicProperties kafkaProperties
			) {
		this.kafkaTemplate = kafkaTemplate;
		this.kafkaProperties = kafkaProperties;
	}
	
	public void publishTransport(String body) {
		JSONObject json = new JSONObject(body);
		JSONObject transport = json.getJSONObject("transport");
		int transportID= transport.getInt("transportid");
		String transportname = transport.getString("transportname");
		long timestamp = System.currentTimeMillis();
		
		TransportUpserted upserted = new TransportUpserted();
		upserted.setTransportID(transportID);
		upserted.setTransportname(transportname);
		upserted.setTimestamp(timestamp);

		kafkaTemplate.send(kafkaProperties.getTransportTopic(), String.format("%d", upserted.getTransportID()), upserted);
	}
}

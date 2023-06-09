package com.webshop.eda.components.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import com.poc.InventoryUpserted;
import com.poc.OrderRegistered;
import com.webshop.eda.entity.Order;
import com.webshop.eda.properties.kafka.ProducerProperties;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;

@Component
public class OrderProducerComponent {
	
	private final ProducerProperties properties;
	
	public OrderProducerComponent(ProducerProperties properties) {
		this.properties = properties;
	}
	
    public ProducerFactory<String, OrderRegistered> orderProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
          ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          properties.getBootstrapServer());
        configProps.put(
        	ProducerConfig.ACKS_CONFIG,
        	properties.getAcks());
        configProps.put(
            	ProducerConfig.RETRIES_CONFIG,
            	properties.getRetries());
        configProps.put(
          ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
          properties.getKeySerializer());
        configProps.put(
          ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
          properties.getValueSerializer());
        configProps.put(
        	AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        	properties.getSchemaRegistry());
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, OrderRegistered> orderKafkaTemplate() {
        return new KafkaTemplate<>(orderProducerFactory());
    }
	
}

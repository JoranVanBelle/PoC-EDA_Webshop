package com.webshop.eda.components.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Component;

import com.poc.InventoryUpserted;
import com.poc.TransportUpserted;
import com.webshop.eda.properties.kafka.ConsumerProperties;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;

@Component
public class TransportConsumerComponent {
	
private final ConsumerProperties properties;
	
	public TransportConsumerComponent(ConsumerProperties properties) {
		this.properties = properties;
	}
	
    @Bean
    public ConsumerFactory<String, TransportUpserted> transportConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
          ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          properties.getBootstrapServer());
        props.put(
          ConsumerConfig.GROUP_ID_CONFIG, 
          properties.getGroupId());
        props.put(
        	ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
        	properties.getAutoOffsetReset());
        props.put(
          ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
          properties.getKeyDeserializer());
        props.put(
          ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
          properties.getValueDeserializer());
        props.put(
        	AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        	properties.getSchemaRegistry());
        
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransportUpserted> 
      transportKafkaListenerContainerFactory(ConsumerFactory<String, TransportUpserted> transportConsumerFactory) {
   
        ConcurrentKafkaListenerContainerFactory<String, TransportUpserted> factory =
          new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(transportConsumerFactory);
        return factory;
    }
}

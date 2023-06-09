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
import com.poc.PaymentRegistered;
import com.poc.TransportUpserted;
import com.webshop.eda.properties.kafka.ConsumerProperties;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;

@Component
public class PaymentConsumerComponent {
	
private final ConsumerProperties properties;
	
	public PaymentConsumerComponent(ConsumerProperties properties) {
		this.properties = properties;
	}
	
    public ConsumerFactory<String, PaymentRegistered> paymentConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        System.err.println(properties.getBootstrapServer());
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
    public ConcurrentKafkaListenerContainerFactory<String, PaymentRegistered> 
      paymentKafkaListenerContainerFactory() {
   
        ConcurrentKafkaListenerContainerFactory<String, PaymentRegistered> factory =
          new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(paymentConsumerFactory());
        return factory;
    }
}

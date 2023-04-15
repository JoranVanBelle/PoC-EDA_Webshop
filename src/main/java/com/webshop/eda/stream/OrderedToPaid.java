package com.webshop.eda.stream;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.poc.OrderRegistered;
import com.poc.PaymentRegistered;
import com.webshop.eda.properties.kafka.OrderedToPaidStreamProperties;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;

@Service
public class OrderedToPaid {

	private final OrderedToPaidStreamProperties properties;
	
	public OrderedToPaid(OrderedToPaidStreamProperties properties) {
		this.properties = properties;
	}
	
	@Bean
	public Topology buildTopology() {
		
		StreamsBuilder streamsBuilder = new StreamsBuilder();
		
        streamsBuilder
                .stream(properties.getInput(), Consumed.with(Serdes.String(), orderRegisteredSerdes(properties)))
        		.mapValues(v -> new PaymentRegistered(v.getOrderID(), v.getOrderPrice(), v.getInventoryID(), v.getQuantity(), v.getUsername(), v.getTransportID(), System.currentTimeMillis()))
        		.to(properties.getTopic());
        
        return streamsBuilder.build();
	}
	
	public Properties streamsConfig() {
        Properties config = new Properties();               

        config.put(StreamsConfig.APPLICATION_ID_CONFIG, properties.getApplicationId());
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServer());
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        config.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, properties.getSchemaRegistry());
        
        return config;
	}
	
	@Bean
	@DependsOn({"paymentDataSourceProperties", "orderConsumer"})
	public KafkaStreams orderedToPaidStreams(Topology topo) {
	    KafkaStreams kafkaStreams = new KafkaStreams(topo, streamsConfig());
	    kafkaStreams.start();
	    return kafkaStreams;
	}
	
	public static SpecificAvroSerde<OrderRegistered> orderRegisteredSerdes(OrderedToPaidStreamProperties props) {
		final SpecificAvroSerde<OrderRegistered> orderRegistered = new SpecificAvroSerde<>();
		Map<String, String> serdeConfig = new HashMap<>();
		serdeConfig.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, props.getSchemaRegistry());
		orderRegistered.configure(serdeConfig, false);
		return orderRegistered;
	}
	
}

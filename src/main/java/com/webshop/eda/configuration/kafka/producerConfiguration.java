package com.webshop.eda.configuration.kafka;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.webshop.eda.properties.kafka.ProducerProperties;

@Configuration
@EnableConfigurationProperties(ProducerProperties.class)
public class producerConfiguration {
	
}

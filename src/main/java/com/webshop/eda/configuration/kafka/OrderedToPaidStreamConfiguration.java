package com.webshop.eda.configuration.kafka;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.webshop.eda.properties.kafka.OrderedToPaidStreamProperties;

@Configuration
@EnableConfigurationProperties(OrderedToPaidStreamProperties.class)
public class OrderedToPaidStreamConfiguration {

}

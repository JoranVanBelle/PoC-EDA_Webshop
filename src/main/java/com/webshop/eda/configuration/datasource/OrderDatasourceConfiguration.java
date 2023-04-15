package com.webshop.eda.configuration.datasource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.webshop.eda.properties.datasource.OrderDatasourceProperties;

@Configuration
@EnableConfigurationProperties(OrderDatasourceProperties.class)
public class OrderDatasourceConfiguration {

}

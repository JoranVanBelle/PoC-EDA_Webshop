package com.webshop.eda.components.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.webshop.eda.properties.datasource.OrderDatasourceProperties;

@Configuration
public class OrderJdbcComponent {
	
	private final OrderDatasourceProperties properties;
	
	public OrderJdbcComponent(OrderDatasourceProperties properties) {
		this.properties = properties;
	}
	
	@Bean
    public DataSourceProperties orderDataSourceProperties() {
		DataSourceProperties props = new DataSourceProperties();
		props.setDriverClassName(properties.getDriverClassName());
		props.setUrl(properties.getUrl());
		props.setUsername(properties.getUsername());
		props.setPassword(properties.getPassword());
		
        return props;
    }
	
	@Bean
	public DataSource orderDataSource() {
	    return orderDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean
	public NamedParameterJdbcTemplate orderJdbcTemplate(@Qualifier("orderDataSource") DataSource dataSource) {
	    return new NamedParameterJdbcTemplate(dataSource);
	}
}

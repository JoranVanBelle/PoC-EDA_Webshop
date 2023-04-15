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

import com.webshop.eda.properties.datasource.PricingDatasourceProperties;

@Configuration
public class PricingJdbcComponent {
	
	private final PricingDatasourceProperties properties;
	
	public PricingJdbcComponent(PricingDatasourceProperties properties) {
		this.properties = properties;
	}
	
	@Bean
    public DataSourceProperties pricingDataSourceProperties() {
		DataSourceProperties props = new DataSourceProperties();
		props.setDriverClassName(properties.getDriverClassName());
		props.setUrl(properties.getUrl());
		props.setUsername(properties.getUsername());
		props.setPassword(properties.getPassword());
		
        return props;
    }
	
	@Bean
	public DataSource pricingDataSource() {
	    return pricingDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean
	public NamedParameterJdbcTemplate pricingJdbcTemplate(@Qualifier("pricingDataSource") DataSource dataSource) {
	    return new NamedParameterJdbcTemplate(dataSource);
	}
}

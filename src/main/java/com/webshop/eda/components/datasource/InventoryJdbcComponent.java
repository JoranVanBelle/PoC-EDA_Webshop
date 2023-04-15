package com.webshop.eda.components.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.webshop.eda.properties.datasource.InventoryDatasourceProperties;

@Configuration
public class InventoryJdbcComponent {
	
	private final InventoryDatasourceProperties properties;
	
	public InventoryJdbcComponent(InventoryDatasourceProperties properties) {
		this.properties = properties;
	}
	
	@Bean("inventoryDataSourceProperties")
	@Primary
	public DataSource primaryDataSourceProperties() {
		return DataSourceBuilder.create()
				.url(properties.getUrl())
				.username(properties.getUsername())
				.password(properties.getPassword())
				.build();
	}
	
	  @Bean(name = "inventoryJdbcTemplate")
	  public NamedParameterJdbcTemplate inventoryJdbcTemplate(@Qualifier("inventoryDataSourceProperties") DataSource dataSource) {
	    return new NamedParameterJdbcTemplate(dataSource);
	  }
	
}

package com.webshop.eda.components.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.webshop.eda.properties.datasource.PaymentDatasourceProperties;

@Configuration
public class PaymentJdbcComponent {
	
	private final PaymentDatasourceProperties properties;
	
	public PaymentJdbcComponent(PaymentDatasourceProperties properties) {
		this.properties = properties;
	}
	
	@Primary
	@Bean("paymentDataSourceProperties")
	public DataSource primaryDataSourceProperties() {
		return DataSourceBuilder.create()
				.url(properties.getUrl())
				.username(properties.getUsername())
				.password(properties.getPassword())
				.build();
	}
	
	  @Bean(name = "paymentJdbcTemplate")
	  public NamedParameterJdbcTemplate paymentJdbcTemplate(@Qualifier("paymentDataSourceProperties") DataSource dataSource) {
	    return new NamedParameterJdbcTemplate(dataSource);
	  }
}

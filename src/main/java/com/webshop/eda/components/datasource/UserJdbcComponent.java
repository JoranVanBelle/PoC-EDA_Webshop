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

import com.webshop.eda.properties.datasource.UserDatasourceProperties;

@Configuration
public class UserJdbcComponent {
	
	private final UserDatasourceProperties properties;
	
	public UserJdbcComponent(UserDatasourceProperties properties) {
		this.properties = properties;
	}
	
	@Primary
	@Bean("userDataSourceProperties")
	public DataSource primaryDataSourceProperties() {
		return DataSourceBuilder.create()
				.url(properties.getUrl())
				.username(properties.getUsername())
				.password(properties.getPassword())
				.build();
	}
	
	  @Bean(name = "userJdbcTemplate")
	  public NamedParameterJdbcTemplate userJdbcTemplate(@Qualifier("userDataSourceProperties") DataSource dataSource) {
	    return new NamedParameterJdbcTemplate(dataSource);
	  }
}

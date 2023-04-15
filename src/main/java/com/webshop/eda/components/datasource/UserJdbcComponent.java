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

import com.webshop.eda.properties.datasource.UserDatasourceProperties;

@Configuration
public class UserJdbcComponent {
	
	private final UserDatasourceProperties properties;
	
	public UserJdbcComponent(UserDatasourceProperties properties) {
		this.properties = properties;
	}
	
	@Bean
    public DataSourceProperties userDataSourceProperties() {
		DataSourceProperties props = new DataSourceProperties();
		props.setDriverClassName(properties.getDriverClassName());
		props.setUrl(properties.getUrl());
		props.setUsername(properties.getUsername());
		props.setPassword(properties.getPassword());
		
        return props;
    }
	
	@Bean
	public DataSource userDataSource() {
	    return userDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean
	public NamedParameterJdbcTemplate userJdbcTemplate(@Qualifier("userDataSource") DataSource dataSource) {
	    return new NamedParameterJdbcTemplate(dataSource);
	}
}

package com.webshop.eda.configuration.datasource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.webshop.eda.properties.datasource.UserDatasourceProperties;

@Configuration
@EnableConfigurationProperties(UserDatasourceProperties.class)
public class UserDatasourceConfiguration {

}

package com.webshop.eda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration;

@SpringBootApplication(exclude = JdbcRepositoriesAutoConfiguration.class)
public class EdaWebshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdaWebshopApplication.class, args);
	}
}

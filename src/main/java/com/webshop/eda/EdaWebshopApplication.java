package com.webshop.eda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication(exclude = JdbcRepositoriesAutoConfiguration.class)
public class EdaWebshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdaWebshopApplication.class, args);
	}
}

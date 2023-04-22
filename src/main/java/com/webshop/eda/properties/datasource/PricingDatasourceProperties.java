package com.webshop.eda.properties.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="pricing.datasource")
public class PricingDatasourceProperties {

	private String url;
	private String password;
	private String username;
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}

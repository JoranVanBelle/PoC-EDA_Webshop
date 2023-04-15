package com.webshop.eda.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	public UserRepository(@Qualifier("userJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		jdbcTemplate.update("DROP TABLE IF EXISTS Users;", new MapSqlParameterSource());
		
		jdbcTemplate.update("CREATE TABLE Users(\r\n"
				+ "	username VARCHAR(100) PRIMARY KEY,\r\n"
				+ "    pw VARCHAR(100) NOT NULL\r\n"
				+ ");", new MapSqlParameterSource());
		jdbcTemplate.update("INSERT INTO Users (username, pw) VALUES (\"user\", \"admin\");", new MapSqlParameterSource());
	}
	
	public boolean login(String username, String password) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("username", username);
		
		List<Map<String, Object>> orders = jdbcTemplate.queryForList("SELECT pw FROM Users WHERE username = :username;", paramSource);
		return password.equals(orders.get(0).get("password"));
	}
	
}

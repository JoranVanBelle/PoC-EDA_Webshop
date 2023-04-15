package com.webshop.eda.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.poc.TransportUpserted;
import com.webshop.eda.entity.Pricing;
import com.webshop.eda.entity.Transport;

@Repository
public class TransportRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	public TransportRepository(@Qualifier("transportJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		jdbcTemplate.update("DROP TABLE IF EXISTS Transport;", new MapSqlParameterSource());
		
		jdbcTemplate.update("CREATE TABLE Transport(\r\n"
				+ "	transportid INT PRIMARY KEY,\r\n"
				+ "    transportname VARCHAR(100) NOT NULL,\r\n"
				+ "    timeUpserted BIGINT NOT NULL\r\n"
				+ ");", new MapSqlParameterSource());
	}
	
	public List<Transport> getTransport() {
		List<Transport> pricingList = new ArrayList<>();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		List<Map<String, Object>> pricings = jdbcTemplate.queryForList("SELECT * FROM transport GROUP BY transportid ORDER BY timeUpserted DESC;", paramSource);
		
		for(Map<String, Object> pricing : pricings) {
			pricingList.add(createPTransportObject(pricing));
		}
		
		return pricingList;
	}
	
	public Transport getTransportById(int transportID) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("transportid", transportID);
		
		List<Map<String, Object>> pricings = jdbcTemplate.queryForList("SELECT * FROM transport WHERE transportid = :transportid ORDER BY timeUpserted DESC LIMIT 1;", paramSource);
		
		return createPTransportObject(pricings.get(0));
	}
	
	public int postTransport(TransportUpserted transport) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("transportid", transport.getTransportID());
		paramSource.addValue("transportname", transport.getTransportname());
		paramSource.addValue("timeUpserted", transport.getTimestamp());
		
		int rowsAffected = jdbcTemplate.update("INSERT INTO Transport(transportid, transportname, timeUpserted) VALUES(:transportid, :transportname, :timeUpserted);", paramSource);
		return rowsAffected;
	}
	
	private Transport createPTransportObject(Map<String, Object> transport) {
		return new Transport(Integer.parseInt(transport.get("transportid").toString()), transport.get("transportname").toString());
	}
}

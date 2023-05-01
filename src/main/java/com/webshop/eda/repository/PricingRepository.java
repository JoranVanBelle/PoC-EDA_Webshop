package com.webshop.eda.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.poc.PricingUpserted;
import com.webshop.eda.entity.Pricing;

@Repository
public class PricingRepository {

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public PricingRepository(@Qualifier("pricingJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		jdbcTemplate.update("DROP TABLE IF EXISTS Pricing;", new MapSqlParameterSource());
		
		jdbcTemplate.update("CREATE TABLE Pricing(\r\n"
				+ "	productid INT PRIMARY KEY,\r\n"
				+ "    productname VARCHAR(100) NOT NULL,\r\n"
				+ "    price DOUBLE NOT NULL,\r\n"
				+ "    timeUpserted BIGINT NOT NULL\r\n"
				+ ");", new MapSqlParameterSource());
	}
	
	public List<Pricing> getPricings() {
		List<Pricing> pricingList = new ArrayList<>();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		List<Map<String, Object>> pricings = jdbcTemplate.queryForList("SELECT * FROM Pricing GROUP BY productid ORDER BY timeUpserted DESC;", paramSource);
		
		for(Map<String, Object> pricing : pricings) {
			pricingList.add(createPricingObject(pricing));
		}
		
		return pricingList;
	}
	
	public Pricing getPricingById(int productID) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("productid", productID);
		
		List<Map<String, Object>> pricings = jdbcTemplate.queryForList("SELECT * FROM Pricing WHERE productid = :productid ORDER BY timeUpserted DESC LIMIT 1;", paramSource);
		
		return createPricingObject(pricings.get(0));
	}
	
	public int postPricing(PricingUpserted pricing) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("productid", pricing.getProductID());
		paramSource.addValue("productname", pricing.getProductname());
		paramSource.addValue("price", pricing.getPrice());
		paramSource.addValue("timeUpserted", pricing.getTimestamp());
		
		int rowsAffected = jdbcTemplate.update("INSERT INTO Pricing(productid, productname, price, timeUpserted) VALUES(:productid, :productname, :price, :timeUpserted);", paramSource);
		
		return rowsAffected;
	}
	
	private Pricing createPricingObject(Map<String, Object> pricing) {
		return new Pricing(Integer.parseInt(pricing.get("productid").toString()), pricing.get("productname").toString(), Double.parseDouble(pricing.get("price").toString()));
	}
	
}

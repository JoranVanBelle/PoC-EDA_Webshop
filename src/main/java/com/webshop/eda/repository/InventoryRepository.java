package com.webshop.eda.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.poc.InventoryUpserted;
import com.webshop.eda.entity.Inventory;

@Repository
public class InventoryRepository {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	public InventoryRepository(@Qualifier("inventoryJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		jdbcTemplate.update("DROP TABLE IF EXISTS Inventory;", new MapSqlParameterSource());
		
		jdbcTemplate.update("CREATE TABLE Inventory(\r\n"
				+ "	productid INT PRIMARY KEY,\r\n"
				+ "    productname VARCHAR(100) NOT NULL,\r\n"
				+ "    quantity INT NOT NULL,\r\n"
				+ "    timeUpserted BIGINT NOT NULL\r\n"
				+ ");", new MapSqlParameterSource());
	}
	
	public List<Inventory> getInventories() {
		List<Inventory> inventoryList = new ArrayList<>();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		List<Map<String, Object>> inventories = jdbcTemplate.queryForList("SELECT * FROM Inventory WHERE quantity != 0 GROUP BY productid ORDER BY timeUpserted DESC;", paramSource);
		
		for(Map<String, Object> inv : inventories) {
			inventoryList.add(createInventoryObject(inv));
		}
		
		return inventoryList;
	}
	
	public Inventory getInventoryById(int productID) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("productid", productID);
		
		List<Map<String, Object>> inventories = jdbcTemplate.queryForList("SELECT * FROM Inventory WHERE productid = :productid ORDER BY timeUpserted DESC LIMIT 1;", paramSource);
		
		return createInventoryObject(inventories.get(0));
	}
	
	public int postInventory(InventoryUpserted inventory) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("productid", inventory.getProductID());
		paramSource.addValue("productname", inventory.getProductname());
		paramSource.addValue("quantity", inventory.getQuantity());
		paramSource.addValue("timeUpserted", inventory.getTimestamp());
		
		int rowsAffected = jdbcTemplate.update("INSERT INTO Inventory(productid, productname, quantity, timeUpserted) VALUES(:productid, :productname, :quantity, :timeUpserted);", paramSource);
		
		return rowsAffected;
	}
	
	private Inventory createInventoryObject(Map<String, Object> inventory) {
		return new Inventory(Integer.parseInt(inventory.get("productid").toString()), inventory.get("productname").toString(), Integer.parseInt(inventory.get("quantity").toString()));
	}
	
}

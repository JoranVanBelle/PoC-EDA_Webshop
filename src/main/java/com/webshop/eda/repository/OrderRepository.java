package com.webshop.eda.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.poc.OrderRegistered;
import com.webshop.eda.entity.Order;

@Repository
public class OrderRepository {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	public OrderRepository(@Qualifier("orderJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		jdbcTemplate.update("DROP TABLE IF EXISTS Orders;", new MapSqlParameterSource());
		
		jdbcTemplate.update("CREATE TABLE orders(\r\n"
				+ "	id INT PRIMARY KEY AUTO_INCREMENT,\r\n"
				+ "	orderid INT NOT NULL,\r\n"
				+ "    orderprice DOUBLE NOT NULL,\r\n"
				+ "    inventoryid INT NOT NULL,\r\n"
				+ "    quantity INT NOT NULL,\r\n"
				+ "    username VARCHAR(100) NOT NULL,\r\n"
				+ "    transportid INT NOT NULL,\r\n"
				+ "    timeUpserted BIGINT NOT NULL\r\n"
				+ ");", new MapSqlParameterSource());
	}
	
	public List<Order> getOrders() {
		List<Order> orderList = new ArrayList<>();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		List<Map<String, Object>> orders = jdbcTemplate.queryForList("SELECT * FROM Orders GROUP BY orderid ORDER BY timeUpserted DESC;", paramSource);

		for(Map<String, Object> order : orders) {
			orderList.add(createOrderObject(order));
		}
		
		return orderList;
	}
	
	public Order getOrderById(int orderID) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("orderid", orderID);
		
		List<Map<String, Object>> orders = jdbcTemplate.queryForList("SELECT * FROM Orders WHERE orderid = :orderid ORDER BY timeUpserted DESC LIMIT 1;", paramSource);

		return createOrderObject(orders.get(0));
	}
	
	public int postOrder(OrderRegistered order) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("oderid", order.getOrderID());
		paramSource.addValue("orderprice", order.getOrderPrice());
		paramSource.addValue("inventoryid", order.getInventoryID());
		paramSource.addValue("quantity", order.getQuantity());
		paramSource.addValue("username", order.getUsername());
		paramSource.addValue("transportid", order.getTransportID());
		paramSource.addValue("timestamp", order.getTimestamp());
		
		int rowsAffected = jdbcTemplate.update("INSERT INTO Orders(orderid, orderprice, inventoryid, quantity, username, transportid, timeUpserted) VALUES(:orderid, :orderprice, :inventoryid, :quantity, :username, :transportid, :timestamp);", paramSource);
		
		return rowsAffected;
				
	}

	
	private Order createOrderObject(Map<String, Object> order) {
		return new Order(Integer.parseInt(order.get("orderid").toString()), Double.parseDouble(order.get("price").toString()), Integer.parseInt(order.get("inventoryid").toString()), Integer.parseInt(order.get("quantity").toString()), order.get("username").toString(), Integer.parseInt(order.get("transportid").toString()));
	}
}

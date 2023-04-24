package com.webshop.eda.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.poc.InventoryUpserted;
import com.poc.PaymentRegistered;
import com.webshop.eda.entity.Payment;

@Repository
public class PaymentRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	public PaymentRepository(@Qualifier("paymentJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		jdbcTemplate.update("DROP TABLE IF EXISTS Payment;", new MapSqlParameterSource());
		
		jdbcTemplate.update("CREATE TABLE Payment(\r\n"
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
	
	public List<Payment> getPayments() {
		List<Payment> paymentsList = new ArrayList<>();
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		List<Map<String, Object>> payments = jdbcTemplate.queryForList("SELECT * FROM Payment ORDER BY timeUpserted DESC;", paramSource);

		for(Map<String, Object> payment : payments) {
			paymentsList.add(createPaymentObject(payment));
		}
		
		return paymentsList;
	}
	
	public Payment getPaymentById(int orderID) {
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("orderid", orderID);
		
		List<Map<String, Object>> payments = jdbcTemplate.queryForList("SELECT * FROM Payment WHERE orderid = :orderid ORDER BY timeUpserted DESC LIMIT 1;", paramSource);

		return createPaymentObject(payments.get(0));
	}
	
	public int postPayment(PaymentRegistered payment) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("orderid", payment.getOrderID());
		paramSource.addValue("orderprice", payment.getOrderPrice());
		paramSource.addValue("inventoryid", payment.getInventoryID());
		paramSource.addValue("quantity", payment.getQuantity());
		paramSource.addValue("username", payment.getUsername());
		paramSource.addValue("transportid", payment.getTransportID());
		paramSource.addValue("timestamp", payment.getTimestamp());
		
		int rowsAffected = jdbcTemplate.update("INSERT INTO Payment(orderid, orderprice, inventoryid, quantity, username, transportid, timeUpserted) VALUES(:orderid, :orderprice, :inventoryid, :quantity, :username, :transportid, :timestamp);", paramSource);
		
		return rowsAffected;
	}
	
	private Payment createPaymentObject(Map<String, Object> order) {
		return new Payment(Integer.parseInt(order.get("orderid").toString()), Double.parseDouble(order.get("orderprice").toString()), Integer.parseInt(order.get("inventoryid").toString()), Integer.parseInt(order.get("quantity").toString()), order.get("username").toString(), Integer.parseInt(order.get("transportid").toString()));
	}
}

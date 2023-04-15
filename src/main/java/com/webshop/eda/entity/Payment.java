package com.webshop.eda.entity;

public class Payment {
	private int orderID;
	private double orderPrice;
	private int inventoryID;
	private int quantity;
	private String username;
	private int transportID;
	
	public Payment() {
		
	}

	public Payment(int orderID, double orderPrice, int inventoryID, int quantity, String username, int transportID) {
		super();
		this.orderID = orderID;
		this.orderPrice = orderPrice;
		this.inventoryID = inventoryID;
		this.quantity = quantity;
		this.username = username;
		this.transportID = transportID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getInventoryID() {
		return inventoryID;
	}

	public void setInventoryID(int inventoryID) {
		this.inventoryID = inventoryID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTransportID() {
		return transportID;
	}

	public void setTransportID(int transportID) {
		this.transportID = transportID;
	}
}

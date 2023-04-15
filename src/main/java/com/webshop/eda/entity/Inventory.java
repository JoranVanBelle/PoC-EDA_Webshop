package com.webshop.eda.entity;

public class Inventory {
	
	private int productID;
	private String productname;
	private int quantity;
	
	public Inventory() {
		
	}
	
	public Inventory(int productID, String productname, int quantity) {
		super();
		this.productID = productID;
		this.productname = productname;
		this.quantity = quantity;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}

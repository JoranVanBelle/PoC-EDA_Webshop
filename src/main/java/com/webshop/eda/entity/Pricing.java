package com.webshop.eda.entity;

public class Pricing {

	private int productID;
	private String productname;
	private double price;
	
	public Pricing(int productID, String productname, double price) {
		super();
		this.productID = productID;
		this.productname = productname;
		this.price = price;
	}
	
	public Pricing() {
		
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}

package com.webshop.eda.entity;

public class Transport {
	
	private int transportID;
	private String transportname;
	
	public Transport() {
		
	}

	public Transport(int transportID, String transportname) {
		super();
		this.transportID = transportID;
		this.transportname = transportname;
	}

	public int getTransportID() {
		return transportID;
	}

	public void setTransportID(int transportID) {
		this.transportID = transportID;
	}

	public String getTransportname() {
		return transportname;
	}

	public void setTransportname(String transportname) {
		this.transportname = transportname;
	}
	
	
}

package com.webshop.eda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.InventoryUpserted;
import com.webshop.eda.entity.Inventory;
import com.webshop.eda.infrastructure.InventoryInfrastructure;
import com.webshop.eda.repository.InventoryRepository;

@Service
public class InventoryService {

	private final InventoryRepository inventoryRepository;
	private final InventoryInfrastructure inventoryInfrastructure;
	
	public InventoryService(
			InventoryRepository inventoryRepository,
			InventoryInfrastructure inventoryInfrastructure
	) {
		this.inventoryRepository = inventoryRepository;
		this.inventoryInfrastructure = inventoryInfrastructure;
	}
	
	public List<Inventory> getInventories() {
		return inventoryRepository.getInventories();
	}
	
	public Inventory getInventoryById(int productID) {
		return inventoryRepository.getInventoryById(productID);
	}
	
	public void postInventory(InventoryUpserted inventory) {
		inventoryRepository.postInventory(inventory);
	}
	
	public void publishInventory(String inventory) {
		inventoryInfrastructure.publishInventory(inventory);
	}	
}

package com.webshop.eda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.PricingUpserted;
import com.webshop.eda.entity.Pricing;
import com.webshop.eda.infrastructure.PricingInfrastructure;
import com.webshop.eda.repository.PricingRepository;

@Service
public class PricingService {
	
	private final PricingRepository pricingRepository;
	private final PricingInfrastructure pricingInfrastructure;
	
	public PricingService(
			PricingRepository pricingRepository,
			PricingInfrastructure pricingInfrastructure
	) {
		this.pricingRepository = pricingRepository;
		this.pricingInfrastructure = pricingInfrastructure;
	}
	
	public List<Pricing> getPricings() {
		return pricingRepository.getPricings();
	}
	
	public Pricing getPricingById(int productID) {
		return pricingRepository.getPricingById(productID);
	}
	
	public void postPricing(PricingUpserted pricing) {
		pricingRepository.postPricing(pricing);
	}
	
	public void publishPricing(String pricing) {
		pricingInfrastructure.publishPricing(pricing);
	}
}

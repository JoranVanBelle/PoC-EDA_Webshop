package com.webshop.eda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.eda.entity.Pricing;
import com.webshop.eda.service.PricingService;

@RestController
@RequestMapping("/pricing")
public class PricingController {
	
	private final PricingService pricingService;
	
	public PricingController(PricingService pricingService) {
		this.pricingService = pricingService;
	}
	
	@GetMapping("")
	public List<Pricing> getPricings() {
		return pricingService.getPricings();
	}
	
	@GetMapping("/{productID}")
	public Pricing getPricingById(@PathVariable int productID) {
		return pricingService.getPricingById(productID);
	}
	
	@PostMapping("")
	public void postPricing(@RequestBody String body) {
		pricingService.publishPricing(body);
	}	
}

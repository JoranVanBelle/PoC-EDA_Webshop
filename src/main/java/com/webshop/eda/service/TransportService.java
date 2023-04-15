package com.webshop.eda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.TransportUpserted;
import com.webshop.eda.entity.Transport;
import com.webshop.eda.infrastructure.TransportInfrastructure;
import com.webshop.eda.repository.TransportRepository;

@Service
public class TransportService {

	private final TransportRepository transportRepository;
	private final TransportInfrastructure transportInfrastructure;
	
	public TransportService(
			TransportRepository transportRepository,
			TransportInfrastructure transportInfrastructure
	) {
		this.transportRepository = transportRepository;
		this.transportInfrastructure = transportInfrastructure;
	}
	
	public List<Transport> getTransports() {
		return transportRepository.getTransport();
	}
	
	public Transport getTransportById(int transportID) {
		return transportRepository.getTransportById(transportID);
	}
	
	public void postTransport(TransportUpserted transport) {
		transportRepository.postTransport(transport);
	}
	
	public void publishTransport(String transport) {
		transportInfrastructure.publishTransport(transport);
	}
}

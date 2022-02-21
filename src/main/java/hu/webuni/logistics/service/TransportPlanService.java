package hu.webuni.logistics.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.logistics.model.TransportPlan;
import hu.webuni.logistics.repository.TransportPlanRepository;

@Service
public class TransportPlanService {

	@Autowired
	TransportPlanRepository transportplanrepository;
	
	@Transactional
	public TransportPlan addTransportPlan(TransportPlan transportplan) {
		return transportplanrepository.save(transportplan);
	}
		
	
}
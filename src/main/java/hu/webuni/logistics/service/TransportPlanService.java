package hu.webuni.logistics.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.logistics.model.Address;
import hu.webuni.logistics.model.TransportPlan;
import hu.webuni.logistics.repository.TransportPlanRepository;

@Service
public class TransportPlanService {

	@Autowired
	TransportPlanRepository transportplanRepository;
	
	@Transactional
	public TransportPlan addTransportPlan(TransportPlan transportplan) {
		return transportplanRepository.save(transportplan);
	}
	
	 public Optional<TransportPlan> findById(long id) {
			return transportplanRepository.findById(id);
	 }
		
	
}
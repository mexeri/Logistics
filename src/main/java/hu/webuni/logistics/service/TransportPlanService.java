package hu.webuni.logistics.service;

import java.util.Optional;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.logistics.config.ConfigProperties;
import hu.webuni.logistics.model.Address;
import hu.webuni.logistics.model.TransportPlan;
import hu.webuni.logistics.repository.TransportPlanRepository;


@Service
public class TransportPlanService {

	
	@Autowired
	ConfigProperties config;
	
	@Autowired
	TransportPlanRepository transportplanRepository;
	
	@Transactional
	public TransportPlan addTransportPlan(TransportPlan transportplan) {
		return transportplanRepository.save(transportplan);
	}
	
	 public Optional<TransportPlan> findById(long id) {
			return transportplanRepository.findById(id);
	 }
		
	 @Transactional
	 public void SetExpectedIncome(TransportPlan transportplan, int delay) {
		 TreeMap<Integer, Double> limits = config.getLimits();
		 Entry<Integer, Double> floorEntry = limits.floorEntry(delay);
		 transportplan.setExpectedIncome(transportplan.getExpectedIncome()*((100-floorEntry.getValue())/100));
		 transportplanRepository.save(transportplan);
	 }
	
}
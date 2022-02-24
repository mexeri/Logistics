package hu.webuni.logistics.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.logistics.model.Address;
import hu.webuni.logistics.model.Milestone;
import hu.webuni.logistics.repository.MilestoneRepository;

@Service
public class MilestoneService {

	@Autowired 
	MilestoneRepository milestoneRepository;
	
	@Transactional
	public Milestone addMilestone(Milestone milestone) {
		return milestoneRepository.save(milestone);
	}
	
	 public List<Milestone> findAll() {
		 return milestoneRepository.findAll();
	 }
	 
	
	public void RemoveAddressFK(long lAddressId)
	{
		List<Milestone> milestones = findAll();
		for (Milestone milestone : milestones) {
			if(milestone.getAddress().getId()==lAddressId)milestone.setAddress(null);
		}
				
	}
	
}

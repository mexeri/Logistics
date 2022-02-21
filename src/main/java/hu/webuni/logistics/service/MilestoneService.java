package hu.webuni.logistics.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.logistics.model.Milestone;
import hu.webuni.logistics.repository.MilestoneRepository;

@Service
public class MilestoneService {

	@Autowired 
	MilestoneRepository milestonerepository;
	
	@Transactional
	public Milestone addMilestone(Milestone milestone) {
		return milestonerepository.save(milestone);
	}
}

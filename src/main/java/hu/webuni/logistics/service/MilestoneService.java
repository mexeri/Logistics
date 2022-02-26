package hu.webuni.logistics.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.conditional.IfAction;
import hu.webuni.logistics.model.Address;
import hu.webuni.logistics.model.Milestone;
import hu.webuni.logistics.model.Section;
import hu.webuni.logistics.model.TransportPlan;
import hu.webuni.logistics.repository.MilestoneRepository;

@Service
public class MilestoneService {

	@Autowired
	TransportPlanService transportPlanService;
	
	@Autowired 
	MilestoneRepository milestoneRepository;
	
	@Transactional
	public Milestone addMilestone(Milestone milestone) {
		return milestoneRepository.save(milestone);
	}
	
	 public List<Milestone> findAll() {
		 return milestoneRepository.findAll();
	 }
	 
	 public Optional<Milestone> findById(long id) {
			return milestoneRepository.findById(id);
	 }
	 
	
	 public void RemoveAddressFK(long lAddressId) {
		List<Milestone> milestones = findAll();
			for (Milestone milestone : milestones) {
				if (milestone.getAddress() != null)
					if (milestone.getAddress().getId() == lAddressId)
						milestone.setAddress(null);
			}

		}
	 
	 @Transactional
	 public Boolean SetDelay(long lTpId, long lMilestoneId, int iDelay) {
			Boolean bMsfoundinTp = false;
			Section section;

			// van-e ilyen tplan
			TransportPlan transportPlan = transportPlanService.findById(lTpId)
					.orElseThrow(() -> new NoSuchElementException());

			// van-e ilyen milestone a DB-ben
			if (!findAll().stream().anyMatch(m -> m.getId() == lMilestoneId))
				throw new NoSuchElementException();

			// benne van-e valamelyik section FROM milestonejában
			section = SectionFilter(transportPlan, m -> m.getFromMilestone().getId() == lMilestoneId);

			if (section != null) {
				bMsfoundinTp = true;
				SetMilestone(section.getFromMilestone(), iDelay);
				SetMilestone(section.getToMilestone(), iDelay);			
			} else {// benne van-e vmelyik section TO milestonejában
				
				section = SectionFilter(transportPlan,m -> m.getToMilestone().getId() == lMilestoneId);
						
				if (section != null) {
					bMsfoundinTp = true;
					SetMilestone(section.getToMilestone(), iDelay);
					int iNextSNr = section.getNumberinPlan() + 1;

					section = SectionFilter(transportPlan, m -> m.getNumberinPlan() == iNextSNr);
							
					if (section != null)
						SetMilestone(section.getFromMilestone(), iDelay);
				}

			}
			return bMsfoundinTp;
		}
		
		private Section SectionFilter(TransportPlan transportPlan, Predicate<? super Section> predicate) {
			return transportPlan.getSections().stream().filter(predicate).findFirst().get();			
		}
		
		private void SetMilestone(Milestone milestone, int delay) {
			if(milestone!=null) milestone.setPlanneDateTime(milestone.getPlanneDateTime().plusMinutes(delay));
		}
	 
	 
	 
	
}

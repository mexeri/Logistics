package hu.webuni.logistics.service;

import java.time.LocalDateTime;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.logistics.model.Address;
import hu.webuni.logistics.model.Milestone;
import hu.webuni.logistics.model.Section;
import hu.webuni.logistics.model.TransportPlan;

@Service
public class InitDBService {

	@Autowired
	AddressService addressservice;

	@Autowired
	TransportPlanService transportplanservice;

	@Autowired
	SectionService sectionservice;

	@Autowired
	MilestoneService milestoneservice;

	public Long FillDatabase(Double dExpectedIncome, String sExtra, int iIterationLimit, LocalDateTime dtStartTime) {
		TransportPlan transportplan = transportplanservice.addTransportPlan(new TransportPlan(dExpectedIncome));
		Section section = new Section();
		Milestone milestone = new Milestone();
		Address address = new Address();
		int iSectionNuminPlan = 0;

		for (int i = 0; i < iIterationLimit; i++) {

			if (i % 2 == 0) {
				if (i == 0)
					address = SetAddress(sExtra, i);
				milestone = SetMilestone(i, address,dtStartTime);
				section = new Section(iSectionNuminPlan, transportplan, milestone, null);
				iSectionNuminPlan++;
			} else {
				address = SetAddress(sExtra, i);
				milestone = SetMilestone(i, address,dtStartTime);
				section.setToMilestone(milestone);
				sectionservice.addSection(section);
				transportplan.setSections(section);
			}

		}

		return transportplan.getId();

	}

	private Milestone SetMilestone(int i, Address address, LocalDateTime startTime) {
		Milestone milestone = new Milestone(address, startTime.plusHours(i));
		milestoneservice.addMilestone(milestone);
		return milestone;
	}

	private Address SetAddress(String sExtra, int i) {
		Address address = new Address("HUN", "City" + sExtra + Integer.toString(i),
				"Street" + sExtra + Integer.toString(i), 1000 + i, 1 + i, 15.2 + i, 10.3 + i);
		addressservice.addAddress(address);
		return address;
	}

}

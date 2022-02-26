package hu.webuni.logistics;

import java.io.Console;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.logistics.model.Address;
import hu.webuni.logistics.model.Milestone;
import hu.webuni.logistics.model.Section;
import hu.webuni.logistics.model.TransportPlan;
import hu.webuni.logistics.service.AddressService;
import hu.webuni.logistics.service.MilestoneService;
import hu.webuni.logistics.service.SectionService;
import hu.webuni.logistics.service.TransportPlanService;

@SpringBootApplication
public class LogisticsApplication implements CommandLineRunner {
	
	@Autowired
	AddressService addressservice;
	
	@Autowired
	TransportPlanService transportplanservice;

	@Autowired
	SectionService sectionservice;
	
	@Autowired
	MilestoneService milestoneservice;
	
	public static void main(String[] args) {
		SpringApplication.run(LogisticsApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

//		Address address = new Address("HUN", "Budapest","Villanyi ut", 1113, 24, 15.582, 23.569);
//		addressservice.save(address);
//		TransportPlan transportplanA = FillDatabase(new TransportPlan(5000),"A", 10);
		TransportPlan transportplanB = FillDatabase(new TransportPlan(100000),"B", 20);
		
		
		//System.in.read();
		
	}

	private TransportPlan FillDatabase(TransportPlan transportplan,String sExtra, int iIterationLimit)
	{
		transportplanservice.addTransportPlan(transportplan);
		Section section= new Section();
		Milestone milestone = new Milestone();
		Address address = new Address();
		int iSectionNuminPlan=0;
		
		for(int i=0; i<iIterationLimit;i++)
		{
		
			if(i % 2 == 0)
			{
				if (i == 0) address = SetAddress(sExtra, i);
				milestone = SetMilestone(i, address);				
				section = new Section(iSectionNuminPlan,transportplan,milestone,null);
				iSectionNuminPlan++;
			}
			else {
				address = SetAddress(sExtra, i);
				milestone = SetMilestone(i,address);
				section.setToMilestone(milestone);
				sectionservice.addSection(section);
				transportplan.setSections(section);
			}			
			
		}
		
		return transportplan;
		
	}

	private Milestone SetMilestone(int i, Address address) {
		Milestone milestone  = new Milestone(address, LocalDateTime.now().plusHours(i));
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

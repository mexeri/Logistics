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
import hu.webuni.logistics.service.InitDBService;
import hu.webuni.logistics.service.MilestoneService;
import hu.webuni.logistics.service.SectionService;
import hu.webuni.logistics.service.TransportPlanService;

@SpringBootApplication
public class LogisticsApplication implements CommandLineRunner {
	
	@Autowired
	InitDBService initDbService;
	
	public static void main(String[] args) {
		SpringApplication.run(LogisticsApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		
		initDbService.FillDatabase(5000.0,"A", 10,LocalDateTime.of(2022, 2, 28, 10, 0));
		initDbService.FillDatabase(100000.2,"B", 20,LocalDateTime.of(2022, 3, 1, 12, 0));
		
		
		//System.in.read();
		
	}

	
	

}

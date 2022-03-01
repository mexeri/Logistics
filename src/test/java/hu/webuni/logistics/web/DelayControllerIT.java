package hu.webuni.logistics.web;

import java.time.LocalDateTime;
import java.util.TreeMap;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;


import hu.webuni.logistics.config.ConfigProperties;
//import hu.webuni.logistics.config.TestSecurityConfig;
import hu.webuni.logistics.dto.DelayDto;
import hu.webuni.logistics.model.Section;
import hu.webuni.logistics.model.TransportPlan;
import hu.webuni.logistics.repository.TransportPlanRepository;
import hu.webuni.logistics.service.InitDBService;
import hu.webuni.logistics.service.TransportPlanService;

// testsecurity profile
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })

public class DelayControllerIT {
	
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	ConfigProperties config;
	
	@Autowired
	InitDBService initDBService;
	
//	@Autowired
//	TransportPlanService transportPlanService;
	
	@Autowired
	TransportPlanRepository tPlanRepository;
	
	@Test
	void testtThatIncomeReducesandTimeIncreases() throws Exception {
		double dOriginalIncome = 5000.0;
		int iDelayLength = 30;
		LocalDateTime lTestTime = LocalDateTime.of(2022,3,5,13,0);
		Section firstSection;
				
		Long tpId = initDBService.FillDatabase(dOriginalIncome, "Test", 10,lTestTime);
		TransportPlan tpBefore = tPlanRepository.getEagerTransportPlan(tpId);
		firstSection = tpBefore.getSections().stream().filter(m -> m.getNumberinPlan() == 0).findFirst().get();
		LocalDateTime fromTimeBefore = firstSection.getFromMilestone().getPlanneDateTime();
		LocalDateTime toTimeBefore = firstSection.getToMilestone().getPlanneDateTime();
		
		DelayDto delayDto = new DelayDto(firstSection.getFromMilestone().getId(),iDelayLength);		
		createTestDelay(delayDto,tpBefore.getId());
		
		TransportPlan tpAfter = tPlanRepository.getEagerTransportPlan(tpId);
		firstSection = tpAfter.getSections().stream().filter(m -> m.getNumberinPlan() == 0).findFirst().get();
		LocalDateTime fromTimeAfter = firstSection.getFromMilestone().getPlanneDateTime();
		LocalDateTime toTimeAfter = firstSection.getToMilestone().getPlanneDateTime();
		
		assertThat(fromTimeAfter).isEqualTo(fromTimeBefore.plusMinutes(iDelayLength));
		assertThat(toTimeAfter).isEqualTo(toTimeBefore.plusMinutes(iDelayLength));
		assertThat(tpAfter.getExpectedIncome()).isEqualTo(GetReducedIncome(dOriginalIncome,iDelayLength));
			
		
	}
	
	private void createTestDelay(DelayDto testDelay, Long id) {
		
		String URI = "api/transportPlans/"+id.toString()+"/delay";
		webTestClient
			.post()
			.uri(URI)
			.bodyValue(testDelay)
			.exchange()
			.expectStatus()
			.isOk();
	}
	
		
	
	
	
	private double GetReducedIncome(double originalIncome, int delay)
	{
		 TreeMap<Integer, Double> limits = config.getLimits();
		 Entry<Integer, Double> floorEntry = limits.floorEntry(delay);
		 return originalIncome*((100-floorEntry.getValue())/100);
		
	}
	
	
	
}

package hu.webuni.logistics.web;


import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.logistics.dto.DelayDto;
import hu.webuni.logistics.service.MilestoneService;

@RestController
@RequestMapping("/api/transportPlans")
public class DelayController {
	
	@Autowired
	MilestoneService milestoneService;
	
	
	@PostMapping("/{id}/delay")
	public DelayDto SetDelay(@PathVariable long id, @RequestBody DelayDto delayDto) {
		try {
			
		if(!milestoneService.SetDelay(id,delayDto.milestoneId,delayDto.delayLength))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		
		}catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return delayDto;
	}
	

	
}

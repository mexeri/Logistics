package hu.webuni.logistics.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.logistics.model.Section;
import hu.webuni.logistics.repository.SectionRepository;

@Service
public class SectionService {

	@Autowired
	SectionRepository sectionrepository;
	
	@Transactional
	public Section addSection(Section section) {
		return sectionrepository.save(section);
	}
}

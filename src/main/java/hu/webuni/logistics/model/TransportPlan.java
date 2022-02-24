package hu.webuni.logistics.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TransportPlan {

	@Id
	@GeneratedValue
	private Long id;
	
	private Long expectedIncome;


	//@JsonIgnore
	@OneToMany(mappedBy = "transportplan")
	private List<Section> sections;


	public TransportPlan(long expectedIncome) {
		this.expectedIncome = expectedIncome;
	}
	
	
	public Long getExpectedIncome() {
		return expectedIncome;
	}


	public void setExpectedIncome(Long expectedIncome) {
		this.expectedIncome = expectedIncome;
	}


	public Long getId() {
		return id;
	}



	public List<Section> getSections() {
		return sections;
	}

	
	public void setSections(Section section) {
		if(this.sections == null)
			this.sections = new ArrayList<>();
		
		this.sections.add(section);
		section.setTransportplan(this);
		
	}


}

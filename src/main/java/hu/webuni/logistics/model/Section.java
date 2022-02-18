package hu.webuni.logistics.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Section {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne // ez egy foreign key a transportplan táblából
	private TransportPlan transportplan;
	
	private Milestone fromMilestone;
	
}

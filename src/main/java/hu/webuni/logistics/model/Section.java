package hu.webuni.logistics.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Section {

	@Id
	@GeneratedValue
	private Long id;
	
	private int numberinPlan;
	
	@ManyToOne // ez egy foreign key a transportplan táblából
	private TransportPlan transportplan;
	
	@ManyToOne
	private Milestone fromMilestone;
	
	@ManyToOne
	private Milestone toMilestone;

	
	public Section() {
		
	}


	public Section(int numberinPlan, TransportPlan transportplan, Milestone fromMilestone, Milestone toMilestone) {
		this.numberinPlan = numberinPlan;
		this.transportplan = transportplan;
		this.fromMilestone = fromMilestone;
		this.toMilestone = toMilestone;
	}
	


	public Long getId() {
		return id;
	}


	public int getNumberinPlan() {
		return numberinPlan;
	}

	public void setNumberinPlan(int numberinPlan) {
		this.numberinPlan = numberinPlan;
	}

	public TransportPlan getTransportplan() {
		return transportplan;
	}

	public void setTransportplan(TransportPlan transportplan) {
		this.transportplan = transportplan;
	}

	public Milestone getFromMilestone() {
		return fromMilestone;
	}

	public void setFromMilestone(Milestone fromMilestone) {
		this.fromMilestone = fromMilestone;
	}

	public Milestone getToMilestone() {
		return toMilestone;
	}

	public void setToMilestone(Milestone toMilestone) {
		this.toMilestone = toMilestone;
	}
	
}

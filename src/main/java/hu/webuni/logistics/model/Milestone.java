package hu.webuni.logistics.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Milestone {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Address address;
	
	private LocalDateTime plannedDateTime;

	public Milestone(Address address, LocalDateTime planneDateTime) {
		this.address = address;
		this.plannedDateTime = planneDateTime;
	}
	
	public Milestone() {
		
	}

	public long getId() {
		return id;
	}	
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDateTime getPlanneDateTime() {
		return plannedDateTime;
	}

	public void setPlanneDateTime(LocalDateTime planneDateTime) {
		this.plannedDateTime = planneDateTime;
	}


}

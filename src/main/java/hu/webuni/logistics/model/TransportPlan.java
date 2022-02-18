package hu.webuni.logistics.model;

import java.util.HashSet;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class TransportPlan {

	@Id
	@GeneratedValue
	private long id;
	
	//@JsonIgnore
	@OneToMany(mappedBy = "transportplan")
	private HashSet<Section> sections;

	
}

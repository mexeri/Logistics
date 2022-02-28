package hu.webuni.logistics.service;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.logistics.model.Address;
import hu.webuni.logistics.model.Address_;

public class AddressSpecifications {

	public static Specification<Address> haspostCode(Integer postCode) {
		return (root, cq, cb) -> cb.equal(root.get(Address_.postCode),postCode);
	}
	
	public static Specification<Address> hasISO(String countryISO) {
		return (root, cq, cb) -> cb.equal(root.get(Address_.isoCode),countryISO);
	}

	public static Specification<Address> hascity(String city) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.city)),(city + "%").toLowerCase());
	}

	public static Specification<Address> hasstreet(String street) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.street)),(street + "%").toLowerCase());
	}



}

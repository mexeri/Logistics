package hu.webuni.logistics.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.logistics.model.Address;
import hu.webuni.logistics.repository.AddressRepository;


@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	@Transactional
	public Address addAddress(Address address) {
		return addressRepository.save(address);
	}
	
	
}

package hu.webuni.logistics.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.logistics.model.Address;
import hu.webuni.logistics.repository.AddressRepository;
import net.bytebuddy.asm.Advice.Return;


@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	MilestoneService milestoneService;
	
	@Transactional
	public Optional<Address> addAddress(Address address) {
		if(!CheckAddress(address))return Optional.empty();
		return Optional.ofNullable(addressRepository.save(address));
	}
	
	private Boolean CheckAddress(Address address) {
		if (address == null)return false;			
		if (address.getId() != null)return false;
		if (address.getIsoCode() == null || address.getIsoCode() == "")return false;
		if (address.getCity() == null || address.getCity() == "")return false;
		if (address.getPostCode() == null)return false;
		if (address.getStreet() == null || address.getStreet() == "")return false;
		if (address.getHouseNumber() == null)return false;

		return true;
	}
	
	 public List<Address> findAll() {
		 return addressRepository.findAll();
	 }
	 
	 public Optional<Address> findById(long id) {
			return addressRepository.findById(id);
		}
	 
	 @Transactional
	 public void delete(long id) {
		 milestoneService.RemoveAddressFK(id);
		 addressRepository.deleteById(id);		 
		 
	 }
	 
	
}

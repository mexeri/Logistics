package hu.webuni.logistics.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
		if(!CheckAddress(address,null))return Optional.empty();
		return Optional.ofNullable(addressRepository.save(address));
	}
	
	@Transactional
	public Optional<Address> updateAddress(Address address, long pathId) {
		if(!addressRepository.existsById(pathId))throw new NoSuchElementException();
		if(!CheckAddress(address,pathId))return Optional.empty();
		address.setId(pathId);
		return Optional.ofNullable(addressRepository.save(address));
	}
	
	
	
	
	
	private Boolean CheckAddress(Address address, Long lPathId) {
		if (address == null)return false;			
		if (address.getId() != null)
		{
			if(lPathId==null)return false;
			else if(lPathId!=address.getId()) return false;					
		}			
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
		 if(addressRepository.existsById(id)) {
		 milestoneService.RemoveAddressFK(id);//foreign key eltávolítása milestoneból, h lehessen törölni
		 addressRepository.deleteById(id);		
		 }
				 
	 }
	 
	 public Page<Address> findAddressbyExample(Address example, Pageable page){
		 
		 Integer postCode = example.getPostCode();
		 String countryISO = example.getIsoCode();
		 String city = example.getCity();
		 String street = example.getStreet();
		 
		 Specification<Address> spec = Specification.where(null);
		 if(postCode!=null)
			 spec = spec.and(AddressSpecifications.haspostCode(postCode));
		 if(StringUtils.hasText(countryISO))
			 spec = spec.and(AddressSpecifications.hasISO(countryISO));
		 if(StringUtils.hasText(city))
			 spec = spec.and(AddressSpecifications.hascity(city));
		 if(StringUtils.hasText(street))
			 spec = spec.and(AddressSpecifications.hasstreet(street));
		 		 
		 
		 return addressRepository.findAll(spec, page);
	 }
	 
	 
}

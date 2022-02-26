package hu.webuni.logistics.web;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.logistics.model.Address;
import hu.webuni.logistics.dto.AddressDto;
import hu.webuni.logistics.mapper.AddressMapper;
import hu.webuni.logistics.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	AddressService addresService;
	AddressMapper addressMapper;
	
	public AddressController(AddressService addresservice, AddressMapper addressMapper) {
		this.addresService = addresservice;
		this.addressMapper = addressMapper;
	}
	
	@GetMapping
	public List<AddressDto> getall(){
		return addressMapper.addressesToDtos(addresService.findAll());
	}
	
	@GetMapping("/{id}")
	public AddressDto getById(@PathVariable long id)
	{
		Address address = addresService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		return addressMapper.addressToDto(address);
	}
	
	 @PostMapping
	 public AddressDto createAddress(@RequestBody AddressDto addressDto)
	 {
		 Address address = addresService.addAddress(addressMapper.dtoToAddress(addressDto))
				 .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
		 return addressMapper.addressToDto(address);
	 }
	 

	 @PutMapping("/{id}")
	 public AddressDto modifyAddress(@PathVariable long id, @RequestBody AddressDto addressDto)
	 {
		 
		 try {
			 Address address = addresService.updateAddress(addressMapper.dtoToAddress(addressDto),id)
					 .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
			 
			 return addressMapper.addressToDto(address);
			 
		 }catch (NoSuchElementException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		 
		 
		 
		 
	 }
	 
	 @DeleteMapping("/{id}")
	 public void deleteAddress(@PathVariable long id) {
		 addresService.delete(id);

		
	 }
}

package hu.webuni.logistics.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.logistics.dto.AddressDto;
import hu.webuni.logistics.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	List<AddressDto> addressesToDtos(List<Address> addresses);
	
	AddressDto addressToDto(Address address);
	
	Address dtoToAddress(AddressDto addressDto);
}

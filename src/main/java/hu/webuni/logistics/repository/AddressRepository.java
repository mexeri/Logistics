package hu.webuni.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hu.webuni.logistics.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}

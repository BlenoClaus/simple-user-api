package br.com.registration.core.repository;

import br.com.registration.core.model.Address;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {
}

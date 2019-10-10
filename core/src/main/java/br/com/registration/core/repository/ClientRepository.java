package br.com.registration.core.repository;

import br.com.registration.core.model.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

}

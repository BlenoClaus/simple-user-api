package br.com.registration.user.client.endpoint.service;

import br.com.registration.core.model.Client;
import br.com.registration.core.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Iterable<Client> list (Pageable pageable) {
        log.info("Listing all clients");
        return clientRepository.findAll(pageable);
    }

}

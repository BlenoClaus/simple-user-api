package br.com.registration.user.client.endpoint.service;

import br.com.registration.core.model.Client;
import br.com.registration.core.repository.ClientRepository;
import br.com.registration.user.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Iterable<Client> list (Pageable pageable) {
        log.info("Listing all clients");
        return clientRepository.findAll(pageable);
    }

    public Client find(Long id) {
        log.info(String.format("searching client: %d", id));
        return clientRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Client not found: %d", id)));
    }

    public void delete(Long id) {
        clientRepository
                .findById(id)
                .ifPresent(client -> clientRepository.deleteById(id));
    }
}

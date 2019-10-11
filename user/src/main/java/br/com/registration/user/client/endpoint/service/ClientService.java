package br.com.registration.user.client.endpoint.service;

import br.com.registration.core.dto.ClientNewDto;
import br.com.registration.core.model.Address;
import br.com.registration.core.model.Client;
import br.com.registration.core.repository.AddressRepository;
import br.com.registration.core.repository.ClientRepository;
import br.com.registration.user.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientService {

    @Autowired private ClientRepository clientRepository;
    @Autowired private AddressRepository addressRepository;

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
        log.info("deleting client . . .");
        clientRepository
                .findById(id)
                .ifPresentOrElse(client -> clientRepository.deleteById(id),
                () -> {
                    throw new ObjectNotFoundException(String.format("Client not deleted: %d", id));
                });
    }

    public Client fromDto(ClientNewDto clientNewDto) {
        log.info("starting converting ClientDto to Client");
        Client client = new Client().builder()
                .id(null)
                .cpf(clientNewDto.getCpf())
                .name(clientNewDto.getName())
                .build();
        List<Address> allAddress = clientNewDto
                .getAllAddress()
                .stream()
                .map(dto -> new Address(dto))
                .collect(Collectors.toList());
        allAddress.forEach(address -> address.setClient(client));
        client.setAllAddress(allAddress);
        log.info("conversion completed");
        return client;
    }

    public Client insert(Client client) {
        log.info("recording client . . .");
        return clientRepository.save(client);
    }

    public Client update(Client client) {
        Client clientFound = find(client.getId());
        updateClient(clientFound, client);
        return clientRepository.save(clientFound);
    }

    private void updateClient(Client clientFound, Client client) {
        clientFound.setName(client.getName());
        clientFound.setCpf(client.getCpf());
    }

    public void delete(Long idClient, Long idAddress) {
        Client client = find(idClient);
        addressRepository.findById(idAddress)
                .ifPresentOrElse(address -> {
                    addressRepository.deleteById(idAddress);
                    client.getAllAddress().remove(address);
                },
                () -> {
                    throw new ObjectNotFoundException(String.format("Address not deleted: %d", idAddress));
                });
    }

    public void update(Client client, Address address) {
        Optional<Address> addressFound = client.getAllAddress()
                .stream()
                .filter(addr -> addr.getId().equals(address.getId()))
                .findFirst();
        addressFound.ifPresentOrElse(
                addressSaved -> {
                    updateAddress(addressSaved, address);
                    addressRepository.save(addressSaved);
                },
                ()-> {
                    throw new ObjectNotFoundException(String.format("Address not updated: %d", address.getId()));
                });
    }

    private void updateAddress(Address addressSaved, Address address) {
        addressSaved.setStreet(address.getStreet());
        addressSaved.setNumber(address.getNumber());
        addressSaved.setNeighborhood(address.getNeighborhood());
        addressSaved.setComplement(address.getComplement());
    }
}

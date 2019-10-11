package br.com.registration.user.client.endpoint.controller;

import br.com.registration.core.dto.AddressNewDto;
import br.com.registration.core.dto.ClientNewDto;
import br.com.registration.core.model.Address;
import br.com.registration.core.model.Client;
import br.com.registration.user.client.endpoint.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("clients")
@Slf4j
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Iterable<Client>> findAll(Pageable pageable) {
        return new ResponseEntity<>(clientService.list(pageable), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Client> find(@PathVariable Long id) {
        return ResponseEntity.ok().body(clientService.find(id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{idClient}/address/{idAddress}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long idClient, @PathVariable Long idAddress) {
        clientService.delete(idClient, idAddress);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDto clientDto){
        Client client = clientService.fromDto(clientDto);
        client = clientService.insert(client);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(client.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> update(@Valid @RequestBody ClientNewDto clientDto, @PathVariable Long id) {
        Client client = clientService.fromDto(clientDto);
        client.setId(id);
        client = clientService.update(client);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{idClient}/address/{idAddress}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> update(@Valid @RequestBody AddressNewDto addressDto, @PathVariable Long idClient, @PathVariable Long idAddress) {
        Client client = clientService.find(idClient);
        Address address = new Address(addressDto);
        address.setId(idAddress);
        clientService.update(client, address);
        return ResponseEntity.noContent().build();
    }

}

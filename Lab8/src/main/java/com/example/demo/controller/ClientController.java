package com.example.demo.controller;

import com.example.demo.model.ClientDTO;
import com.example.demo.service.ClientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        logger.info("Fetching all clients");
        List<ClientDTO> clients = clientService.findAll();
        logger.info("Found {} clients", clients.size());
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        logger.info("Fetching client with id {}", id);
        ClientDTO client = clientService.findById(id);
        if (client != null) {
            logger.info("Found client: {}", client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            logger.warn("Client with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        logger.info("Updating client {}", clientDTO);
        clientDTO.setId(id);
        ClientDTO updatedClient = clientService.save(clientDTO);
        if (updatedClient != null) {
            logger.info("Updated client: {}", updatedClient);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } else {
            logger.warn("Client with id {} not found for update", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClientById(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        logger.info("Updating client with id {}", id);
        clientDTO.setId(id);
        ClientDTO updatedClient = clientService.update(clientDTO);
        if (updatedClient != null) {
            logger.info("Updated client: {}", updatedClient);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } else {
            logger.warn("Client with id {} not found for update", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ClientDTO> addClient(@Valid @RequestBody ClientDTO clientDTO) {
        logger.info("Adding new client: {}", clientDTO);
        ClientDTO savedClient = clientService.save(clientDTO);
        logger.info("Saved client: {}", savedClient);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        logger.info("Deleting client with id {}", id);
        if (clientService.findById(id) == null) {
            logger.warn("Client with id {} not found for deletion", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            clientService.deleteById(id);
            logger.info("Deleted client with id {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}

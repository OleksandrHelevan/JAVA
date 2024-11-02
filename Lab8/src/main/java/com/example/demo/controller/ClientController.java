package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
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
    public ResponseEntity<List<Client>> getAllClients() {
        logger.info("Fetching all clients");
        List<Client> clients = clientService.findAll();
        logger.info("Found {} clients", clients.size());
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        logger.info("Fetching client with id {}", id);
        Client client = clientService.findById(id);
        if (client != null) {
            logger.info("Found client: {}", client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            logger.warn("Client with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Client> updateClientById(@PathVariable Long id, @RequestBody Client client) {
        logger.info("Updating client with id {}", id);
        Client updatedClient = clientService.update(id, client);
        if (updatedClient != null) {
            logger.info("Updated client: {}", updatedClient);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } else {
            logger.warn("Client with id {} not found for update", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        logger.info("Adding new client: {}", client);
        Client savedClient = clientService.save(client);
        logger.info("Saved client: {}", savedClient);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateCurrentClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        logger.info("Updating current client with id {}", id);
        Client existingClient = clientService.findById(id);
        if (existingClient != null) {
            updatedClient.setId(id);
            Client savedClient = clientService.save(updatedClient);
            logger.info("Updated existing client: {}", savedClient);
            return new ResponseEntity<>(savedClient, HttpStatus.OK);
        }
        logger.warn("Client with id {} not found for update", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Transactional
    public Client update(Long id, Client client) {
        return clientRepository.update(id, client);
    }

    @Transactional
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

}

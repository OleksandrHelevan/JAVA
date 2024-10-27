package com.example.Lab7.service;

import com.example.Lab7.model.Client;
import com.example.Lab7.model.Order;
import com.example.Lab7.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client getClientById(String id) {
        return clientRepository.getClientById(Integer.parseInt(id));
    }

    public void addClient(Client client) {
        clientRepository.addClient(client);
        System.out.println("Client added: " + client);
    }
   public void setClientId(Client client) {
        clientRepository.setClientId(client);
   }
   public void addOrder(Order order) {
        clientRepository.addOrder(order);
   }
}

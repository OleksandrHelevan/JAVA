package com.example.demo.repository;

import com.example.demo.model.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Client> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Client.class, id));

    }

    public Client save(Client client) {
        if (client.getId() == null) {
            entityManager.persist(client);
            return client;
        } else {
            return entityManager.merge(client);
        }
    }

    public List<Client> findAll() {
        TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM Client c", Client.class);
        return query.getResultList();
    }

    public void deleteById(Long id) {
        Client client = entityManager.find(Client.class, id);
        if (client != null) {
            entityManager.remove(client);
        }
    }

    public Client update(Long id, Client client) {
        Client existingClient = entityManager.find(Client.class, id);
        if (existingClient == null) {
            return null;
        }

        if (client.getName() != null) {
            existingClient.setName(client.getName());
        }
        if (client.getSurname() != null) {
            existingClient.setSurname(client.getSurname());
        }
        if (client.getMiddleName() != null) {
            existingClient.setMiddleName(client.getMiddleName());
        }
        if (client.getDateOfBirthday() != null) {
            existingClient.setDateOfBirthday(client.getDateOfBirthday());
        }
        if (client.getPhoneNumber() != null) {
            existingClient.setPhoneNumber(client.getPhoneNumber());
        }
        if (client.getPassword() != null) {
            existingClient.setPassword(client.getPassword());
        }

        return entityManager.merge(existingClient);
    }
}
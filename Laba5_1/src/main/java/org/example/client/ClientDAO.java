package org.example.client;

import lombok.Getter;


public class ClientDAO {
    ClientDTO client;

    public ClientDAO(ClientDTO client) {
        this.client = client;
    }

    public ClientDTO getClient() {
        return client;
    }
}

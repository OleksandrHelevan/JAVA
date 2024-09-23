package org.example;

import lombok.Getter;
import org.example.client.ClientDTO;

@Getter
public class Order{
    int id;
    double distance;
    Driver driver;
    ClientDTO client;

    public Order(int id, double distance, Driver driver, ClientDTO client) {
        this.id = id;
        this.distance = distance;
        this.driver = driver;
        this.client = client;

    }
    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", distance=" + distance + ", driver=" + driver + ", client=" + client;
    }


}

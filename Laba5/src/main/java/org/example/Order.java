package org.example;

public class Order {
    int id;
    double distance;
    Driver driver;
    Client client;

    public Order(int id, double distance, Driver driver, Client client) {
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

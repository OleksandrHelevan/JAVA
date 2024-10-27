package com.example.Lab7.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
@Scope("prototype")
public class Order {
    private int id;
    private double distance;
    private Driver driver;
    private Client client;

    public Order(int orderId, double distance, Driver driver, Client client) {
        this.id = orderId;
        this.distance = distance;
        this.driver = driver;
        this.client = client;
    }
}

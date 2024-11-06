package com.example.demo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
@Scope("prototype")
public class OrderDTO {
    private int id;
    private double distance;
    private DriverDTO driver;
    private ClientDTO client;

    public OrderDTO(int orderId, double distance, DriverDTO driver, ClientDTO client) {
        this.id = orderId;
        this.distance = distance;
        this.driver = driver;
        this.client = client;
    }
}

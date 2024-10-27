package com.example.Lab7.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor

@Component
@Scope("prototype")
public class Car {
    private int id;
    private String brand;
    private String model;
    private String color;
    private int year;
    private String fuelType;
    private String bodyType;
    private String registrationPlate;
    private int passengers;
    private int cargo;
    private Driver driver;

    public Car(int id, String brand, String model, String color, int year, String fuelType, String bodyType, String registrationPlate, int passengers, int cargo, Driver driver) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
        this.fuelType = fuelType;
        this.bodyType = bodyType;
        this.registrationPlate = registrationPlate;
        this.passengers = passengers;
        this.cargo = cargo;
        this.driver = driver;
    }

    @Override
    public String toString() {
            return brand + " " + model;
    }
}

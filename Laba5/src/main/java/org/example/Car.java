package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {
    int id;
    String brand;
    String model;
    String color;
    String fuelType;
    String bodyType;
    String registrationPlate;
    int passengers;
    int cargo;
    Driver driver;

    public Car() {
    }

    public Car(int id, String brand, String model, String color, String fuelType, String bodyType, String registrationPlate, int passengers, int cargo, Driver driver) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.fuelType = fuelType;
        this.bodyType = bodyType;
        this.registrationPlate = registrationPlate;
        this.passengers = passengers;
        this.cargo = cargo;
        this.driver = driver;
    }


}

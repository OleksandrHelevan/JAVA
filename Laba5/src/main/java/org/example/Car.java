package org.example;

public class Car {
    String brand;
    String model;
    String color;
    String fuelType;
    String bodyType;
    String registrationPlate;
    int passengers;
    int cargo;

    public Car() {
    }

    public Car(String brand, String model, String color, String fuelType, String bodyType, String registrationPlate, int passengers, int cargo) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.fuelType = fuelType;
        this.bodyType = bodyType;
        this.registrationPlate = registrationPlate;
        this.passengers = passengers;
        this.cargo = cargo;
    }

}

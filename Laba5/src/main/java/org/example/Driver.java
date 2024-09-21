package org.example;

public class Driver extends Person{
    double drivingExperience;
    String phoneNumber;
    Car Car;

    public Driver(String name, String surname, String password, String middleName, String dateOfBirth, String phoneNumber, double drivingExperience, Car car) {
        super(name, surname, middleName, dateOfBirth);
        this.phoneNumber = phoneNumber;
        this.drivingExperience = drivingExperience;
        Car = car;
    }
}

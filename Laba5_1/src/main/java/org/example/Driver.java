package org.example;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Driver extends Person{
    double drivingExperience;
    String phoneNumber;
    List<Order> orders = new ArrayList<>();

    public Driver(int id, String name, String surname, String middleName, String dateOfBirth, double drivingExperience, String phoneNumber) {
        super(id, name, surname, middleName, dateOfBirth);
        this.phoneNumber = phoneNumber;
        this.drivingExperience = drivingExperience;
    }
}

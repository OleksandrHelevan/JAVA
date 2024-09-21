package org.example;

import java.util.List;

public class Client extends Person {
    String phoneNumber;
    List<Order> orders;

    public Client(String name, String surname, String password, String middleName, String dateOfBirth, String phoneNumber,List<Order> orders) {
        super(name, surname, middleName, dateOfBirth);
        this.phoneNumber = phoneNumber;
        this.orders = orders;
    }
}

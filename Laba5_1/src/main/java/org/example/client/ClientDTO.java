package org.example.client;

import lombok.Getter;
import org.example.Order;
import org.example.Person;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ClientDTO extends Person {
    String password;
    String phoneNumber;
    List<Order> orders = new ArrayList<>();

    public ClientDTO(int id, String name, String surname, String middleName, String dateOfBirth, String phoneNumber, String password){
        super(id, name, surname, middleName, dateOfBirth);
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}

package com.example.Lab7.model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Scope("prototype")
public class Client extends Person {
    private int password;
    private String phoneNumber;

    public Client(String name, String surname, String middleName, String dateOfBirth, String phoneNumber, String password) {
        super(name, surname, middleName, dateOfBirth);
        this.phoneNumber = phoneNumber;
        this.password = password.hashCode();
    }
}

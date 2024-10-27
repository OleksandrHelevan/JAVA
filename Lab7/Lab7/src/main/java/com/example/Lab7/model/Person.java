package com.example.Lab7.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@RequiredArgsConstructor
@Component
@Scope("prototype")
public class Person {
    private int id;
    private String name;
    private String surname;
    private String middleName;
    private String dateOfBirth;

    public Person(String name, String surname, String middleName, String dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}

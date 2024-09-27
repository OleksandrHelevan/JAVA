package org.example;


import lombok.Getter;

@Getter
public class Person {
    int id;
    String name;
    String surname;
    String middleName;
    String dateOfBirth;

    public Person(int id) {
        this.id = id;
    }
    public Person(int id,String name, String surname, String middleName, String dateOfBirth) {
        this.id = id;
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

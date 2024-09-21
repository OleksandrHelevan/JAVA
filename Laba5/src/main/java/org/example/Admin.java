package org.example;

public class Admin extends Person {
    String password;
    public Admin(String name, String surname, String password, String middleName, String dateOfBirth) {
        super(name, surname, middleName, dateOfBirth);
        this.password = password;
    }

}

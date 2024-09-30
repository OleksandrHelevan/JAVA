package org.example.admin;

import lombok.Getter;
import org.example.Person;

@Getter
public class AdminDTO extends Person {
    int password;

    public AdminDTO(int id, String name, String surname, String middleName, String dateOfBirth, int password) {
        super(id, name, surname, middleName, dateOfBirth);
        this.password = password;
    }


}

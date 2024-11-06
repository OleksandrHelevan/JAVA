package com.example.demo.model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Setter
@Getter
@NoArgsConstructor
@Component
@Scope("prototype")
public class ClientDTO {
    private Long id;
    private String name;
    private String surname;
    private String middleName;
    private String dateOfBirth;
    private String phoneNumber;

    public ClientDTO(String name, String surname, String middleName, String dateOfBirth, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }
}

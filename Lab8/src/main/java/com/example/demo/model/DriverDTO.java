package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Scope("prototype")
public class DriverDTO {
    private Long id;
    private String name;
    private String surname;
    private String middleName;
    private String dateOfBirth;
    private double drivingExperience;
    private String phoneNumber;

}

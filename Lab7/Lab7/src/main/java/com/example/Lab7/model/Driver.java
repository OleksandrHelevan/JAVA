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
public class Driver extends Person {
    private double drivingExperience;
    private String phoneNumber;

}

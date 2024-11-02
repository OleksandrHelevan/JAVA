package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client", schema = "taxi_db")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name must be between 1 and 50 characters")
    @Column(name = "name")
    private String name;

    @Size(min = 2, max = 50, message = "Surname must be between 1 and 50 characters")
    @NotNull(message = "Surname cannot be null")
    @Column(name = "surname")
    private String surname;

    @Column(name = "middle_name")
    private String middleName;

    @NotNull(message = "Date of birthday cannot be null")
    @Column(name = "date_of_birthday")
    private String dateOfBirthday;

    @NotNull(message = "Phone number cannot be null")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull(message = "Password cannot be null")
    @Column(name = "password")
    private String password;
}

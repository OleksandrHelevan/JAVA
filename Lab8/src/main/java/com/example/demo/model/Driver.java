package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "driver", schema = "taxi_db")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Surname cannot be null")
    @Column(name = "surname")
    private String surname;

    @Column(name = "middle_name")
    private String middleName;

    @NotNull(message = "Date of birthday cannot be null")
    @Column(name = "date_of_birthday")
    private String dateOfBirthday;

    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "^\\+380\\d{9}$", message = "Phone number must start with +380 and have 9 additional digits")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull(message = "Experience cannot be null")
    @Min(value = 0, message = "Experience must be at least 0")
    @Column(name = "experience")
    private Double experience;
}

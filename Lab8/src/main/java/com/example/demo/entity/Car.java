package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "registration_plate")
    private String registrationPlate;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "body_type")
    private String bodyType;

    @Column(name = "passengers")
    private Byte passengers;

    @Column(name = "cargo")
    private Double cargo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Driver_driver_id")
    private Driver driver;

    @Column(name = "year")
    private Integer year;
}

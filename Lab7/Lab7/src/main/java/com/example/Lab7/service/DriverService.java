package com.example.Lab7.service;

import com.example.Lab7.model.Driver;
import com.example.Lab7.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;

    public List<Driver> getAllDrivers() {
        return driverRepository.getAll();
    }

    public Driver getDriverById(int id) {
        return driverRepository.getById(id);
    }

}

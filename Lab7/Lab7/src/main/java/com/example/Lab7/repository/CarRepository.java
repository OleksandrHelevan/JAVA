package com.example.Lab7.repository;

import com.example.Lab7.model.Car;
import com.example.Lab7.model.Driver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CarRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Car> carRowMapper = (rs, rowNum) -> {
        Driver driver = new Driver(); // заповнити дані водія
        driver.setId(rs.getInt("driver_id"));
        driver.setName(rs.getString("driver_name"));
        driver.setSurname(rs.getString("driver_surname"));
        driver.setMiddleName(rs.getString("driver_middle_name"));
        driver.setDateOfBirth(rs.getString("driver_date_of_birthday"));
        driver.setDrivingExperience(rs.getDouble("driver_experience"));
        driver.setPhoneNumber(rs.getString("driver_phone_number"));

        return new Car(
                rs.getInt("id"),
                rs.getString("brand"),
                rs.getString("model"),
                rs.getString("color"),
                rs.getInt("year"),
                rs.getString("fuel_type"),
                rs.getString("body_type"),
                rs.getString("registration_plate"),
                rs.getInt("passengers"),
                rs.getInt("cargo"),
                driver
        );
    };

    public List<Car> getAll() {
        String sql = "SELECT * FROM car";
        return jdbcTemplate.query(sql, carRowMapper);
    }
}

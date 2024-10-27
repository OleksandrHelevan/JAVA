package com.example.Lab7.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.Lab7.model.*;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DriverRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Driver> driverRowMapper = (rs, rowNum)->{
        Driver driver = new Driver();
        driver.setId(rs.getInt("driver_id"));
        driver.setName(rs.getString("name"));
        driver.setSurname(rs.getString("surname"));
        driver.setMiddleName(rs.getString("middle_name"));
        driver.setDateOfBirth(rs.getString("date_of_birthday"));
        driver.setDrivingExperience(rs.getDouble("experience"));
        driver.setPhoneNumber(rs.getString("phone_number"));
        return driver;
    };

    public List<Driver> getAll(){
        String sql = "SELECT * FROM driver";
         return jdbcTemplate.query(sql, driverRowMapper);
    }

    public Driver getById(int id){
        String sql = "SELECT * FROM driver WHERE driver_id = ?";
        return jdbcTemplate.queryForObject(sql, driverRowMapper, id);
    }


}

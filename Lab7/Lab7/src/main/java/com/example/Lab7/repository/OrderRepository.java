package com.example.Lab7.repository;

import com.example.Lab7.model.Order;
import com.example.Lab7.model.Driver;
import com.example.Lab7.model.Client;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Order> orderRowMapper = (rs, rowNum) -> {
        Driver driver = new Driver();
        driver.setId(rs.getInt("driver_id"));
        driver.setName(rs.getString("driver_name"));
        driver.setSurname(rs.getString("driver_surname"));
        driver.setMiddleName(rs.getString("driver_middle_name"));
        driver.setDateOfBirth(rs.getString("driver_date_of_birthday"));
        driver.setDrivingExperience(rs.getDouble("driver_experience"));
        driver.setPhoneNumber(rs.getString("driver_phone_number"));

        Client client = new Client();
        client.setId(rs.getInt("client_id"));
        client.setName(rs.getString("client_name"));
        client.setSurname(rs.getString("client_surname"));
        client.setMiddleName(rs.getString("client_middle_name"));
        client.setDateOfBirth(rs.getString("client_date_of_birthday"));
        client.setPhoneNumber(rs.getString("client_phone_number"));

        return new Order(rs.getInt("order_id"), rs.getDouble("distance"), driver, client);
    };

    public List<Order> getAllOrders() {
        String sql = """
    SELECT o.order_id AS order_id, o.distance,
           d.driver_id, d.name AS driver_name, d.surname AS driver_surname,
           d.middle_name AS driver_middle_name, d.date_of_birthday AS driver_date_of_birthday,
           d.experience AS driver_experience, d.phone_number AS driver_phone_number,
           c.client_id, c.name AS client_name, c.surname AS client_surname,
           c.middle_name AS client_middle_name, c.date_of_birthday AS client_date_of_birthday,
           c.phone_number AS client_phone_number
    FROM `order` o
    JOIN driver d ON o.Driver_driver_id = d.driver_id
    JOIN client c ON o.Client_client_id = c.client_id
""";
        return jdbcTemplate.query(sql, orderRowMapper);
    }

}

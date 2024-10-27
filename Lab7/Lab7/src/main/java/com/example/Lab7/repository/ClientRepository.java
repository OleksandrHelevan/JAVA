package com.example.Lab7.repository;

import com.example.Lab7.model.Client;
import com.example.Lab7.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ClientRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Client> clientRowMapper = (rs, rowNum) -> {
        Client client = new Client();
        client.setId(rs.getInt("client_id"));
        client.setName(rs.getString("name"));
        client.setSurname(rs.getString("surname"));
        client.setMiddleName(rs.getString("middle_name"));
        client.setDateOfBirth(rs.getString("date_of_birthday"));
        client.setPhoneNumber(rs.getString("phone_number"));
        client.setPassword(rs.getInt("password"));
        return client;
    };


    public Client getClientById(int id) {
        String sql = "select * from client where client_id = ? limit 1";
        return jdbcTemplate.query(sql, clientRowMapper, id).stream().findFirst().orElse(null);
    }

    public List<Client> getAll() {
        String sql = "SELECT * FROM client";
        return jdbcTemplate.query(sql, clientRowMapper);
    }

    public void addClient(Client client) {
        String sql = "INSERT INTO client(name,surname,middle_name, date_of_birthday,phone_number, password)" +
                     " VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, client.getName(), client.getSurname(), client.getMiddleName(),
                client.getDateOfBirth(), client.getPhoneNumber(), client.getPassword());
    }

    public void setClientId(Client client) {
        String sql = "SELECT client_id FROM client WHERE name = ? AND surname = ? AND middle_name = ? AND password = ? LIMIT 1";
        try {
            client.setId(jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{client.getName(), client.getSurname(), client.getMiddleName(), client.getPassword()},
                    int.class
            ));


        } catch (EmptyResultDataAccessException e) {
            System.out.println("Client not found: " + e.getMessage());
        } catch (IncorrectResultSizeDataAccessException e) {
            System.out.println("Multiple clients found with the same details: " + e.getMessage());
        }
    }

    public void addOrder(Order order){
        String sql = "INSERT INTO `order`(distance, Driver_driver_id, Client_client_id) values(?,?,?)";
        jdbcTemplate.update(sql, order.getDistance(),order.getDriver().getId(),order.getClient().getId());
    }
}

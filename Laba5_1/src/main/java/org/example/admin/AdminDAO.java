package org.example.admin;

import lombok.Getter;
import org.example.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

@Getter
public class AdminDAO {
    AdminDTO admin;

    public AdminDAO() {
    }
    public AdminDAO(AdminDTO admin) {
        this.admin = admin;
    }

//    public void addCar(){
//        Connection connection = ConnectorDB.getConnection();
//        String sql = "INSERT INTO car (car_id, brand, model, color, registration_plate, fuel_type, body_type, passengers, cargo, Driver_driver_id) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        try {
//            preparedStatement.setInt(1,car.getId());
//            preparedStatement.setString(2, car.getBrand());
//            preparedStatement.setString(3, car.getModel());
//            preparedStatement.setString(4, car.getColor());
//            preparedStatement.setString(5, car.getRegistrationPlate());
//            preparedStatement.setString(6, car.getFuelType());
//            preparedStatement.setString(7, car.getBodyType());
//            preparedStatement.setInt(8, car.getPassengers());
//            preparedStatement.setInt(9, car.getCargo());
//            preparedStatement.setInt(10,car.driver.getId());
//
//            int result = preparedStatement.executeUpdate();
//            System.out.println("Rows affected: " + result);
//        }
//        catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//
//            connection.close();
//        }
//    }
}

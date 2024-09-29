package org.example.admin;

import lombok.Getter;
import org.example.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Getter
public class AdminDAO {
    AdminDTO admin;

    public AdminDAO() {
    }
    public AdminDAO(AdminDTO admin) {
        this.admin = admin;
    }

    public void addDriver(Driver driver) {
        String configFile = "C:\\Users\\Admin\\Desktop\\JAVA\\Laba5_1\\src\\main\\resources\\config.properties";
        try {
            Connection connection = ConnectorDB.getConnection(configFile);
            String query = "INSERT INTO driver(name,surname,middle_name, date_of_birthday, experience, phone_number) VALUES (?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try{
                preparedStatement.setString(1, driver.getName());
                preparedStatement.setString(2, driver.getSurname());
                preparedStatement.setString(3, driver.getMiddleName());
                preparedStatement.setString(4, driver.getDateOfBirth());
                preparedStatement.setDouble(5, driver.getDrivingExperience());
                preparedStatement.setString(6, driver.getPhoneNumber());

                int result = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + result);
                preparedStatement.close();
                connection.close();

            }catch (SQLException e){
                System.out.println(e.getMessage());
            }finally {
                connection.close();
            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public void addCar(Car car) {
        String configFile = "C:\\Users\\Admin\\Desktop\\JAVA\\Laba5_1\\src\\main\\resources\\config.properties";
        try {
            Connection connection = ConnectorDB.getConnection(configFile);

            String query = "INSERT INTO car ( brand, model, color, registration_plate, fuel_type, body_type, passengers, cargo, Driver_driver_id, year) " +
                    "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setString(1, car.getBrand());
                preparedStatement.setString(2, car.getModel());
                preparedStatement.setString(3, car.getColor());
                preparedStatement.setString(4, car.getRegistrationPlate());
                preparedStatement.setString(5, car.getFuelType());
                preparedStatement.setString(6, car.getBodyType());
                preparedStatement.setInt(7, car.getPassengers());
                preparedStatement.setInt(8, car.getCargo());
                preparedStatement.setInt(9, car.getDriver().getId());
                preparedStatement.setInt(10, car.getYear());

                int result = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + result);
                preparedStatement.close();
                connection.close();


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}

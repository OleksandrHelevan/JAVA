package org.example;

import java.sql.*;

import java.util.*;

import org.example.Car;

public class Admin extends Person {
    String password;

    public Admin(int id, String name, String surname, String password, String middleName, String dateOfBirth) {
        super(id, name, surname, middleName, dateOfBirth);
        this.password = password;
    }
    public void addCar(Properties properties, Car car) throws SQLException {
        Connection connection = MySQLConnector.getConnection(properties);
        String sql = "INSERT INTO car (car_id, brand, model, color, registration_plate, fuel_type, body_type, passengers, cargo, Driver_driver_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        try {
            preparedStatement.setInt(1,car.getId());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setString(4, car.getColor());
            preparedStatement.setString(5, car.getRegistrationPlate());
            preparedStatement.setString(6, car.getFuelType());
            preparedStatement.setString(7, car.getBodyType());
            preparedStatement.setInt(8, car.getPassengers());
            preparedStatement.setInt(9, car.getCargo());
            preparedStatement.setInt(10,car.driver.getId());

            int result = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + result);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            connection.close();
        }
    }

    public List<Client> getListOfClient () throws Exception {
        Properties properties = new Properties();
        properties.setProperty("user", "Oleksandr");
        properties.setProperty("password", "7=TURK?upxxjKzg");
        List<Client> clients = new ArrayList<>();

        try (Connection connection = MySQLConnector.getConnection(properties);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM `client`;")) {

            while (resultSet.next()) {
                Client client = new Client(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
                    );
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
           System.out.println(e.getMessage());
            throw new Exception("Помилка при підключенні до бази даних", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

public Map<String, Double> getCars() throws Exception {
    Properties properties = new Properties();
    properties.setProperty("user", "Oleksandr");
    properties.setProperty("password", "7=TURK?upxxjKzg");

    Map<String, Double> map = new HashMap<>();

    try (Connection connection = MySQLConnector.getConnection(properties);
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT * FROM `car`;")) {

        while (resultSet.next()) {

            Car car = new Car(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4),
            resultSet.getString(5),
            resultSet.getString(6),
            resultSet.getString(7),
            resultSet.getInt(8),
            resultSet.getInt(9),
            Driver.DriverFromDB(resultSet.getInt(10),properties)
            );
            Double buff = car.getOrders().stream()
                    .mapToDouble(Order::getDistance)
                    .sum();
            map.put(car.getCar(), buff);
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        throw new Exception("DB problem", e);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return map;
}




}


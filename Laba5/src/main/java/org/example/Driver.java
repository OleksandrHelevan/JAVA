package org.example;

import lombok.Getter;

import java.sql.*;
import java.util.List;
import java.util.Properties;

@Getter
public class Driver extends Person {
    double drivingExperience;
    String phoneNumber;
    List<Order> orders;

    public Driver(int id, String name, String surname, String middleName, String dateOfBirth, double drivingExperience, String phoneNumber) {
        super(id, name, surname, middleName, dateOfBirth);
        this.phoneNumber = phoneNumber;
        this.drivingExperience = drivingExperience;
    }

   static public Driver DriverFromDB(int id, Properties properties) throws Exception {
        try (Connection connection = MySQLConnector.getConnection(properties);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM driver;")) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {

                if (resultSet.getInt(1) == id) {
                    return new Driver(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDouble(6),
                            resultSet.getString(7)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Помилка при підключенні до бази даних", e);
        }
        return null;
    }
}

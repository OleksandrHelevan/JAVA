package org.example;

import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Getter
public class Driver extends Person {
    double drivingExperience;
    String phoneNumber;

    public Driver(int id) {
        super(id);
    }
    public Driver(int id, String name, String surname, String middleName, String dateOfBirth, double drivingExperience, String phoneNumber) {
        super(id, name, surname, middleName, dateOfBirth);
        this.phoneNumber = phoneNumber;
        this.drivingExperience = drivingExperience;
    }

   static public Driver DriverFromDB(int id, Properties properties) throws Exception {
        try (Connection connection = MySQLConnector.getConnection(properties);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM driver;")) {

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

   public List<Order> getOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        Properties properties = new Properties();
        properties.setProperty("user", "Oleksandr");
        properties.setProperty("password", "7=TURK?upxxjKzg");

        try (Connection connection = MySQLConnector.getConnection(properties);
              Statement statement = connection.createStatement();
              ResultSet resultSet = statement.executeQuery("SELECT * FROM `order`;")) {

              while (resultSet.next()) {
                  if (resultSet.getInt(3) == this.id) {
                   resultSet.getInt(1);
                   resultSet.getDouble(2);
                   resultSet.getInt(3);
                   resultSet.getInt(4);
                   Client client = Client.ClientFromDB(resultSet.getInt(4), properties);
                   Order order = new Order(resultSet.getInt(1), resultSet.getDouble(2), this, client);
                   orders.add(order);
               }
           }
       } catch (SQLException e) {
          System.out.println(e.getMessage());
           throw new Exception("Помилка при підключенні до бази даних", e);
       } catch (Exception e) {
            throw new RuntimeException(e);
        }
       return orders;
   }
}

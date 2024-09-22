package org.example;

import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Getter
public class Client extends Person {
    String password;
    String phoneNumber;
    List<Order> orders = new ArrayList<>();


    public Client(int id, String name, String surname, String middleName, String dateOfBirth, String phoneNumber, String password) throws Exception {
        super(id, name, surname, middleName, dateOfBirth);
        this.phoneNumber = phoneNumber;
        setOrders();
        this.password = password;
    }
    public void setOrders() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("user", "Oleksandr");
        properties.setProperty("password", "7=TURK?upxxjKzg");

        try (Connection connection = MySQLConnector.getConnection(properties);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM `order`;")) {

            while (resultSet.next()) {

                if (resultSet.getInt(4) == this.id) {
                            resultSet.getInt(1);
                            resultSet.getDouble(2);
                            resultSet.getInt(3);
                            resultSet.getInt(4);
                    Driver driver = Driver.DriverFromDB(resultSet.getInt(3),properties);
                    Order order = new Order(resultSet.getInt(1), resultSet.getDouble(2),driver,this);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception("Помилка при підключенні до бази даних", e);
        }
    }

    public void printOrders() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void reg(){
        System.out.println("Yeeeeeeeeeeees!");
    }

    public int getOrdersNumber(){
        return orders.size();
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    static public Client ClientFromDB(int id, Properties properties) throws Exception {
        try (Connection connection = MySQLConnector.getConnection(properties);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM client;")) {

            while (resultSet.next()) {

                if (resultSet.getInt(1) == id) {
                    return new Client(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(6)
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception("Помилка при підключенні до бази даних", e);
        }
        return null;
    }

}

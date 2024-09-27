package org.example.client;

import lombok.Getter;
import org.example.ConnectorDB;
import org.example.Driver;
import org.example.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Getter
public class ClientDAO {
    ClientDTO client;
    List<Order> orders = new ArrayList<>();

    public ClientDAO(ClientDTO client) {
        this.client = client;
    }


    public void addOrder(Order order){
        orders.add(order);
    }
    public void printOrders(){
        for(Order order : orders){
            System.out.println(order);
        }
        System.out.println();
    }


    public void makeOrder(Order order){
        String configFile = "C:\\Users\\Admin\\Desktop\\JAVA\\Laba5_1\\src\\main\\resources\\config.properties";
        try {
            Connection connection = ConnectorDB.getConnection(configFile);
            String query = "INSERT INTO `order`(distance, Driver_driver_id, Client_client_id) value(?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            try {
                preparedStatement.setDouble(1, order.getDistance());
                preparedStatement.setInt(2, order.getDriver().getId());
                preparedStatement.setInt(3, this.getClient().getId());

                int result = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + result);

                this.addOrder(order);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                preparedStatement.close();
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cancelOrder(Order order) {
        String configFile = "C:\\Users\\Admin\\Desktop\\JAVA\\Laba5_1\\src\\main\\resources\\config.properties";
        try {
            Connection connection = ConnectorDB.getConnection(configFile);
            String query = "DELETE FROM `order` WHERE order_id = ? AND Client_client_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            try {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, this.getClient().getId());

                int result = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + result);

            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void changeOrder(Order order, Driver driver, double distance) {
        order.orderFromFile();
        order.setDistance(distance);
        order.setDriver(driver);
        String configFile = "C:\\Users\\Admin\\Desktop\\JAVA\\Laba5_1\\src\\main\\resources\\config.properties";
        try {
            Connection connection = ConnectorDB.getConnection(configFile);
            String query = "UPDATE `order` SET distance = ?, Driver_driver_id = ? WHERE order_id = ? AND Client_client_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            try {
                preparedStatement.setDouble(1, order.getDistance());
                preparedStatement.setInt(2, order.getDriver().getId());
                preparedStatement.setInt(3, order.getId());
                preparedStatement.setInt(4, this.getClient().getId());

                int result = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + result);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                preparedStatement.close();
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setOrderHistory() {
        String configFilename = "C:\\Users\\Admin\\Desktop\\JAVA\\Laba5_1\\src\\main\\resources\\config.properties";
        try {
            Connection connection = ConnectorDB.getConnection(configFilename);
            Statement statement = connection.createStatement();

            try (connection; statement; ResultSet resultSet = statement.executeQuery("SELECT * FROM `order`;")) {
                while (resultSet.next()) {
                    if (resultSet.getInt("Client_client_id") == this.getClient().getId()) {
                        Driver driver = Driver.driverFromDB(resultSet.getInt("Driver_driver_id"));
                        Order order = new Order(resultSet.getInt(1), resultSet.getDouble(2), driver, this.getClient());
                        order.orderFromFile();
                        this.addOrder(order);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getOrdersNumber(){
        return orders.size();
    }

    @Override
    public String toString(){
        return client.toString();
    }

}

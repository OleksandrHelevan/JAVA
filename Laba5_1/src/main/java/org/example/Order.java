package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.client.ClientDTO;

import java.sql.*;

@Setter
@Getter
public class Order{
    int id;
    double distance;
    Driver driver;
    ClientDTO client;

    public Order(int id, double distance, Driver driver, ClientDTO client) {
        this.id = id;
        this.distance = distance;
        this.driver = driver;
        this.client = client;

    }

    public void orderFromFile() {
        String configFilename = "C:\\Users\\Admin\\Desktop\\JAVA\\Laba5_1\\src\\main\\resources\\config.properties";
        try {
            Connection connection = ConnectorDB.getConnection(configFilename);
            String query = "SELECT order_id FROM `order` WHERE distance = ? AND Driver_driver_id = ? AND Client_client_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            try {
                preparedStatement.setDouble(1, this.distance);
                preparedStatement.setInt(2, this.driver.getId());
                preparedStatement.setInt(3, this.client.getId());

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next())
                    this.id = resultSet.getInt("order_id");

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

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", distance=" + distance + ", driver=" + driver + ", client=" + client;
    }


}

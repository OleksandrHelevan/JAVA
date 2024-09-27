package org.example;
import org.example.client.ClientDAO;
import org.example.client.ClientDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class Menu {

    public Map<String, Double> getCars() throws Exception {
        String configFilename = "C:\\Users\\Admin\\Desktop\\JAVA\\Laba5_1\\src\\main\\resources\\config.properties";
        Map<String, Double> map = new HashMap<>();

        try (Connection connection = ConnectorDB.getConnection(configFilename);
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
                        Driver.driverFromDB(resultSet.getInt(10))
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

    public List<ClientDAO> getListOfClient() throws Exception {
        List<ClientDAO> clients = new ArrayList<>();
        String configFile = "C:\\Users\\Admin\\Desktop\\JAVA\\Laba5_1\\src\\main\\resources\\config.properties";

        try (Connection connection = ConnectorDB.getConnection(configFile);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM `client`;")) {

            while (resultSet.next()) {
                ClientDAO client = new ClientDAO(new ClientDTO(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                ));
                client.setOrderHistory();
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception("DB problem", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

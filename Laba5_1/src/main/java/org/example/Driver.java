package org.example;
import lombok.Getter;
import org.example.client.ClientDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



@Getter
public class Driver extends Person{
    double drivingExperience;
    String phoneNumber;
    List<Order> orders = new ArrayList<>();

    public Driver(int id, String name, String surname, String middleName, String dateOfBirth, double drivingExperience, String phoneNumber) {
        super(id, name, surname, middleName, dateOfBirth);
        this.phoneNumber = phoneNumber;
        this.drivingExperience = drivingExperience;
    }

    static public Driver driverFromDB(int id) throws Exception {
        String configFile = "C:\\Users\\Admin\\Desktop\\JAVA\\Laba5_1\\src\\main\\resources\\config.properties";

        try (Connection connection = ConnectorDB.getConnection(configFile);
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
            System.out.println(e.getMessage());
            throw new Exception("Помилка при підключенні до бази даних", e);
        }
        return null;
    }

    public List<Order> getOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        String configFile = "C:\\Users\\Admin\\Desktop\\JAVA\\Laba5_1\\src\\main\\resources\\config.properties";

        try (Connection connection = ConnectorDB.getConnection(configFile);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM `order`;")) {

            while (resultSet.next()) {
                if (resultSet.getInt(3) == this.id) {
                    resultSet.getInt(1);
                    resultSet.getDouble(2);
                    resultSet.getInt(3);
                    resultSet.getInt(4);
                    ClientDTO client = ClientDTO.ClientFromDB(resultSet.getInt(4));
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


package org.example;

import java.sql.*;
import java.util.Properties;

public class Authenticator {


    public Authenticator() {}

    public Client authenticate(Properties properties, String name, String password) throws Exception {
        try (Connection connection = MySQLConnector.getConnection(properties);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM client")) {

            while (resultSet.next()) {

                if (resultSet.getString(2).equals(name) && resultSet.getString(7).equals(password)) {
                    return new Client(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
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
}

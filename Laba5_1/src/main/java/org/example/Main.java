package org.example;

import java.sql.*;
import java.util.Properties;


public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Javadb";
        String user = "Oleksandr";
        String password = "7=TURK?upxxjKzg";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("INSERT INTO users (id, name, surname) VALUES (1,'John','Snow')");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
            }
            resultSet.close();
            statement.close();
            connection.close();

            System.out.println("Підключення успішне!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
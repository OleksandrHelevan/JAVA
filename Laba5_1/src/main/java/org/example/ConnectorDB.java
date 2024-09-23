package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectorDB {

    static Properties readConfiguration(String fileName) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException e) {
            System.out.println("Error! " + e.getMessage());
        }
        return properties;
    }

        public static Connection getConnection(String fileName) throws SQLException {
        Properties properties = readConfiguration(fileName);
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/taxi_db",
                    properties.getProperty("username"),properties.getProperty("password"));
        }

}

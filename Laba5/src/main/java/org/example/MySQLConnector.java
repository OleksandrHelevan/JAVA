package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnector {
    public static Connection getConnection(Properties properties) throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/taxi_db", properties);
    }

}
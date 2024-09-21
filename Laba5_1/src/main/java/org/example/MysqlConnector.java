package org.example;

import com.sun.jdi.connect.spi.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlConnector {
    public static Connection getConnection(Properties properties) throws SQLException {
        return (Connection) DriverManager.getConnection(
                properties.getProperty("jdbc:mysql://localhost:3306/javadb"), properties);
    }
}



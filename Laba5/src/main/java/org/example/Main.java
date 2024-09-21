package org.example;

import java.sql.*;
import java.util.Properties;


public class Main {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/taxi_db";
        String user = "Oleksandr";
        String password = "7=TURK?upxxjKzg";
        Properties props = new Properties();
        props.put("user", user);
        props.put("password", password);
        try {
            Connection connection = MySQLConnector.getConnection(url, props);
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate
                    ("INSERT INTO client(client_id, name, surname, middle_name, date_of_birthday, phone_number)\n" +
                    "    values(2,'Roman','Romanenko','Romanovych','1989-12-04','+380689843238')");
    }
    catch (Exception e) {
        System.out.println(e);
        }
    }
}
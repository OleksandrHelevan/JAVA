package org.example.client;

import lombok.Getter;
import org.example.ConnectorDB;
import org.example.Person;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Getter
public class ClientDTO extends Person {
    int password;
    String phoneNumber;


    public ClientDTO(int id, String name, String surname, String middleName, String dateOfBirth, String phoneNumber, int password){
        super(id, name, surname, middleName, dateOfBirth);
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


    static public ClientDTO ClientFromDB(int id) throws Exception {

        String configFile = "C:\\Users\\Admin\\Desktop\\JAVA\\Laba5_1\\src\\main\\resources\\config.properties";

        try (Connection connection = ConnectorDB.getConnection(configFile);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM client;")) {

            while (resultSet.next()) {

                if (resultSet.getInt(1) == id) {
                    return new ClientDTO(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getInt(7)
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

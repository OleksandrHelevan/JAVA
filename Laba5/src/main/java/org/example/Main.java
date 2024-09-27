package org.example;


import java.util.*;
import java.util.stream.Collectors;


public class Main {
    static String userName = "Oleksandr";
    static String password = "7=TURK?upxxjKzg";
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        System.out.println(System.getenv("user_name"));
        Properties prop = new Properties();
        prop.setProperty("user", userName);
        prop.setProperty("password", password);

        Authenticator menu = new Authenticator();
        Client client = menu.authenticate(prop, "Oleksandr", "12345678");
                client.reg();
                client.printOrders();

        System.out.println();


        Driver driver = new Driver(1);
        List<Order> driverOrders = driver.getOrders();
        for (Order order : driverOrders) {
            System.out.println(order);
        }

        Admin admin = new Admin(1, userName,"Helevan", password,
                "Vitaliyovich","2006-03-15");


        Map<String, Double> map = admin.getCars();
        List<Map.Entry<String, Double>> sortedMap = map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .toList();

        for (Map.Entry<String, Double> entry : sortedMap) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();



        List<Client> clients = admin.getListOfClient();
        List<Client> sortedClients = clients.stream()
                .sorted(Comparator.comparingInt(Client::getOrdersNumber).reversed())
                .toList();
        for (Client c : sortedClients) {
            System.out.println(c + " " + c.getOrdersNumber());
        }
    }
}
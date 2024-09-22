package org.example;


import java.util.*;


public class Main {
    static String userName = "Oleksandr";
    static String password = "7=TURK?upxxjKzg";
    public static void main(String[] args) throws Exception {

        Properties prop = new Properties();
        prop.setProperty("user", userName);
        prop.setProperty("password", password);

        Authenticator menu = new Authenticator();
        Client client = menu.authenticate(prop, "Oleksandr", "12345678");
                client.reg();
                client.printOrders();
        Admin admin = new Admin(1, userName,"Helevan", password,
                "Vitaliyovich","2006-03-15");
        List<Client> clients = admin.getListOfClient();
        List<Client> sortedClients = clients.stream()
                .sorted(Comparator.comparingInt(Client::getOrdersNumber).reversed())
                .toList();
        for (Client c : sortedClients) {
            System.out.println(c + " " + c.getOrdersNumber());
        }
    }
}
package org.example;
import org.example.admin.AdminDAO;
import org.example.client.ClientDAO;
import org.example.client.ClientDTO;
import java.util.Comparator;
import java.util.List;
import java.util.Map;



public class Main {

    public static void main(String[] args) throws Exception {
        Authorizer authorizer = new Authorizer();
        AdminDAO admin = authorizer.adminAuthorization
                ("Oleksandr","12345678");

//        ClientDTO clientDTO = new ClientDTO(5,"Vova","Dobry","Vasylovich","2005-12-12","+380982345438","ooo");
//        ClientDAO client = authorizer.clientAuthorization
//                ("Oleksandr", "12345678");

//        ClientDAO client = authorizer.register(clientDTO);
//
//        Driver driver = new Driver(5,"Vova","Vovcheno","Yuriyevich","2000-10-21", 1.2,"+380983748548");
//        admin.addDriver(driver);
//
//        Car car = new Car(3,"Renault","Logan","black","dizel","universal","AO1232AT",4,300, driver);
//        admin.addCar(car);

//        Order order = new Order(15,8, driver, client.getClient());
//        client.makeOrder(order);
//
//       client.printOrders();
//        client.changeOrder(order,driver,7);
//        client.cancelOrder(order);


//        Menu menu = new Menu() {};
//        Map<String, Double> map = menu.getCars();
//        List<Map.Entry<String, Double>> sortedMap = map.entrySet()
//                .stream()
//                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
//                .toList();
//
//        for (Map.Entry<String, Double> entry : sortedMap) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//        System.out.println();
//
//        List<ClientDAO> clients = menu.getListOfClient();
//        List<ClientDAO> sortedClients = clients.stream()
//                .sorted(Comparator.comparingInt(ClientDAO::getOrdersNumber).reversed())
//                .toList();
//        for (ClientDAO c : sortedClients) {
//            System.out.println(c + " " + c.getOrdersNumber());
//        }



    }
}
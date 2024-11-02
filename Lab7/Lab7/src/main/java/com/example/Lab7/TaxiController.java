package com.example.Lab7;

import com.example.Lab7.model.Driver;
import com.example.Lab7.model.Order;
import com.example.Lab7.service.ClientService;
import com.example.Lab7.service.DriverService;
import com.example.Lab7.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class TaxiController {

    private final DriverService driverService;
    private final ClientService clientService;

    @Autowired
    public TaxiController(DriverService driverService, ClientService clientService) {
        this.driverService = driverService;
        this.clientService = clientService;
    }

    @RequestMapping("/")
    public String home() {
        System.out.println("Home method was called");
        return "register.jsp";

    }

    @RequestMapping("/make")
    public String makeOrder(@RequestParam("name") String name,
                            @RequestParam("surname") String surname,
                            @RequestParam("middleName") String middleName,
                            @RequestParam("dateOfBirth") String dateOfBirth,
                            @RequestParam("phoneNumber") String phoneNumber,
                            @RequestParam("password") String password, Model model) {

        Client client = new Client(name, surname, middleName, dateOfBirth, phoneNumber, password);
        clientService.addClient(client);
        clientService.setClientId(client);
        List<Driver> drivers = driverService.getAllDrivers();
        model.addAttribute("drivers", drivers);
        model.addAttribute("clientId", client.getId());
        return "make-order.jsp";
    }

    @RequestMapping("/made")
    public String madeOrder(@RequestParam("distance") Double distance,
                            @RequestParam("driverId") Integer driverId,
                            @RequestParam("clientId") String clientId,
                            Model model) {

        Client client = clientService.getClientById(clientId);


        Order order = new Order();
        order.setDistance(distance);
        order.setDriver(driverService.getDriverById(driverId));
        order.setClient(client);

        clientService.addOrder(order);

        String clientOut = order.getClient().getName() + " " + order.getClient().getSurname();
        String driverOut = order.getDriver().getName() + " " + order.getDriver().getSurname();
        model.addAttribute("client", clientOut);
        model.addAttribute("driver", driverOut);

        return "order-was-made.jsp";
    }
}
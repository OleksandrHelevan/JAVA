package com.example.Lab7;

import com.example.Lab7.service.DriverService;
import com.example.Lab7.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Lab7Application {

    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(Lab7Application.class, args);

//        OrderService orderService = context.getBean(OrderService.class);
//        orderService.getOrders().forEach(System.out::println);
    }

}

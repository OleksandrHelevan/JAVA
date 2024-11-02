package com.example.Lab7;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab7Application {

    public static void main(String[] args){
        SpringApplication.run(Lab7Application.class, args);

//        OrderService orderService = context.getBean(OrderService.class);
//        orderService.getOrders().forEach(System.out::println);
    }

}

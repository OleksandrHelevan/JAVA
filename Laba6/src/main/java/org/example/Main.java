package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Map<Product, Integer> products = new HashMap<>();
        Random random = new Random();

        for (int i = 1; i <= 20; i++) {
            Product product = new Product("Product " + i, random.nextDouble() * 100);
            products.put(product, random.nextInt(5)+1);
        }

        Warehouse warehouse = new Warehouse(products);
        System.out.println("Quantity before: " + warehouse.getAmountOfProducts());
        List<Client> clients = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            clients.add(new Client("Client " + i));
        }

        try{
        Market market = new Market(clients, warehouse);
        market.printProducts();
        market.shopping(products);
        market.printProducts();

        System.out.println("Was sold: " + ShoppingThread.totalShopping);
        System.out.println("Quantity after: " + warehouse.getAmountOfProducts());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Client {
    private final String name;
    private final List<Product> basket = new ArrayList<>();
    private double totalPrice = 0;

    public Client(String name) {
        this.name = name;
    }

    public void addToBasket(Product product) {
        basket.add(product);
        totalPrice += product.price();
    }

    public void removeFromBasket(Product product) {
        if (basket.remove(product)) {
            totalPrice -= product.price();
        }
    }

    public void printBasket() {
        for (Product product : basket) {
            System.out.println(product);
        }
    }
}

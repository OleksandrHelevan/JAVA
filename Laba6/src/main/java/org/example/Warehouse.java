package org.example;

import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Warehouse {
    private final Map<Product, Integer> products;

    public Warehouse(Map<Product, Integer> products) {
        this.products = new HashMap<>(products);
    }

    public synchronized boolean searchProduct(Product product) {
        if (products.containsKey(product) && products.get(product) > 0) {
            products.put(product, products.get(product) - 1);
            return true;
        }
        return false;
    }

    public synchronized void returnProduct(Product product) {
        products.put(product, products.getOrDefault(product, 0) + 1);
    }

    public synchronized void printProducts() {
        System.out.println("Available Products in Warehouse:");
        if (products.isEmpty()) {
            System.out.println("- No products available.");
        } else {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                System.out.println(" - " + entry.getKey() + " (total amount: " + entry.getValue() + ")");
            }
        }
        System.out.println();
    }
}

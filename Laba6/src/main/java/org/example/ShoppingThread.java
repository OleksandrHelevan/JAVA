package org.example;

import lombok.Getter;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class ShoppingThread extends Thread {
    private final Client client;
    private final Map<Product, Integer> products;
    private final ReentrantLock lock = new ReentrantLock();
    private final Market market;
    public static int totalShopping = 0;


    public ShoppingThread(Client client, Map<Product, Integer> products, Market market) {
        this.client = client;
        this.products = products;
        this.market = market;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
//            try {
//              Thread.sleep(random.nextInt(200));
                Random random = new Random();
                int action = random.nextInt(2);
                lock.lock();
                try {
                    if (action == 0) {
                        Product productToAdd = (Product) products.keySet().toArray()[random.nextInt(products.size())];

                        if (market.getWarehouse().searchProduct(productToAdd)) {
                            client.addToBasket(productToAdd);
                            System.out.println(client.getName() + " added " + productToAdd + " to the basket");
                            totalShopping++;
                        } else {
                            System.out.println(client.getName() + " tried to add " + productToAdd + " but this product does not exist in warehouse");
                        }
                    } else {
                        if (!client.getBasket().isEmpty()) {
                            Product productToRemove = client.getBasket().get(random.nextInt(client.getBasket().size()));
                            client.removeFromBasket(productToRemove);
                            market.getWarehouse().returnProduct(productToRemove);
                            System.out.println(client.getName() + " removed " + productToRemove + " from the basket");
                            totalShopping--;
                        }
                    }
                } finally {
                    lock.unlock();
                }

//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
        }

        market.notifyCompletion();
    }
}

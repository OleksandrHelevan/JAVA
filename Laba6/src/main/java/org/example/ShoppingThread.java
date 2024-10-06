package org.example;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class ShoppingThread extends Thread {
    private final Client client;
    private final Map<Product, Integer> products;
    private final Warehouse warehouse;
    private final Random random;
    private final ReentrantLock lock = new ReentrantLock();
    private final Market market;

    public ShoppingThread(Client client, Map<Product, Integer> products, Warehouse warehouse, Market market) {
        this.client = client;
        this.products = products;
        this.warehouse = warehouse;
        this.random = new Random();
        this.market = market;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(random.nextInt(200));
                int action = random.nextInt(2);
                lock.lock();
                try {
                    if (action == 0) {
                        Product productToAdd = (Product) products.keySet().toArray()[random.nextInt(products.size())];

                        if (warehouse.searchProduct(productToAdd)) {
                            client.addToBasket(productToAdd);
                            System.out.println(client.getName() + " added " + productToAdd + " to the basket");
                        } else {
                            System.out.println(client.getName() + " tried to add " + productToAdd + " but this product does not exist in warehouse");
                        }
                    } else {
                        if (!client.getBasket().isEmpty()) {
                            Product productToRemove = client.getBasket().get(random.nextInt(client.getBasket().size()));
                            client.removeFromBasket(productToRemove);
                            warehouse.returnProduct(productToRemove);
                            System.out.println(client.getName() + " removed " + productToRemove + " from the basket");
                        }
                    }
                } finally {
                    lock.unlock();
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        market.notifyCompletion();
    }
}

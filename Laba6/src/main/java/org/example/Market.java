package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class Market {
    List<Client> clientList;
    Warehouse warehouse;
    Random random = new Random();
    ReentrantLock lock = new ReentrantLock();
    private int completedThreads = 0;

    public Market(List<Client> clientList, Warehouse warehouse) {
        this.clientList = clientList;
        this.warehouse = warehouse;
    }

    public synchronized void notifyCompletion() {
        completedThreads++;
        if (completedThreads == clientList.size()) {
            notifyAll();
        }
    }

    public void printProducts() {
        warehouse.printProducts();
    }

    public void shopping(Map<Product, Integer> products) {
        List<ShoppingThread> threads = new ArrayList<>();

        for (Client client : clientList) {
            ShoppingThread thread = new ShoppingThread(client, products, warehouse, lock, this);
            threads.add(thread);
            thread.start();
        }

        Thread basketPrinterThread = new Thread(new BasketPrinter(this));
        basketPrinterThread.start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        notifyCompletion();

        try {
            basketPrinterThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

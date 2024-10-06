package org.example;

public class BasketPrinter extends Thread{
    private final Market market;

    public BasketPrinter(Market market) {
        this.market = market;
    }

    @Override
    public void run() {
        synchronized (market) {

            while (market.getCompletedThreads() < market.getClientList().size()) {
                try {
                    market.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        for (Client client : market.getClientList()) {
            System.out.println(client.getName() + "'s basket: ");
            client.printBasket();
            if (client.getTotalPrice() > 0) {
                System.out.println("Total price: " + client.getTotalPrice());
            } else {
                System.out.println("Total price: 0");
            }
        }
    }
}

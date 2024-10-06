package org.example;

import lombok.Getter;

import java.util.List;

@Getter
public class User extends Thread {
    private final MessageQueue messageQueue;

    public User(String name, MessageQueue messageQueue) {
        super(name);
        this.messageQueue = messageQueue;
    }

    public void sendMessage(String text) {
        Message message = new Message(text);
        messageQueue.sendMessage(message);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = messageQueue.receiveMessage();
                System.out.println(getName() + ": " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

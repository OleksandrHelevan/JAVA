package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private final Queue<Message> queue = new LinkedList<>();

    public synchronized void sendMessage(Message message) {
        queue.add(message);
        notify();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized Message receiveMessage() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.poll();
    }
}

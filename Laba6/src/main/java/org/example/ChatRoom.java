package org.example;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private final List<User> users;
    MessageQueue messageQueue;

    public ChatRoom() {
        this.users = new ArrayList<>();
        this.messageQueue = new MessageQueue();
    }

    public void addUser(User user) {
        users.add(user);
        user.start();
    }

    public void broadcastMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.sendMessage(sender.getName() + ": " + message);
            }
        }
    }

    public void stopChat() {
        for (User user : users) {
            user.interrupt();
        }
    }
}

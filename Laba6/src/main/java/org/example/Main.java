package org.example;

public class Main {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        User alice = new User("Alice", chatRoom.messageQueue);
        User bob = new User("Bob", chatRoom.messageQueue);
        User charlie = new User("Charlie", chatRoom.messageQueue);
        User dave = new User("Dave", chatRoom.messageQueue);
        User eve = new User("Eve", chatRoom.messageQueue);

        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);
        chatRoom.addUser(dave);
        chatRoom.addUser(eve);

        chatRoom.broadcastMessage("Hello from Alice!", alice);
        chatRoom.broadcastMessage("Hi everyone, this is Bob.", bob);
        chatRoom.broadcastMessage("Charlie here, how's it going?", charlie);
        chatRoom.broadcastMessage("Dave checking in!", dave);
        chatRoom.broadcastMessage("Eve says hi!", eve);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        chatRoom.stopChat();
    }
}

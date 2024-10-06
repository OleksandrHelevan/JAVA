package org.example;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class Message {
    private final String text;
    private final String date;

    public Message(String text) {
        this.text = text;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.date = now.format(formatter);
    }

    @Override
    public String toString() {
        return text + " - (" + date + ")";
    }
}

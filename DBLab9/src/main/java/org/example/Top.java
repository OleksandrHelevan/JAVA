package org.example;

import java.util.ArrayList;
import java.util.List;

public class Top {
    private final List<TopItem> topItems = new ArrayList<>();

    public void addItem(TopItem item) {
        topItems.add(item);
    }

    public void printTopItems() {
        for(TopItem topItem : topItems) {
            System.out.println(topItem.toString());
        }
    }


}

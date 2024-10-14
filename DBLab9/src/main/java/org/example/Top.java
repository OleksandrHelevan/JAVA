package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
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

package task3;

import java.util.ArrayList;
import java.util.List;

public class Box <T>{

    private final List<T> items;

    public Box(){
        items = new ArrayList<T>();
    }

    public void add(T item){
        items.add(item);
    }

    public void take(){
        items.removeLast();
    }

    public void print(){
        for(T item : items){
            System.out.print(item+" ");
        }
        System.out.println();
    }
}

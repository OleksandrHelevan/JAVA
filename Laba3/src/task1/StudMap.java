package task1;

import java.util.HashMap;
import java.util.Map;

public class StudMap {
    Map<String, Double> map;

    public StudMap() {
        map = new HashMap<>();
    }

    public void addStudent(Student s){
        map.put(s.getSurname(),s.getAveragescore());
    }

    public void printMap(){
        for(Map.Entry<String, Double> entry : map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public void removeStudent(String key){
        map.remove(key);
    }

    public void searchStudent(String key){
        if(map.containsKey(key)){
            System.out.println("Student " + key + " " + map.get(key));
        }else {
            System.out.println("Student " + key + " not found");
        }
    }
}

import task1.*;
import task3.*;
import task8.*;
import task5.*;
import task6.*;

import java.util.*;

public class Main {

    public static void shtrix(){
        System.out.println("---------------------------------");
    }

    public static void task3(){
        Box<Integer> box1 = new Box<>();
        box1.add(1);
        box1.add(2);
        box1.add(3);
        box1.print();

        Item item1 = new Item("Banana",25);
        Item item2 = new Item("Apple",20);
        Item item3 = new Item("Phone",28000);

        Box <Item> box2 = new Box<>();
        box2.add(item1);
        box2.add(item2);
        box2.add(item3);
        box2.print();

        box2.take();
        box2.print();
    }

    public static void task8(){
        AnimalShelter shelter = new AnimalShelter();
        Dog dog = new Dog();

        shelter.addAnimal(dog);
        Cat cat = new Cat();

        shelter.addAnimal(cat);
        Labrador labrador = new Labrador();
        shelter.addAnimal(labrador);

        shelter.printAnimalSounds();

        shelter.printDogSounds();

    }

    public static void task1(){
        Student a = new Student("Oleksandr","Helevan",11,88.3);
        Student b = new Student("Volodymyr","Dobryansky",11,90.5);
        Student c = new Student("Volodymyr","Zankovsky",11,56.0);

        StudMap map = new StudMap();
        map.addStudent(a);
        map.addStudent(b);
        map.addStudent(c);
        map.printMap();
        System.out.println();

        map.removeStudent("Zankovsky");
        map.printMap();
        System.out.println();
        map.searchStudent("Dobryansky");
        map.searchStudent("Ostrovsky");
        System.out.println();
    }

    public static  void task2(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        Set<Integer> set = new HashSet<>(list);
        System.out.println(set);

        Map<Integer,Integer> map = new HashMap<>();
        for (Integer i : list){
            Integer counter = 0;
            for(Integer j : list){
                if(Objects.equals(i, j)){
                    counter++;
                }
            }
            map.put(i,counter);
        }
        System.out.println(map);
    }

    public static <T extends Comparable<T>> void findMax(T[] array){
        T value = Arrays.stream(array).max(T::compareTo).get();
        System.out.println(value);
    }

    public static void task4(){

    Integer[] arrayInt = new Integer[]{1,2,3,4,5,6};
    Double[] arrayDouble = new Double[]{1.0,2.0,3.0,4.0,5.0};
    Character[] arrayChar = new Character[]{'A','B','C','D','E','F'};
    String[] arrayString = new String[]{"Alisa","Bob","John","Vova","Sanya","Fred"};

    findMax(arrayInt);
    findMax(arrayDouble);
    findMax(arrayChar);
    findMax(arrayString);

    }

    public static void task5(){
        Pair<Integer,String> a = new Pair<>(1,"Oleksandr");
        Pair<Integer,String> b = new Pair<>(2,"Volodymyr");
        Pair<Integer,String> c = new Pair<>(3,"Vlad");

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println("Compare: ");
        System.out.println(a.compare(b));
        System.out.println();

        Pair<String, List<Integer>> a1 = new Pair<>("O",Arrays.asList(1,2));
        Pair<String, List<Integer>> b1 = new Pair<>("A",Arrays.asList(3,4));
        Pair<String, List<Integer>> c1 = new Pair<>("V",Arrays.asList(5,6));

        System.out.println(a1);
        System.out.println(b1);
        System.out.println(c1);
        System.out.println("Compare: ");
        System.out.println(c1.compare(b1));
    }

    public static double calculateTotalArea(List<? extends Shape> shapes) {
        double area = 0;
        for (Shape shape : shapes) {
            area += shape.getArea();
        }
        return area;
    }

    public static void task6(){
    Circle a = new Circle(1);
    Rectangle b = new Rectangle(2,2);
    Circle c = new Circle(10);
    System.out.println(a.getArea() + " " + b.getArea() + " " + c.getArea());
    List<Shape>shapes = new ArrayList<>();

    shapes.add(a);
    shapes.add(b);
    shapes.add(c);

    System.out.println(calculateTotalArea(shapes));

    }

    public static void addToList(List<? super Integer> list){
        for(int i =1; i<11; i++){
            list.add(i);
        }
    }

    public static void task7(){
        List<Integer> list1 = new ArrayList<>();
        addToList(list1);
        for (Integer i : list1){
            System.out.print(i + " ");
        }
        System.out.println();

        

        List<Number> list2 = new ArrayList<>();
        addToList(list2);
        for (Number i : list2){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        task1();
        shtrix();

        task2();
        shtrix();

        task3();
        shtrix();

        task4();
        shtrix();

        task5();
        shtrix();

        task6();
        shtrix();

        task7();
        shtrix();

        task8();
        shtrix();
    }
}
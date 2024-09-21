import java.util.*;
import java.util.stream.*;


public class Main {

    public static void shtrix(){
        System.out.println("---------------------------------------");
    }

    public static void xFunction(String str){
        Optional<String> res = Optional.of(str)
                .filter(s -> s.toLowerCase().startsWith("x") && s.length() > 5)
                .or(() -> Optional.of("Default"));

        System.out.println(res.get());
    }

    public static void task1(){
        String a = "Lil peep";
        String b = "XXXtentasion";
        String c = "x";
        xFunction(a);
        xFunction(b);
        xFunction(c);
    }

    public static void task2(){
        List<Optional<Integer>> list = List.of(
                Optional.of(1),
                Optional.of(2),
                Optional.empty(),
                Optional.of(3),
                Optional.empty()
        );

        List<Integer> res = list.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        for(Integer i : res){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public static void longest(List<String> list){
        Optional<String> res = list.stream().max(Comparator.comparingInt(String::length));
        res.ifPresentOrElse(System.out::println,
                () -> System.out.println("none"));
    }

    static public void task3(){
        List<String> list = List.of("Oleksandr","Vova","Anton","Vlad");
        List<String> list2 = new ArrayList<>();
        longest(list);
        longest(list2);
    }

    public static Map<String, List<Employee>> group(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> {
                            if (employee.getSalary() < 3000) return "-3k";
                            else if (employee.getSalary() <= 5000) return "3k-5k";
                            else return "5k+";
                        }
                ));
    }

    public static void task4() {
        List<Employee> employees = List.of(
                new Employee("Vova", 5400),
                new Employee("Sanya", 5500),
                new Employee("Vlad", 2900),
                new Employee("Anton", 4900),
                new Employee("Vasya", 1000)
        );

        Map<String, List<Employee>> maps = group(employees);
        maps.forEach((range, list) -> {
            System.out.println(range + ":");
            list.forEach(System.out::println);
        });
    }

    public static void task5(){
        List<Integer> intList = List.of(1,2,3,4,5,6,7,8,9,10);
        for (Integer i: intList){
            System.out.print(i+" ");
        }
        List<Integer> buff = intList.stream().filter(e -> e % 2 == 1).toList();
        System.out.println();
        Optional<Integer> res = buff.stream().reduce((num1, num2)->num1*num2);
        System.out.print("Result: ");
        res.ifPresent(System.out::println);
    }

    public static void task6(){
        Person friend1 = new Person("Vova", List.of());
        Person friend2 = new Person("Anton", List.of());
        Person friend3 = new Person("Vlad", List.of());
        Person friend4 = new Person("Vasya", List.of());
        Person friend5 = new Person("Vova", List.of());
        Person friend6 = new Person("Vova", List.of());

        Person me = new Person("Oleksandr", List.of(
                friend1, friend2, friend3, friend4, friend5, friend6));

        me.getUniqueFriends().forEach(person -> System.out.println(person.getNameUp()));
    }

    public static void task7(){
    List<Transaction> transactions = List.of(
            new Transaction(50.99,"Cafe"),
            new Transaction(765.50, "Market"),
            new Transaction(240.50,"Cafe"),
            new Transaction(120.49, "Market")
    );

    Set<String> categorySet = new HashSet<>();
    transactions.forEach(transaction -> {
        categorySet.add(transaction.getCategory());
    });
    Map<String,Double> CategoryMap = new HashMap<>();

        categorySet.forEach(category -> {
            double total = transactions.stream()
                    .filter(transaction -> transaction.getCategory().equals(category))
                    .mapToDouble(Transaction::getAmount)
                    .sum();
            CategoryMap.put(category, total);
        });

        CategoryMap.forEach((c,t)-> System.out.println(c + " " + t));

    }

    public static void task8(){
        List<Product> productList = List.of(
                new Product("Sugar",60),
                new Product("Milk",45),
                new Product("Chocolate",40),
                new Product("Pineapple",120),
                new Product("Potato",20),
                new Product("Burger",90)
        );

        Optional<Product> second = productList.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .skip(1).findFirst();

        second.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("not enough products")
        );
    }

    public static void task9(){
        Map<Integer, Optional<String>> map = Map.of(
                1, Optional.of("Dog"),
                2, Optional.empty(),
                3, Optional.of("Cat"),
                4, Optional.of("Horse"),
                5, Optional.empty(),
                6, Optional.of("Snake")
        );

        List<String> list = map.values().stream()
                .filter(Optional::isPresent)
                .map(optional -> optional.get().toUpperCase())
                .toList();

        list.forEach(System.out::println);
    }

    public static void task10(){
        Map<String,List<Integer>> cityMap = Map.of(
                "Khust",List.of(37,35,31,33,35),
                "Chernivtsi",List.of(30,32,33,31,35),
                "Kyiv",List.of(29,25,27,33,33),
                "Kharkiv",List.of(27,29,31,33,30),
                "Odesa",List.of(34,36,37,36,37),
                "Lviv",List.of(35,34,32,33,34)
        );

        Map<String,Double> avCityMap = cityMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .mapToInt(Integer::intValue)
                                .average().orElse(0)
                ));

        System.out.println(avCityMap.entrySet().stream()
                .max(Map.Entry.comparingByValue()).get());
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

    task9();
    shtrix();

    task10();
    }
}
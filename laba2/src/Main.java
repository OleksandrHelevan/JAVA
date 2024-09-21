import package1.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void task1() {
        Example example = new Example();
        while(true) {
            System.out.println("Enter a number: ");
            String num = new Scanner(System.in).nextLine();
            try {
                int number = example.ReadNumber(num);
                System.out.println("Number: " + number + " is right");
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void task2() {
        List <Integer> numbers = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            numbers.add(i+10);
//        }
        try {
            if (numbers.isEmpty())
                throw new ArithmeticException("List is empty!!!");
            double sum = 0;
            for (Integer number : numbers) {
                System.out.print(number + " ");
                sum+=number;
            }
            System.out.println();
            System.out.println(sum/numbers.size());
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

    task1();

    task2();

    }
}
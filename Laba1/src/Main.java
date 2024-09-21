import java.util.Scanner;

public class Main {

    public static double fromCtoF(double c){
        return c*9/5+32;
    }

    public static int maxEl(int a, int b){
        if(a>b)
            return a;
        else return b;
    }

    public static void task1(){
        double cel = 22;
        System.out.print("Цельсію = ");
        System.out.println(cel);
        System.out.print("Фаренгейту = ");
        System.out.println(fromCtoF(cel));
        System.out.println("-----------------------------");
    }

    public static void task2(){
        int a = 10;
        int b = 20;
        System.out.println(maxEl(a,b));
        System.out.println("-----------------------------");
    }

    public static void task3(){
        double num1 = new Scanner(System.in).nextDouble();
        String znak = new Scanner(System.in).nextLine();
        double num2 = new Scanner(System.in).nextDouble();
        switch (znak){
            case "+":{
                System.out.println(num1+num2);
                break;
            }
            case "-":{
                System.out.println(num1-num2);
                break;
            }
            case "*":{
                System.out.println(num1*num2);
                break;
            }
            case "/":{
                System.out.println(num1/num2);
                break;
            }

            default:{
                System.out.println("ERROR!");
            }
        }
    }

    public static void task4(){

        System.out.println("-----------------------------");
        int num_for_fac = new Scanner(System.in).nextInt();
        int i = 1;
        int res = 1;
        if(num_for_fac==0){
            System.out.println(1);
        }
        else {
            while (i <= num_for_fac) {
                res *= i;
                i++;
            }
            System.out.println(res);
        }
        System.out.println("-----------------------------");

    }

    public static void task5(){
        int[] array = new int[]{1,2,3,4,5,6,7,8,9,10};
        int maxel = array[1];
        for( int j =0; j<10; j++) {
            if(maxel<array[j])
                maxel = array[j];
        }
        System.out.println(maxel);
    }

    public static void main(String[] args) {

        task1();

        task2();

        task3();

        task4();

        task5();

    }

}
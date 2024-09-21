package task1;

public class Student{
    private final String name;
    private final String surname;
    private final int age;
    private final double averagescore;

    public Student(String name, String surname, int age, double averagescore) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.averagescore = averagescore;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", surname=" + surname + ", age=" + age + "]";
    }


    public String getSurname() {
        return surname;
    }

    public double getAveragescore() {
        return averagescore;
    }
}

public class Employee implements Comparable<Employee>{
    private final String name;
    private final int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return name + "\t" + salary;
    }


    @Override
    public int compareTo(Employee o) {
        return salary - o.salary;
    }
}

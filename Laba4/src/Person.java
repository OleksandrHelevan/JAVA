import java.util.*;


public class Person implements Comparable<Person> {
    private final String name;
    private final List<Person> friends;

    public Person(String name, List<Person> friends) {
        this.name = name;
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public String getNameUp() {
        return name.toUpperCase();
    }


    public List<Person> getUniqueFriends() {
    return friends.stream().distinct().toList();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Person o) {
        if(this.name.compareTo(o.getName())==0)
            return 0;
        if (this.name.compareTo(o.getName())>0)
            return 1;
        else return -1;
    }
}


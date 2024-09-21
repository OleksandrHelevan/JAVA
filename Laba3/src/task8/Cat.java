package task8;

public class Cat extends Animal {

    @Override
    public void makeSound() {
        System.out.print("Meow" + " ");
    }

    @Override
    public boolean isDog() {
        return false;
    }

}

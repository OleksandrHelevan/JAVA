package task8;

public class Dog extends Animal {


    @Override
    public void makeSound() {
        System.out.print("Woof"+" ");
    }
    @Override
    public boolean isDog(){
        return true;
    }

}


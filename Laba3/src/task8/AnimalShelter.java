package task8;

import java.util.ArrayList;
import java.util.List;

public class AnimalShelter {
    private final List<Animal> animals = new ArrayList<>();
    private final List <Animal> dogs = new ArrayList<>();

    public AnimalShelter() {
    }

    public <T extends Animal>void addAnimal(T a){
        animals.add(a);
        if(a.isDog()) {
            dogs.add(a);
        }
    }
    public void printAnimalSounds(){
        System.out.println("Animals: ");
        for (Animal a : animals) {
            a.makeSound();
        }
        System.out.println();
    }

    public void printDogSounds(){
        System.out.println("Dogs: ");
        for (Animal d: dogs) {
            d.makeSound();
        }
        System.out.println();
    }

}

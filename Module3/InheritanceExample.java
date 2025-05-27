class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound"); // cite: 47
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark"); // cite: 47
    }
}

public class InheritanceExample {
    public static void main(String[] args) {
        Animal animal = new Animal(); // cite: 48
        Dog dog = new Dog(); // cite: 48

        animal.makeSound(); // cite: 48
        dog.makeSound(); // cite: 48
    }
}
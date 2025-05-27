public class Car {
    String make; // cite: 44
    String model; // cite: 44
    int year; // cite: 44

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public void displayDetails() {
        System.out.println("Make: " + make + ", Model: " + model + ", Year: " + year); // cite: 45
    }

    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Camry", 2020); // cite: 45
        Car car2 = new Car("Honda", "Civic", 2018); // cite: 45

        car1.displayDetails(); // cite: 45
        car2.displayDetails(); // cite: 45
    }
}
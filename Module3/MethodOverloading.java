public class MethodOverloading {
    

    // Method to add two integers
    public int add(int a, int b) { // cite: 30
        return a + b; // cite: 31
    }

    // Method to add two doubles
    public double add(double a, double b) { // cite: 30
        return a + b; // cite: 31
    }

    // Method to add three integers
    public int add(int a, int b, int c) { // cite: 30
        return a + b + c; // cite: 31
    }

    public static void main(String[] args) {
        MethodOverloading calculator = new MethodOverloading();

        // Call each method and display the results
        System.out.println("Sum of two integers (5, 10): " + calculator.add(5, 10)); // cite: 32
        System.out.println("Sum of two doubles (5.5, 10.5): " + calculator.add(5.5, 10.5)); // cite: 32
        System.out.println("Sum of three integers (1, 2, 3): " + calculator.add(1, 2, 3)); // cite: 32
    }
}
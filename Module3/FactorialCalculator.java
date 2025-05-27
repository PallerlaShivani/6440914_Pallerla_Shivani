// FactorialCalculator.java
import java.util.Scanner;

public class FactorialCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for a non-negative integer. [cite: 28, 132]
        System.out.print("Enter a non-negative integer to calculate its factorial: ");
        int number = scanner.nextInt();

        if (number < 0) {
            System.out.println("Factorial is not defined for negative numbers.");
        } else {
            long factorial = 1; // Use long to handle larger factorial values

            // Use a for loop to calculate the factorial. [cite: 28, 132]
            for (int i = 1; i <= number; i++) {
                factorial *= i; // factorial = factorial * i;
            }

            System.out.println("The factorial of " + number + " is: " + factorial); // Display the result. [cite: 28, 132]
        }

        scanner.close();
    }
}
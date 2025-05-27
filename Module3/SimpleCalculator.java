// SimpleCalculator.java
import java.util.Scanner; // Import the Scanner class for user input

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter two numbers. [cite: 109]
        System.out.print("Enter the first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter the second number: ");
        double num2 = scanner.nextDouble();

        // Ask the user to choose an operation. [cite: 109]
        System.out.println("Choose an operation (+, -, *, /):");
        char operation = scanner.next().charAt(0);

        double result;

        switch (operation) {
            case '+':
                result = num1 + num2;
                System.out.println("Result: " + result); // Display the result of the operation. [cite: 109, 110]
                break;
            case '-':
                result = num1 - num2;
                System.out.println("Result: " + result); // Display the result of the operation. [cite: 109, 110]
                break;
            case '*':
                result = num1 * num2;
                System.out.println("Result: " + result); // Display the result of the operation. [cite: 109, 110]
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                    System.out.println("Result: " + result); // Display the result of the operation. [cite: 109, 110]
                } else {
                    System.out.println("Error: Division by zero is not allowed.");
                }
                break;
            default:
                System.out.println("Invalid operation. Please choose +, -, *, or /.");
        }

        scanner.close();
    }
}
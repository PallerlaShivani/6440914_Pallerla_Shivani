// EvenOddChecker.java
import java.util.Scanner;

public class EvenOddChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for an integer. [cite: 111]
        System.out.print("Enter an integer: ");
        int number = scanner.nextInt();

        // Use the modulus operator % to check divisibility by 2. [cite: 111]
        if (number % 2 == 0) {
            System.out.println(number + " is an even number."); // Display whether the number is even or odd. [cite: 111]
        } else {
            System.out.println(number + " is an odd number."); // Display whether the number is even or odd. [cite: 111]
        }

        scanner.close();
    }
}
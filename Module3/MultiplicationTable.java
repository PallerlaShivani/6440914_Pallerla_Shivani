// MultiplicationTable.java
import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for a number. [cite: 116]
        System.out.print("Enter a number to display its multiplication table: ");
        int number = scanner.nextInt();

        System.out.println("Multiplication table for " + number + ":");

        // Use a for loop to iterate from 1 to 10. [cite: 116]
        for (int i = 1; i <= 10; i++) {
            // Multiply the input number by the loop counter and display the result. [cite: 116]
            System.out.println(number + " x " + i + " = " + (number * i));
        }

        scanner.close();
    }
}
import java.util.Scanner;

public class TryCatchExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first integer: "); // cite: 52
        int num1 = scanner.nextInt(); // cite: 52
        System.out.print("Enter second integer: "); // cite: 52
        int num2 = scanner.nextInt(); // cite: 52

        try {
            int result = num1 / num2; // cite: 52
            System.out.println("Result of division: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero."); // cite: 52
        }
        scanner.close();
    }
}
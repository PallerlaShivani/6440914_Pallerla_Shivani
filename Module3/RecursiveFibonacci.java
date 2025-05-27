import java.util.Scanner;

public class RecursiveFibonacci {

    // Define a recursive method fibonacci(int n)
    public static int fibonacci(int n) { // cite: 34
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2); // cite: 34
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a positive integer n to find the nth Fibonacci number: "); // cite: 33
        int n = scanner.nextInt(); // cite: 33

        if (n < 0) {
            System.out.println("Please enter a positive integer.");
        } else {
            System.out.println("The " + n + "th Fibonacci number is: " + fibonacci(n)); // cite: 34
        }
        scanner.close();
    }
}
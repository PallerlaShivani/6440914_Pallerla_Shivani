import java.util.Scanner;

class InvalidAgeException extends Exception { // cite: 53
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class CustomException {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        try {
            if (age < 18) {
                throw new InvalidAgeException("Age cannot be less than 18."); // cite: 54
            }
            System.out.println("Age is valid: " + age);
        } catch (InvalidAgeException e) {
            System.out.println("Caught an exception: " + e.getMessage()); // cite: 54
        }
        scanner.close();
    }
}
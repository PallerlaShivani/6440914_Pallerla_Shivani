import java.util.Scanner;

public class StringReversal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to reverse: "); // cite: 39
        String inputString = scanner.nextLine(); // cite: 39

        // Using StringBuilder to reverse the string
        StringBuilder reversedStringBuilder = new StringBuilder(inputString); // cite: 39
        reversedStringBuilder.reverse(); // cite: 39
        String reversedString = reversedStringBuilder.toString();

        System.out.println("Original string: " + inputString);
        System.out.println("Reversed string: " + reversedString); // cite: 40

        // Alternative: Using a loop
        String reversedByLoop = "";
        for (int i = inputString.length() - 1; i >= 0; i--) { // cite: 39
            reversedByLoop += inputString.charAt(i);
        }
        // System.out.println("Reversed by loop: " + reversedByLoop);

        scanner.close();
    }
}
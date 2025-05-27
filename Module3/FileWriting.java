import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to write to file: "); // cite: 55
        String input = scanner.nextLine(); // cite: 55

        try (FileWriter writer = new FileWriter("output.txt")) { // cite: 56
            writer.write(input); // cite: 56
            System.out.println("Data successfully written to output.txt"); // cite: 56
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
        scanner.close();
    }
}
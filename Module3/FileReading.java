import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReading {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) { // cite: 58
            String line;
            System.out.println("Contents of output.txt:");
            while ((line = reader.readLine()) != null) { // cite: 58
                System.out.println(line); // cite: 58
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
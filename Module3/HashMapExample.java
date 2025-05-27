import java.util.HashMap;
import java.util.Scanner;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> studentMap = new HashMap<>(); // cite: 62
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student IDs and names (type ID '0' to finish adding):");
        while (true) {
            System.out.print("Enter student ID (0 to stop): ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (id == 0) {
                break;
            }
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            studentMap.put(id, name); // cite: 62
        }

        System.out.print("\nEnter an ID to retrieve a student's name: ");
        int searchId = scanner.nextInt(); // cite: 63
        if (studentMap.containsKey(searchId)) {
            System.out.println("Student ID: " + searchId + ", Name: " + studentMap.get(searchId)); // cite: 63
        } else {
            System.out.println("Student with ID " + searchId + " not found.");
        }
        scanner.close();
    }
}
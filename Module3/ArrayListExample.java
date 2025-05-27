import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> studentNames = new ArrayList<>(); // cite: 60
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student names (type 'done' to finish):");
        while (true) {
            System.out.print("Name: ");
            String name = scanner.nextLine(); // cite: 60
            if (name.equalsIgnoreCase("done")) {
                break;
            }
            studentNames.add(name); // cite: 60
        }

        System.out.println("\nList of student names:");
        for (String name : studentNames) { // cite: 60
            System.out.println(name);
        }
        scanner.close();
    }
}
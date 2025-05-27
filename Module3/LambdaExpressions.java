import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaExpressions {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(); // cite: 66
        names.add("Alice");
        names.add("Charlie");
        names.add("Bob");
        names.add("David");

        System.out.println("Original list: " + names);

        // Sort the list using a lambda expression [cite: 67]
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2)); // cite: 67

        System.out.println("Sorted list: " + names); // cite: 67
    }
}
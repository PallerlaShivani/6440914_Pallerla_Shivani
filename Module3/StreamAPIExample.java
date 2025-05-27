import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPIExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10); // cite: 69

        System.out.println("Original list: " + numbers);

        // Filter even numbers using Stream API [cite: 69]
        List<Integer> evenNumbers = numbers.stream()
                                            .filter(n -> n % 2 == 0) // cite: 69
                                            .collect(Collectors.toList()); // cite: 70

        System.out.println("Even numbers: " + evenNumbers); // cite: 70
    }
}
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

// Define a record named Person [cite: 72]
record Person(String name, int age) { } // cite: 72

public class RecordsExample {
    public static void main(String[] args) {
        // Create instances and print them [cite: 72]
        Person person1 = new Person("Alice", 30); // cite: 72
        Person person2 = new Person("Bob", 25); // cite: 72
        Person person3 = new Person("Charlie", 35); // cite: 72
        Person person4 = new Person("David", 22); // cite: 72

        System.out.println("Person 1: " + person1);
        System.out.println("Person 2: " + person2);

        // Use records in a List and filter based on age using Streams [cite: 73]
        List<Person> people = new ArrayList<>();
        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);

        System.out.println("\nAll people: " + people);

        List<Person> adults = people.stream()
                                    .filter(p -> p.age() >= 25) // cite: 73
                                    .collect(Collectors.toList()); // cite: 73

        System.out.println("People aged 25 or older: " + adults); // cite: 73
    }
}
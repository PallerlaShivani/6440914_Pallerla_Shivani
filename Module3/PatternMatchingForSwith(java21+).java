public class PatternMatchingSwitch {

    // Create a method that accepts Object as input [cite: 75]
    public static void processObject(Object obj) {
        // Use a switch expression to check the object's type [cite: 75]
        String message = switch (obj) {
            case Integer i -> "It's an Integer with value: " + i; // cite: 75
            case String s -> "It's a String with length: " + s.length(); // cite: 75
            case Double d -> "It's a Double with value: " + d; // cite: 75
            case null -> "It's a null object.";
            default -> "It's an unknown type.";
        };
        System.out.println(message); // cite: 76
    }

    public static void main(String[] args) {
        processObject(10);
        processObject("Hello Java!");
        processObject(3.14);
        processObject(true);
        processObject(null);
    }
}
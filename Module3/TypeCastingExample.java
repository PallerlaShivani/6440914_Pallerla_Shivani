// TypeCastingExample.java
public class TypeCastingExample {
    public static void main(String[] args) {
        // Declare a double variable with a decimal value. [cite: 122]
        double myDouble = 10.75;
        System.out.println("Original double value: " + myDouble);

        // Cast it to an int and display the result. [cite: 122]
        int intFromDouble = (int) myDouble;
        System.out.println("Double cast to int: " + intFromDouble); // Note: Decimal part is truncated

        // Declare an int variable and cast it to a double, then display. [cite: 122]
        int myInt = 25;
        System.out.println("Original int value: " + myInt);

        double doubleFromInt = (double) myInt;
        System.out.println("Int cast to double: " + doubleFromInt); // Note: .0 is added
    }
}
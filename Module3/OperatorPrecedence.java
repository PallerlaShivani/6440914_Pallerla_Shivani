// OperatorPrecedence.java
public class OperatorPrecedence {
    public static void main(String[] args) {
        // Write expressions combining multiple operators, e.g., int result = 10 + 5 * 2; [cite: 124]

        // Example 1: Multiplication before addition
        int result1 = 10 + 5 * 2; // Equivalent to 10 + (5 * 2) = 10 + 10 = 20
        System.out.println("Result of 10 + 5 * 2: " + result1); // Display the result and explain the order of operations. [cite: 125]
        System.out.println("Explanation: Multiplication (*) has higher precedence than addition (+).");

        System.out.println("\n---");

        // Example 2: Parentheses override precedence
        int result2 = (10 + 5) * 2; // Equivalent to (15) * 2 = 30
        System.out.println("Result of (10 + 5) * 2: " + result2); // Display the result and explain the order of operations. [cite: 125]
        System.out.println("Explanation: Parentheses change the order of evaluation, so addition is done first.");

        System.out.println("\n---");

        // Example 3: Mixed operators
        int result3 = 20 - 4 / 2 + 3; // Equivalent to 20 - (4 / 2) + 3 = 20 - 2 + 3 = 18 + 3 = 21
        System.out.println("Result of 20 - 4 / 2 + 3: " + result3); // Display the result and explain the order of operations. [cite: 125]
        System.out.println("Explanation: Division (/) has higher precedence than subtraction (-) and addition (+). Operations of same precedence are evaluated from left to right.");
    }
}
import java.util.Scanner;

public class ArraySumAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements for the array: "); // cite: 36
        int numElements = scanner.nextInt(); // cite: 36

        int[] numbers = new int[numElements]; // cite: 37
        long sum = 0;

        System.out.println("Enter " + numElements + " integers:");
        for (int i = 0; i < numElements; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt(); // cite: 37
            sum += numbers[i]; // cite: 37
        }

        double average = (double) sum / numElements; // cite: 37

        System.out.println("Sum of elements: " + sum); // cite: 37
        System.out.println("Average of elements: " + average); // cite: 37

        scanner.close();
    }
}
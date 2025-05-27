// LeapYearChecker.java
import java.util.Scanner;

public class LeapYearChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a year. [cite: 113]
        System.out.print("Enter a year: ");
        int year = scanner.nextInt();

        boolean isLeapYear;

        // A year is a leap year if it's divisible by 4 but not by 100, unless it's also divisible by 400. [cite: 114]
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            isLeapYear = true;
        } else {
            isLeapYear = false;
        }

        if (isLeapYear) {
            System.out.println(year + " is a leap year."); // Display the result accordingly. [cite: 114]
        } else {
            System.out.println(year + " is not a leap year."); // Display the result accordingly. [cite: 114]
        }

        scanner.close();
    }
}
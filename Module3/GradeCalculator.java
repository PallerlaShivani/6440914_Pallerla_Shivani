import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Grade Calculator ---");

        // Get the number of subjects
        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();

        if (numberOfSubjects <= 0) {
            System.out.println("Number of subjects must be greater than 0.");
            scanner.close();
            return;
        }

        int totalMarks = 0;
        int maxMarksPerSubject = 100; // Assuming each subject is out of 100

        // Get marks for each subject
        for (int i = 1; i <= numberOfSubjects; i++) {
            System.out.print("Enter marks obtained in Subject " + i + " (out of " + maxMarksPerSubject + "): ");
            int marks = scanner.nextInt();

            // Validate marks
            if (marks < 0 || marks > maxMarksPerSubject) {
                System.out.println("Invalid marks! Marks should be between 0 and " + maxMarksPerSubject + ".");
                // Decrement i to re-enter marks for the current subject
                i--;
                continue;
            }
            totalMarks += marks;
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / (numberOfSubjects * maxMarksPerSubject) * 100;

        // Assign grade based on average percentage
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else if (averagePercentage >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        // Display results
        System.out.println("\n--- Results ---");
        System.out.println("Total Marks: " + totalMarks + " / " + (numberOfSubjects * maxMarksPerSubject));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
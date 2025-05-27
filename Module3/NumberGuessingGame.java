// NumberGuessingGame.java
import java.util.Random; // For generating random numbers
import java.util.Scanner; // For user input

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Generate a random number between 1 and 100. [cite: 25, 129]
        int randomNumber = random.nextInt(100) + 1; // Generates a number from 1 to 100
        int guess;
        int numberOfGuesses = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have generated a number between 1 and 100. Try to guess it!");

        while (!hasGuessedCorrectly) {
            // Prompt the user to guess the number. [cite: 26, 130]
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            numberOfGuesses++;

            if (guess < 1 || guess > 100) {
                System.out.println("Your guess is out of range. Please guess a number between 1 and 100.");
            } else if (guess < randomNumber) {
                System.out.println("Too low! Try again."); // Provide feedback if the guess is too high or too low. [cite: 26, 130]
            } else if (guess > randomNumber) {
                System.out.println("Too high! Try again."); // Provide feedback if the guess is too high or too low. [cite: 26, 130]
            } else {
                hasGuessedCorrectly = true;
                System.out.println("Congratulations! You've guessed the number " + randomNumber + " in " + numberOfGuesses + " guesses.");
            }
            // Continue until the user guesses correctly. [cite: 27, 131]
        }

        scanner.close();
    }
}
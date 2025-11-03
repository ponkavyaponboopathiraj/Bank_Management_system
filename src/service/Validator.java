package src.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import src.exception.MaxAttemptsExceededException;



public class Validator {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static final int MAX_ATTEMPTS = 3;

    public static int getValidatedChoice(int min, int max) {
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your choice (" + min + " - " + max + "): ");
            try {
                String input = READER.readLine().trim();
                int choice = Integer.parseInt(input);

                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Invalid input! Please enter a number between " + min + " and " + max + ".\n");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Invalid input! Only numbers are allowed.\n");
            }

            attempts++;
        }

        throw new MaxAttemptsExceededException("Too many invalid attempts!!!. Exiting...");
    }
}
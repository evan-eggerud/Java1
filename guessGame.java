//Name: Evan Eggerud
//Assignment: Guess Game Assignment 4
//Class: CS 141
//Source: Deitel Deitel

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.security.SecureRandom;
public class guessGame{
    public static List<Integer> gameCounts = new ArrayList<>(); //Initialize array for game counts
    public static void main(String[] args) {
        //assign variables
        SecureRandom randomNumbers = new SecureRandom();
        Scanner input = new Scanner(System.in);
        boolean playAgain = true;
        double totalCount = 0;
        double gameTracker = 0; 
        // while loop runs only if playAgain is true, stops program when answered "n"
        while (playAgain) {
            int randomInt = randomNumbers.nextInt(10) + 1; //create random number
            int guess;
            int count = 0;
            instructions();
            //do while loop takes input from guess, runs the guessing method and adds count for game counts.
            do { 
                guess = input.nextInt();                
                count++; //count is declared as 0 in the while loop so it resets upon playing a new game
                totalCount++; //total count declared outside of it to create total count
                boolean isCorrect = guessMethod(guess, randomInt, count);

                if (isCorrect){
                    gameCounts.add(count); //add game count to array
                    gameTracker++;
                    break;
                }
            } while (true);
            
            System.out.println("Would you like to play again (y or n)?");
            String restart = input.next();
            playAgain = restart.equalsIgnoreCase("y");
        }
        //results print:
        results(totalCount, gameTracker, findLowestGameCount());
    }
    //This method gives you instructions for how to play the game at the start
    public static void instructions(){
        System.out.println("We're going to play a guessing game.");
        System.out.println("I'm going to pick a random number between 1 and 100.");
        System.out.println("I will tell you if the number is higher or lower.\nPlease guess the number: ");
    }
    //guess method determines whether guess is correct, lower or higher and outputs respectively until correct guess is made/
    public static boolean guessMethod(int guess, int randomInt, int count) {
        boolean result = false;
        if (guess > randomInt) {
            System.out.println("The number is lower, guess again:");
        } else if (guess < randomInt) {
            System.out.println("The number is higher, guess again:");
        }
        if (guess == randomInt) {
            System.out.println("Congrats, you got the correct answer in " + count + " guesses!");
            result = true;
        }
        return result; // Return true if the guess is correct
    }
    //find lowest game count method determines the lowest count guess among the gameCounts array. 
    public static int findLowestGameCount() {
        if (gameCounts.isEmpty()) {
            return 0; // No games played, return 0
        }
        int lowestGameCount = gameCounts.get(0);
        for (int i = 0; i < gameCounts.size(); i++) {
            if (gameCounts.get(i) < lowestGameCount) {
                lowestGameCount = gameCounts.get(i);
            }
        }
        return lowestGameCount;
    }
    //this method prints out the results once the game has ended and gives the total guess count, the amount of times played, average guesses per round and best round
    public static void results(double totalCount, double gameTracker, int findLowestGameCount){
        System.out.println("Total guesses: " + totalCount);
        System.out.println("You played " + gameTracker + " times");
        double average = totalCount / gameTracker;
        System.out.printf("Average guesses per game: %.2f", average);
        System.out.println("\nYour best game was: " + findLowestGameCount() + " guesses");
    }
}
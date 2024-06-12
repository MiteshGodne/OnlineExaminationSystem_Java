package JavaProgramming;

import java.util.Scanner;

public class NumberGuessingGame {

  public static int randomNumber() {
    int random = (int) Math.ceil((Math.random() * 100));
    return random;
  }

  public static void main(String[] args) {

    System.out.println("Hello !! Welcome to the Number Guessing Game :) ");
    System.out.println(
        "Rule - There will be 2 rounds and you will be given 7 attempts to guess a number for each round, Guess the number in least attempts to get the highest score !! ");

    try (Scanner obj = new Scanner(System.in)) {
      int replay = 1;

      System.out.print("Enter Y/y start Or N/n to exit the game : ");
      String canStart = obj.nextLine();

      while (replay > 0) {
        replay--;

        if (canStart.equals("Y") || canStart.equals("y")) {
          int guess, random, round = 1, totalScore = 0;

          while (round <= 2) {
            int attempts = 7;
            System.out.printf("\nRound - %d   Attempt - %d \n", round, attempts);
            System.out.print("Guess an integer number between 1 to 100 : ");
            random = randomNumber();

            for (int i = 0; i < attempts;) {
              guess = obj.nextInt();

              if (guess == random) {
                System.out.printf("\nYay!! You guessed the number accurately in %d attempts !!\n", attempts);
                totalScore += attempts * 10;
                break;
              } else if (guess > random) {
                attempts--;
                if (attempts == 0) {
                  System.out.println("Oops !! You lose... The number was " + random);
                  break;
                }
                System.out.printf("Too high, [%d attempts left], Guess again with lower number : ", attempts);
              } else {
                attempts--;
                if (attempts == 0) {
                  System.out.println("Oops !! You lose... The number was " + random);
                  break;
                }
                System.out.printf("Too low, [%d attempts left],  Guess again with higher number : ", attempts);
              }

            }
            System.out.printf("Your score after round %d is %d \n", round, totalScore);
            round++;

            if (round > 2) {
              System.out.print("\nEnter Y/y to replay the game and N/n to exit the game : ");
              if (obj.nextLine().equals("Y") || obj.nextLine().equals("y")) {
                replay++;
              } else {
                replay--;
              }
            }
          }

        } else if (canStart.equals("N") || canStart.equals("n")) {
          System.out.println("You could have tried it for once !! ");
        } else {
          System.out.println("You pressed the wrong key !!");
        }
      }
    }
    System.out.println("Game exited !!");
  }
}

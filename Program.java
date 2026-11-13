import java.util.Scanner;

public class Program {
    public static void main(String[] args){

    // Write a program that:
    // Saves a secret number. 
    // Asks the user for a number guess.
    // Prints a boolean depending on whether the guess matches the secret.

        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Guess the Number: ");
            int userInput = sc.nextInt();

            if(userInput == 67){
                System.out.println("You guessed the number.");
            }else {
                System.out.println("Guess again.");
            }

            
            System.out.println(".()");
            String userInt = sc.nextLine();
            if(userInt.equalsIgnoreCase("END")){
                System.out.println(".()");
                break;
            }

            sc.next();
        }
    }
}

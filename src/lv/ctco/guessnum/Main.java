package lv.ctco.guessnum;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random(); //Ctrl+Q = quick help

    public static void main(String[] args) { //psvm - public static void main(String[] args) { }
        int myNum = rand.nextInt(100) + 1; //Ctrl+P - info about parameters
        System.out.println("What's the number?");
        for (int i = 0; i < 10; i++) {
            System.out.println("Guess Nr. " + (i + 1)); //sout - System.out.print
            int userNum;
            try {
                userNum = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You are cheater");
                return;
            }
            if (myNum == userNum) {
                System.out.println("Congratulations!");
                return;
            } else if (myNum > userNum) {
                System.out.println("It is larger...");
            } else {
                System.out.println("It is smaller...");
            }
        }
        System.out.println("You Lost! Correct Answer: " + myNum);
    }
}

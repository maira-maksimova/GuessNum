package lv.ctco.guessnum;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random(); //Ctrl+Q = quick help
    static List<GameResult> results = new ArrayList<>();

    public static void main(String[] args) { //psvm - public static void main(String[] args) { }

        do {
            System.out.println("What's your name?");
            String name = scanner.next();
            System.out.println("Hello, " + name + "!");
            int myNum = generateNumber();
            System.out.println("What's the number?");
            boolean isLooser = true;
            long start = System.currentTimeMillis();

            for (int i = 1; i <= 10; i++) {
                System.out.println("Guess Nr. " + (i) + ":"); //sout - System.out.print
                int userNum = readUserNum();
                if (myNum == userNum) {
                    System.out.println("Congratulations!");

                    GameResult r = new GameResult();
                    r.name = name;
                    r.triesCount = i;
                    r.duration = System.currentTimeMillis() - start;
                    results.add(r);

                    isLooser = false;
                    break;
                } else if (myNum > userNum) {
                    System.out.println("It is larger...");
                } else {
                    System.out.println("It is smaller...");
                }
            }

            if (isLooser) {
                System.out.println("You Lost!");
            }
            System.out.println("Do you want to play one more game? Type 'y' if you want to continue");
        }
        while ("y".equals(scanner.next())); //TODO while correct
        System.out.println("Statistics: ");

        for (GameResult r: results) {
            r.print();
        }
    }

    private static int generateNumber() {
        int myNum = rand.nextInt(100) + 1; //Ctrl+P - info about parameters
        System.out.println("Spoiler: " + myNum);
        return myNum;
    }

    private static int readUserNum() {
        while (true) { //Ctrl + Y =delete row
            try {
                int userNum = scanner.nextInt();
                if (userNum < 1 || userNum > 100) {
                    System.out.println("Number not in range 1 - 100. Try again");
                    continue;
                }
                return userNum; //Ctrl +Shift + Arrow = move row up or down
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("You are cheater");
            }
        }
    }

}

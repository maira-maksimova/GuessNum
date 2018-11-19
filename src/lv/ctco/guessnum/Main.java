package lv.ctco.guessnum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static final File RESULTS_FILE = new File("results.txt");
    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random(); //Ctrl+Q = quick help
    static List<GameResult> results = new ArrayList<>();

    public static void main(String[] args) { //psvm - public static void main(String[] args) { }
        loadResults();
        do {
            String name = greet();

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

        }
        while (wantToContinue()); //TODO while correct
        saveResults();
        printResults();
    }

    private static void printResults() {
        System.out.println("Statistics: ");

        results.stream()
                .sorted(Comparator.<GameResult>comparingInt(r -> r.triesCount)
                        .<GameResult>thenComparingLong(r -> r.duration))
                .limit(3)
                .forEach(r -> System.out.printf("Player %s has done %d tries and it took %.2f sec\n",
                        r.name,
                        r.triesCount,
                        r.duration / 1000.0));
    }

    private static String greet() {
        System.out.println("What's your name?");
        String name = scanner.next();
        System.out.println("Hello, " + name + "!");

        return name;
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

    private static boolean wantToContinue() {
        System.out.println("Do you want to play one more game? (y/n)");
        while (true) {
            String input = scanner.next();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("Enter 'y' to continue or 'n' to finish");
            }
        }
    }

    private static void saveResults() {
        //Shift + F6 rename variable //Ctrl + Alt+ C  - extract constant

        try (PrintWriter fileOut = new PrintWriter(RESULTS_FILE)) {
            int skipCount = results.size() - 5;

            results.stream()
                    .skip(skipCount)
                    .forEach(r -> fileOut.printf("%s %d %d\n", r.name, r.triesCount, r.duration));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void loadResults() {
        try (Scanner in = new Scanner(RESULTS_FILE)) {
            while (in.hasNext()) {
                GameResult gr = new GameResult();
                gr.name = in.next();
                gr.triesCount = in.nextInt();
                gr.duration = in.nextLong();
                results.add(gr);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

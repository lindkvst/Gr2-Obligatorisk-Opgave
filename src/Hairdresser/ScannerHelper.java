package Hairdresser;

import java.util.Scanner;

public class ScannerHelper {

    private Scanner sc;

    //Constructor
    public ScannerHelper () {
        this.sc = new Scanner(System.in);
    }
    /*
      public int selectUser() {
        boolean numCorrect = false;
        int selectInt = 0;
        int selectIntMax = 2;

        while (!numCorrect) {
            System.out.print("Please create user.\n1: Free User\n2: Premium.\n Input your choice: ");

            if (sc.hasNextInt()) {
                selectInt = sc.nextInt();
                sc.nextLine();

                if (selectInt < 0) {
                    System.out.println("You have entered a negative number. Try again");
                } else if (selectInt == 0) {
                    System.out.println("You cannot enter 0. Try again");
                } else if (selectInt > selectIntMax) {
                    System.out.println("You cannot enter numbers larger than " + selectIntMax + ". Try again");
                } else {
                    if (selectInt == 1) {
                        System.out.println("You are a free user.");
                    } else {
                        System.out.println("you are a premium user.");
                    }
                    numCorrect = true;
                }
            } else {
                System.out.println("That's not a number. Try again");
                sc.nextLine(); // rydder forkert input
            }
        }
        return selectInt;
    }
     */

/*
    public int selectOption() {
        boolean numCorrect = false;
        int selectInt = 0;
        int selectIntMax = 6;

        System.out.println("\nChoose option:");
        System.out.println("1: Add song");
        System.out.println("2: Remove song");
        System.out.println("3: Show all songs");
        System.out.println("4: Search for song");
        System.out.println("5: Edit song title");
        System.out.println("6: Finish");
        System.out.print("Choose option (1-6): ");

        while (!numCorrect) {

            if (sc.hasNextInt()) {
                selectInt = sc.nextInt();
                sc.nextLine();

                if (selectInt < 0) {
                    System.out.println("You have entered a negative number. Try again");
                } else if (selectInt == 0) {
                    System.out.println("You cannot enter 0. Try again");
                } else if (selectInt > selectIntMax) {
                    System.out.println("You cannot enter numbers larger than " + selectIntMax + ". Try again");
                } else {
                    numCorrect = true;
                }
            } else {
                System.out.println("That's not a number. Try again");
                sc.nextLine(); // rydder forkert input
            }
        }
        return selectInt;
    }
*/
    public int askNumber(int intMax) {
        boolean numCorrect = false;
        int selectInt = 0;
        int selectIntMax = intMax;


        while (!numCorrect) {

            if (sc.hasNextInt()) {
                selectInt = sc.nextInt();
                sc.nextLine();

                if (selectInt < 0) {
                    System.out.println("You have entered a negative number. Try again");
                } else if (selectInt == 0) {
                    System.out.println("You cannot enter 0. Try again");
                } else if (selectInt > selectIntMax) {
                    System.out.println("You cannot enter numbers larger than " + selectIntMax + ". Try again");
                } else {
                    numCorrect = true;
                }
            } else {
                System.out.println("That's not a number. Try again");
                sc.nextLine(); // rydder forkert input
            }
        }
        return selectInt;
    }


    public String askQuestion(String question) {
        System.out.print(question + ": ");
        String answer = sc.nextLine();
        if (answer.isEmpty()) {
            System.out.println("Ups - den fik jeg ikke. Prøv igen.");
            System.out.print(question + ": ");
            answer = sc.nextLine();
        }
        return answer;
    }


}

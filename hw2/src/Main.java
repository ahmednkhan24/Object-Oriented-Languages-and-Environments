//package hw2;

import java.util.Scanner;

public class Main {

    /*
        Returns true if the number is out of range, false if not.
    */
    private static boolean invalidValue(int value, int min, int max) {
        if (value < min || value > max)
            return true;
        else
            return false;
    }

    /*
        Returns the number entered by the user
        Numbers can only either be 0, 1, 2, 3
    */
    private static int getValue(Scanner scanner) {
        int action = 0;
        do {
            System.out.print("Enter action number [0-7]: ");
            action = scanner.nextInt();

        } while (invalidValue(action, 0, 7));
        return action;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Exec e = new Exec();

        // get a number 0-7
        int action = getValue(scanner);

        // exit on 0
        while (action != 0) {
            switch(action) {
                case 1:
                    System.out.println("1");
                    e.loadDirectory();
                    break;
                case 2:
                    System.out.println("2");
                    e.addToDirectory();
                    break;
                case 3:
                    System.out.println("3");
                    e.nameSearch();
                    break;
                case 4:
                    System.out.println("4");
                    e.courseSearch();
                    break;
                case 5:
                    System.out.println("5");
                    e.noInstructorCourses();
                    break;
                case 6:
                    System.out.println("6");
                    e.updatePayments();
                    break;
                case 7:
                    System.out.println("7");
                    e.sendEmail();
                    break;
                default:
                    System.out.println("Invalid value");
            }

            // get the next action command
            action = getValue(scanner);
        }

        System.out.println("Goodbye");
        return;
    }
}
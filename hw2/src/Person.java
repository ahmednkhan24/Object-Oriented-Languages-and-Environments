//package hw2;

import java.util.ArrayList;
import java.util.Scanner;

public class Person {
    private int userID;
    private String role;
    private String lastName;
    private String firstName;
    private String emailAddress;

    // default constructor
    public Person(String role) {
        this.role         = role;
        this.userID       = -1;
        this.lastName     = "empty";
        this.firstName    = "empty";
        this.emailAddress = "empty";
    }

    // fully qualified constructor
    public Person(int userID, String role, String firstName, String lastName, String emailAddress) {
        this.role         = role;
        this.userID       = userID;
        this.lastName     = lastName;
        this.firstName    = firstName;
        this.emailAddress = emailAddress;
    }

    // getters
    public String getRole() {
        return role;
    }

    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    // setters
    public void setRole(String role) {
        this.role = role;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // setters via user input
    public void inputPersonData(String role) {
        int uid = inputUserId();
        String[] fullName = inputFullName();
        String email = inputEmail();

        setRole(role);
        setUserID(uid);
        setFirstName(fullName[0]);
        setLastName(fullName[1]);
        setEmailAddress(email);
    }

    private int inputUserId() {
        Scanner scanner = new Scanner(System.in);
        int userId = 0;
        do {
            System.out.print("Enter the user's ID: ");
            userId = scanner.nextInt();
        } while(userId < 0);
        return userId;
    }

    private String[] inputFullName() {
        Scanner scanner = new Scanner(System.in);
        String fullName = "na";
        do {
            System.out.print("Enter name of person (FirstName LastName): ");
            fullName = scanner.nextLine();
        } while (!fullName.contains(" "));
        return fullName.split(" ");
    }

    private String inputEmail() {
        Scanner scanner = new Scanner(System.in);
        String email = "na";
        do {
            System.out.print("Enter user's email address: ");
            email = scanner.nextLine();
        } while (!email.contains("@") && email.length() <= 2);
        return email;
    }

    /*
        returns a positive decimal value from user input
    */
    protected double enterPositiveDouble(String message) {
        Scanner scanner = new Scanner(System.in);
        double value = 0.00;
        do {
            System.out.print(message);
            value = scanner.nextDouble();
        } while (value < 0.00);
        return value;
    }

    /*
        returns a String containing all data fields associated with a Person
    */
    protected String dataToString() {
        StringBuilder sb = new StringBuilder();
        if (getUserID() != -1) {
            sb.append("User ID: " + getUserID());
            sb.append("\n");
        }
        sb.append("Role: " + getRole().toUpperCase());
        sb.append("\n");
        sb.append("Name: " + getFirstName() + " " + getLastName());
        sb.append("\n");
        sb.append("Email: " + getEmailAddress());
        sb.append("\n");
        return sb.toString();
    }

    /*
        converts a list of strings to 1 string separated by commas
    */
    protected String listToString(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1)
                sb.append(", ");
        }
        return sb.toString();
    }
}
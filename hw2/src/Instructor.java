//package hw2;

import java.util.ArrayList;
import java.util.Scanner;

public class Instructor extends Staff {
    private ArrayList<String> coursesTeaching;

    // default constructor
    public Instructor(String role) {
        super(role);
        coursesTeaching = new ArrayList<>();
    }

    // fully qualified constructor
    public Instructor(int userID, String role, String first, String last, String email, double salary, ArrayList<String> coursesTeaching) {
        super(userID, role, first, last, email, salary);
        this.coursesTeaching = coursesTeaching;
    }

    // getters
    public ArrayList<String> getCoursesTeaching() {
        return coursesTeaching;
    }

    // setters
    public void setCoursesTeaching(ArrayList<String> coursesTeaching) {
        this.coursesTeaching = coursesTeaching;
    }

    // setters via user input
    public void inputInstructorData() {
        inputStaffData();
        ArrayList<String> teaching = enterCourses("Enter courses teaching:");

        setCoursesTeaching(teaching);
    }

    /*
        returns a list of courses from the user
    */
    private ArrayList<String> enterCourses(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> c = new ArrayList<>();
        System.out.print("Enter course name ['exit' to finish]: ");
        String input = scanner.nextLine();
        while (!input.equals("exit")) {
            // remove all whitespace from the string
            input = input.replaceAll("\\s+", "").toUpperCase();
            c.add(input);
            System.out.print("Enter course name ['exit' to finish]: ");
            input = scanner.nextLine();
        }
        return c;
    }

    /*
        creates a string of just the instructor's name and the courses he/she is currently teaching
    */
    public String coursesInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + getFirstName() + " " + getLastName());
        sb.append("\n");
        sb.append("Courses Teaching: ");
        if (!coursesTeaching.isEmpty()) {
            sb.append(listToString(coursesTeaching));
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void pay() {
        setTotalPayment(getSalary() * coursesTeaching.size());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(staffData());
        if (!coursesTeaching.isEmpty()) {
            sb.append("Courses Teaching: ");
            sb.append(listToString(coursesTeaching));
            sb.append("\n");
        }
        return sb.toString();
    }
}
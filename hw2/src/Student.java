//package hw2;

import java.util.ArrayList;
import java.util.Scanner;

public class Student extends Person {
    private double gpa;
    private ArrayList<String> coursesTaken;
    private ArrayList<String> coursesTaking;

    // default constructor
    public Student(String role) {
        super(role);
        gpa = 0.00;
        coursesTaken  = new ArrayList<>();
        coursesTaking = new ArrayList<>();
    }

    // fully qualified constructor
    public Student(int userID, String role, String first, String last, String email, double gpa,
                   ArrayList<String> coursesTaken, ArrayList<String> coursesTaking) {
        super(userID, role, first, last, email);
        this.gpa           = gpa;
        this.coursesTaken  = coursesTaken;
        this.coursesTaking = coursesTaking;
    }

    // getters
    public double getGPA() {
        return gpa;
    }

    public ArrayList<String> getCoursesTaken() {
        return coursesTaken;
    }

    public ArrayList<String> getCoursesTaking() {
        return coursesTaking;
    }

    // setters
    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

    public void setCoursesTaken(ArrayList<String> coursesTaken) {
        this.coursesTaken = coursesTaken;
    }

    public void setCoursesTaking(ArrayList<String> coursesTaking) {
        this.coursesTaking = coursesTaking;
    }

    // setters via user input
    public void inputStudentData() {
        inputPersonData(getRole());
        double gradePointAverage = enterPositiveDouble("Enter user's GPA: ");
        ArrayList<String> taking = enterCourses("Enter courses taking:");
        ArrayList<String> taken = enterCourses("Enter courses taken:");

        setGPA(gradePointAverage);
        setCoursesTaking(taking);
        setCoursesTaken(taken);
    }

    /*
        returns a list of courses from the user
    */
    protected ArrayList<String> enterCourses(String message) {
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
        creates a string of all data fields associated with a Student
    */
    protected String studentData() {
        StringBuilder sb = new StringBuilder();
        sb.append(dataToString());

        if (getGPA() > 0.00) {
            sb.append("GPA: " + getGPA());
            sb.append("\n");
        }

        if (!coursesTaking.isEmpty()) {
            sb.append("Courses Taking: ");
            sb.append(listToString(coursesTaking));
            sb.append("\n");
        }

        if (!coursesTaken.isEmpty()) {
            sb.append("Courses Taken: ");
            sb.append(listToString(coursesTaken));
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return studentData();
    }
}
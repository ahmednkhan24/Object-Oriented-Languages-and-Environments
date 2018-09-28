//package hw2;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TeachingAssistant extends Student implements Payable {
    private double salary;
    private double hours;
    private double totalPayment;
    private ArrayList<String> coursesTeaching;

    // default constructor
    public TeachingAssistant(String role) {
        super(role);
        salary          = 0.00;
        hours           = 0.00;
        totalPayment    = 0.00;
        coursesTeaching = new ArrayList<>();
    }

    // fully qualified constructor
    public TeachingAssistant(int userID, String role, String first, String last, String email,
                             double gpa, double salary, double hours, ArrayList<String> coursesTaken,
                             ArrayList<String> coursesTaking, ArrayList<String> coursesTeaching) {
        super(userID, role, first, last, email, gpa, coursesTaken, coursesTaking);
        this.hours           = hours;
        this.salary          = salary;
        this.totalPayment    = 0.00;
        this.coursesTeaching = coursesTeaching;
    }

    // getters
    public double getSalary() {
        return salary;
    }

    public double getHours() {
        return hours;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public ArrayList<String> getCoursesTeaching() {
        return coursesTeaching;
    }

    // setters
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public void setCoursesTeaching(ArrayList<String> coursesTeaching) {
        this.coursesTeaching = coursesTeaching;
    }

    // setters via user input
    public void inputTaData() {
        inputStudentData();
        double sal = enterPositiveDouble("Enter user's salary: ");
        double hrs = enterPositiveDouble("Enter user's hours: ");
        ArrayList<String> teaching = enterCourses("Enter courses teaching:");

        setSalary(sal);
        setHours(hrs);
        setCoursesTeaching(teaching);
    }

    @Override
    public void pay() {
        totalPayment += (getHours() * getSalary());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(studentData());

        if (!coursesTeaching.isEmpty()) {
            sb.append("Courses Teaching: ");
            sb.append(listToString(coursesTeaching));
            sb.append("\n");
        }

        DecimalFormat df = new DecimalFormat("#.00");
        if (getSalary() > 0.00) {
            sb.append("Salary: $" + df.format(getSalary()));
            sb.append("\n");
        }

        if (getTotalPayment() > 0.00) {
            sb.append("Total Payment: $" + df.format(getTotalPayment()));
            sb.append("\n");
        }

        if (getHours() > 0.00) {
            sb.append("Hours worked: " + getHours());
            sb.append("\n");
        }

        return sb.toString();
    }
}
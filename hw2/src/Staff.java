//package hw2;

import java.text.DecimalFormat;

public class Staff extends Person implements Payable {
    private double salary;
    private double totalPayment;

    // default constructor
    public Staff(String role) {
        super(role);
        salary = 0.00;
        totalPayment = 0.00;
    }

    // fully qualified constructor
    public Staff(int userID, String role, String first, String last, String email, double salary) {
        super(userID, role, first, last, email);
        this.totalPayment = 0.00;
        this.salary = salary;
    }

    // getters
    public double getSalary() {
        return salary;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    // setters
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setTotalPayment(double add) {
        totalPayment += add;
    }

    // setter via user input
    public void inputStaffData() {
        inputPersonData(getRole());
        double sal = enterPositiveDouble("Enter user's salary: ");

        setTotalPayment(sal);
    }

    /*
        creates a string of all data fields associated with a staff
    */
    protected String staffData() {
        StringBuilder sb = new StringBuilder();
        sb.append(dataToString());

        DecimalFormat df = new DecimalFormat("#.00");
        if (getSalary() > 0.00) {
            sb.append("Salary: $" + df.format(getSalary()));
            sb.append("\n");
        }

        if (getTotalPayment() > 0.00) {
            sb.append("Total Payment: $" + df.format(getTotalPayment()));
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void pay() {
        setTotalPayment(salary);
    }

    @Override
    public String toString() {
        return staffData();
    }
}
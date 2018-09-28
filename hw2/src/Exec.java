//package hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Exec {
    private Map<String, Course> courses;
    private ArrayList<Staff> staffs;
    private ArrayList<Student> students;
    private ArrayList<Instructor> instructors;
    private ArrayList<TeachingAssistant> teachingAssistants;

    // constructor
    public Exec() {
        courses            = new HashMap<>();
        staffs             = new ArrayList<>();
        students           = new ArrayList<>();
        instructors        = new ArrayList<>();
        teachingAssistants = new ArrayList<>();
    }

    /*
        returns a file name from the user
    */
    private String getFileName(Scanner scanner) {
        String fileName;
        System.out.print("Enter name of .csv file: ");
        fileName = scanner.nextLine();

        if (!fileName.contains(".csv")) {
            fileName = fileName.concat(".csv");
        }
        return fileName;
    }

    /*
        checks if the given file exists
    */
    private boolean fileExists(String fileName) {
        File f = new File(fileName);
        if (f.exists() && !f.isDirectory())
            return true;
        else
            return false;
    }

    /*
        checks if the given type is supported in this program
    */
    private boolean isValidType(String type) {
        if (type.equals("student") || type.equals("instructor") || type.equals("ta") || type.equals("staff"))
            return true;
        else
            return false;
    }

    /*
        returns the person type from the user
    */
    private String getPersonType(Scanner scanner) {
        String type = "na";
        do {
            System.out.print("Enter the type of person: [Student, Instructor, TA, Staff]: ");
            type = scanner.nextLine().toLowerCase();
        } while(!isValidType(type));
        return type;
    }

    /*
        returns the full name of a person from the user
    */
    private String[] getFullName(Scanner scanner) {
        String fullName = "na";
        do {
            System.out.print("Enter name of person (FirstName LastName): ");
            fullName = scanner.nextLine();
        } while (!fullName.contains(" "));
        return fullName.split(" ");
    }

    /*
        returns an email address from the user
    */
    private String enterEmail(Scanner scanner) {
        String email = "na";
        do {
            System.out.print("Enter user's email address: ");
            email = scanner.nextLine();
        } while (!email.contains("@") && email.length() <= 2);
        return email;
    }

    /*
        takes a single string separated by commas and returns a list from the commas
    */
    private ArrayList<String> buildList(String s) {
        // remove all whitespace from the string
        s = s.replaceAll("\\s+", "");

        if (s.length() > 0)
            return new ArrayList<String>(Arrays.asList(s.split(",")));
        else
            return new ArrayList<String>();
    }

    /*
        updates the 3 data lists for this class depending on what role is provided
        Role        List
        student     coursesTaking
        instructor  coursesTeaching
        ta          coursesTeaching
    */
    private void updateCourses(Person p, String role, ArrayList<String> listOfCourses) {
        for (String courseName : listOfCourses) {
            courseName = courseName.toUpperCase();
            Course thisCourse;

            if (courses.containsKey(courseName))
                thisCourse = courses.get(courseName);
            else
                thisCourse = new Course();

            thisCourse.setName(courseName.toUpperCase());

            if (role.equals("instructor"))
                thisCourse.setInstructor(p);
            else if (role.equals("student"))
                thisCourse.addToList(thisCourse.getStudents(), p);
            else if (role.equals("ta"))
                thisCourse.addToList(thisCourse.getTeachingAssistants(), p);

            if (!courses.containsKey(courseName))
                courses.put(courseName, thisCourse);
        }
    }

    /*
        searches all the lists for the user with the given first and last name
    */
    private Person findUser(String firstName, String lastName) {
        for (Student p : students)
            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                return p;
        for (Instructor p : instructors)
            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                return p;
        for (TeachingAssistant p : teachingAssistants)
            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                return p;
        for (Staff p : staffs)
            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                return p;
        return null;
    }

    /*
        searches the students for the user with the given first and last name
    */
    private Person searchStudents(String email) {
        for (Student p : students)
            if (p.getEmailAddress().equals(email))
                return p;
        return null;
    }

    /*
        searches the instructors for the user with the given first and last name
    */
    private Person searchInstructors(String email) {
        for (Instructor p : instructors)
            if (p.getEmailAddress().equals(email))
                return p;
        return null;
    }

    /*
        searches the teaching assistants for the user with the given first and last name
    */
    private Person searchTas(String email) {
        for (TeachingAssistant p : teachingAssistants)
            if (p.getEmailAddress().equals(email))
                return p;
        return null;
    }

    /*
        searches the staff for the user with the given first and last name
    */
    private Person searchStaff(String email) {
        for (Staff p : staffs)
            if (p.getEmailAddress().equals(email))
                return p;
        return null;
    }

    /*
        creates a list of all people who have the query string somewhere in his/her first or last name
    */
    private ArrayList<Person> regularSearch(String query) {
        ArrayList<Person> found = new ArrayList<>();
        for (Person p : students)
            if (p.getFirstName().toLowerCase().contains(query) || p.getLastName().toLowerCase().contains(query))
                found.add(p);
        for (Person p : instructors)
            if (p.getFirstName().toLowerCase().contains(query) || p.getLastName().toLowerCase().contains(query))
                found.add(p);
        for (Person p : teachingAssistants)
            if (p.getFirstName().toLowerCase().contains(query) || p.getLastName().toLowerCase().contains(query))
                found.add(p);
        for (Person p : staffs)
            if (p.getFirstName().toLowerCase().contains(query) || p.getLastName().toLowerCase().contains(query))
                found.add(p);
        return found;
    }

    /*
        creates a list of all employees who
        have the first pattern in his/her first name
        and the last pattern in his/her last name
    */
    private ArrayList<Person> advancedSearch(String first, String last) {
        ArrayList<Person> found = new ArrayList<>();
        for (Person p : students)
            if (p.getFirstName().toLowerCase().contains(first) && p.getLastName().toLowerCase().contains(last))
                found.add(p);
        for (Person p : instructors)
            if (p.getFirstName().toLowerCase().contains(first) && p.getLastName().toLowerCase().contains(last))
                found.add(p);
        for (Person p : teachingAssistants)
            if (p.getFirstName().toLowerCase().contains(first) && p.getLastName().toLowerCase().contains(last))
                found.add(p);
        for (Person p : staffs)
            if (p.getFirstName().toLowerCase().contains(first) && p.getLastName().toLowerCase().contains(last))
                found.add(p);
        return found;
    }

    /*
        the user is further prompted for the name of a comma separated value file (.csv)

        then loads all the names from that file into the directory. T
        he csv contains a comma separated list of attributes specifying the format of the file
        in the first line, and data about people, one person per line, in the remaining lines.

        Since a single format is specified for all people, many of the fields will be empty.
        Some fields may contain multiple values, separated by commas.
        These fields will be surrounded by double quotes.
    */
    public void loadDirectory() {
        Scanner scanner = new Scanner(System.in);
        String fileName = getFileName(scanner);

        if (!fileExists(fileName)) {
            System.out.println("Error: " + fileName + " not found.");
            return;
        }

        // read from the file
        try {
            CsvReader data = new CsvReader(fileName);
            data.readHeaders();

            // parse the data
            while (data.readRecord()) {
                String lastName              = data.get("Last Name");
                String firstName             = data.get("First Name");
                String emailAddress          = data.get("Email");
                String gpaString             = data.get("GPA");
                String roleString            = data.get("Role").toLowerCase();
                String hoursString           = data.get("Hours Assigned");
                String salaryString          = data.get("Salary");
                String userIDString          = data.get("User ID");
                String coursesTakenString    = data.get("Courses Taken");
                String coursesTakingString   = data.get("Courses Taking");
                String coursesTeachingString = data.get("Courses Teaching");

                // clean the data
                int userID = -1;
                if (userIDString.length() > 0) userID = Integer.parseInt(userIDString);

                double gpa = 0.00, hours = 0.00, salary = 0.00;
                if (gpaString.length() > 0)    gpa    = Double.parseDouble(gpaString);
                if (hoursString.length() > 0)  hours  = Double.parseDouble(hoursString);
                if (salaryString.length() > 0) salary = Double.parseDouble(salaryString);

                ArrayList<String> coursesTaken        = buildList(coursesTakenString);
                ArrayList<String> coursesTaking       = buildList(coursesTakingString);
                ArrayList<String> coursesTeaching     = buildList(coursesTeachingString);

                /*
                    figure out what the role of this person is from the role attribute

                    if the role attribute was not given in the file, use context clues to figure it out
                    Student    - A student must be taking a class, and there's no way a student can teach any class
                    TA         - A TA must be taking a class, and must be teaching a class
                    Instructor - An instructor must be teaching a course
                    Staff      - By default the person can only possibly be staff
                */
                if (roleString.equals("student") || !coursesTakingString.isEmpty() && coursesTeachingString.isEmpty()) {
                    Student student = new Student(userID, "student", firstName, lastName, emailAddress, gpa, coursesTaken, coursesTaking);
                    students.add(student);
                    if (!coursesTaking.isEmpty())
                        updateCourses(student, "student", coursesTaking);
                }
                else if (roleString.equals("ta") || !coursesTakingString.isEmpty() && !coursesTeachingString.isEmpty()) {
                    TeachingAssistant ta = new TeachingAssistant(userID, "ta", firstName, lastName, emailAddress, gpa, salary, hours, coursesTaken, coursesTaking, coursesTeaching);
                    teachingAssistants.add(ta);
                    if (!coursesTeaching.isEmpty())
                        updateCourses(ta, "ta", coursesTeaching);
                    if (!coursesTaking.isEmpty())
                        updateCourses(ta, "student", coursesTaking);
                }
                else if (roleString.equals("instructor") || !coursesTeachingString.isEmpty()) {
                    Instructor instructor = new Instructor(userID, "instructor", firstName, lastName, emailAddress, salary, coursesTeaching);
                    instructors.add(instructor);
                    if (!coursesTeaching.isEmpty())
                        updateCourses(instructor, "instructor", coursesTeaching);
                }
                else {
                    Staff staff = new Staff(userID, "staff", firstName, lastName, emailAddress, salary);
                    staffs.add(staff);
                }
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        System.out.println("Data successfully loaded.");
    }

    /*
        the user is further prompted for a name to enter into the directory.
        This name should be in the format FirstName LastName.
        The user is then prompted to enter the type of person,
        then based on that answer asked to enter values in each of the relevant fields.
        The person is then added to the specific directory he/she belongs to
    */
    public void addToDirectory() {
        Scanner scanner = new Scanner(System.in);
        String type = getPersonType(scanner);

        if (type.equals("student")) {
            Student student = new Student("student");
            student.inputStudentData();
            updateCourses(student, "student", student.getCoursesTaking());
            students.add(student);
        }
        else if (type.equals("instructor")) {
            Instructor instructor = new Instructor("instructor");
            instructor.inputInstructorData();
            updateCourses(instructor, "instructor", instructor.getCoursesTeaching());
            instructors.add(instructor);
        }
        else if (type.equals("ta")) {
            TeachingAssistant ta = new TeachingAssistant("ta");
            ta.inputTaData();
            updateCourses(ta, "ta", ta.getCoursesTeaching());
            updateCourses(ta, "student", ta.getCoursesTaking());
            teachingAssistants.add(ta);
        }
        else if (type.equals("staff")) {
            Staff staff = new Staff("staff");
            staff.inputStaffData();
            staffs.add(staff);
        }
        else {
            System.out.println("Sorry, program does not support that type.");
        }
    }

    /*
        the user is prompted for a name search query.
        This should behave the same as the query from HW1, repeated here.
        The sequence of characters they enter becomes a substring to be
        matched against first and last names in the database.

        If there is a space in the sequence, the program searches for a match
        among the first name to the sequence leading up to the space,
        and the last name to the sequence after the space.

        All names in the database that match the query should be printed out,
        with all of the data associated with that user and a blank line between users.

    */
    public void nameSearch() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter search query: ");
        String query = scanner.nextLine().toLowerCase();

        ArrayList<Person> found;

        if (query.contains(" ")) {
            String[] name = query.split(" ");
            found = advancedSearch(name[0], name[1]);
        }
        else
            found = regularSearch(query);

        System.out.println("Found people:");
        for (Person p : found)
            System.out.println(p.toString());
    }

    /*
        the user is prompted for the name of a course.
        The application should output
            1. the name of the instructor for the course, if there is one,
            2. followed by the teaching assistants, if there are any,
            3. followed by the names of all students currently taking the course.
    */
    void courseSearch() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine().toUpperCase();

        // remove all whitespace from the strings
        courseName = courseName.replaceAll("\\s+", "").toUpperCase();

        if (courses.containsKey(courseName)) {
            Course c = courses.get(courseName);
            System.out.println(c.toString());
        }
        else {
            System.out.println("Course was not found.");
        }
    }

    /*
        the application should list all courses currently being taken by students which do not have an instructor,
        followed by all instructors alongside the courses they are currently instructing.
    */
    void noInstructorCourses() {
        Set<String> setCodes = courses.keySet();
        Iterator<String> it = setCodes.iterator();

        while (it.hasNext()) {
            Course c = courses.get(it.next());
            if (!c.hasInstructor())
                System.out.println(c.toString());
        }

        System.out.println("\nALL INSTRUCTORS");
        for (Instructor p : instructors) {
            System.out.println(p.coursesInfo());
        }
    }

    /*
        all accounts should be updated with a payment.

        Instructors should be credited according to their salary multiplied by the number of classes they are teaching.
        TAs should be credited according to the product of their hours worked and salary.
        Staff should be credited according to their salary.
    */
    void updatePayments() {
        for (Staff staff : staffs)
            staff.pay();
        for (Instructor instructor : instructors)
            instructor.pay();
        for (TeachingAssistant ta : teachingAssistants)
            ta.pay();

        int total = staffs.size() + instructors.size() + teachingAssistants.size();
        System.out.println(total + " payments have been processed.");
    }

    /*
        the user is prompted for the name of a user.
        Then, the user is prompted for a list of emails.
        Then output for each email whether an email could be successfully sent according to the table
    */
    void sendEmail() {
        Scanner scanner = new Scanner(System.in);

        String[] name    = getFullName(scanner);
        String firstName = name[0];
        String lastName  = name[1];

        Person user = findUser(firstName, lastName);

        if (user == null) {
            System.out.println("Could not find user.");
            return;
        }

        System.out.println("Enter email addresses [@@ to quit]: ");
        ArrayList<String> emails = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            String e = enterEmail(scanner);
            if (e.equals("@@"))
                flag = false;
            else
                emails.add(e);
        }

        for (String email : emails) {
            Person p;

            if (user.getRole().equals("student")) {
                p = searchTas(email);
            }
            else if (user.getRole().equals("instructor")) {
                p = searchInstructors(email);
                if (p == null)
                    p = searchStaff(email);
            }
            else if (user.getRole().equals("ta")) {
                p = searchStudents(email);
                if (p == null)
                    p = searchTas(email);
                if (p == null)
                    p = searchInstructors(email);
            }
            else {
                p = searchStudents(email);
                if (p == null)
                    p = searchTas(email);
                if (p == null)
                    p = searchStaff(email);
            }

            if (p == null)
                System.out.println("Email was not successfully sent to " + email);
            else
                System.out.println("Email was successfully sent to " + email);
        }
    }
}
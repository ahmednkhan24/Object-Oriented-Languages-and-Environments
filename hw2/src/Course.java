//package hw2;

import java.util.ArrayList;

public class Course {
    private String name;
    private Person instructor;
    private ArrayList<Person> students;
    private ArrayList<Person> teachingAssistants;

    // constructors
    public Course() {
        name = "empty";
        students = new ArrayList<>();
        instructor = new Instructor("instructor");
        teachingAssistants = new ArrayList<>();
    }

    // getters
    public String getName() {
        return name;
    }

    public Person getInstructor() {
        return instructor;
    }

    public ArrayList<Person> getStudents() {
        return students;
    }

    public ArrayList<Person> getTeachingAssistants() {
        return teachingAssistants;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setInstructor(Person instructor) {
        this.instructor = instructor;
    }

    public void setTeachingAssistants(ArrayList<Person> teachingAssistants) {
        this.teachingAssistants = teachingAssistants;
    }

    public void setStudents(ArrayList<Person> students) {
        this.students = students;
    }

    public void addToList(ArrayList<Person> list, Person p) {
        list.add(p);
    }

    private String listToString(ArrayList<Person> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getFirstName() + " " + list.get(i).getLastName());
            if (i != list.size() - 1)
                sb.append(", ");
        }
        return sb.toString();
    }

    public boolean hasInstructor() {
        if (instructor.getFirstName().equals("empty") || instructor.getLastName().equals("empty"))
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + getName());
        sb.append("\n");

        if (hasInstructor())
            sb.append("Instructor: " + instructor.getFirstName() + " " + instructor.getLastName());
        else
            sb.append("Instructor: TBA");

        sb.append("\n");

        if (!teachingAssistants.isEmpty()) {
            sb.append("Teaching Assistants: ");
            sb.append(listToString(teachingAssistants));
            sb.append("\n");
        }
        else {
            sb.append("Teaching Assistants: TBA");
            sb.append("\n");
        }

        if (!students.isEmpty()) {
            sb.append("Students: ");
            sb.append(listToString(students));
            sb.append("\n");
        }
        else {
            sb.append("Students: None");
            sb.append("\n");
        }

        return sb.toString();
    }
}
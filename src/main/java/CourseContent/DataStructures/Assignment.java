package main.java.CourseContent.DataStructures;
/*
 * Ryan Christopher
 */

/*
 * Assignment Class:
 * This class creates an assignment from the data in assignments.csv
 */
public class Assignment {
    //member variables
    private String courseCode;
    private String assignmentName;
    private String dueDate;
    private double weight;
    private String mark;

    //This constructor populates the member variables from the sent data
    public Assignment(String code, String name, String date, double weight, String mark) {
        courseCode = code;
        assignmentName = name;
        dueDate = date;
        this.weight = weight;
        this.mark = mark;
    }

    //This constructor populates the member variables from a String array
    public Assignment(String[] a) {
        courseCode = a[0];
        assignmentName = a[1];
        dueDate = a[2];
        this.weight = Double.parseDouble(a[3]);
        this.mark = a[4];
    }

    //get methods return the member variable
    public String getCourseCode() {
        return courseCode;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getMark() {
        return mark;
    }

    public double getWeight() {
        return weight;
    }

    //toString() prints all the member variables
    @Override
    public String toString() {
        return "Assignment{" +
                "courseCode='" + courseCode + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", weight=" + weight +
                ", mark=" + mark +
                '}';
    }
}
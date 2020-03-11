package DataStructures;

public class Assignment {
    private String courseCode;
    private String assignmentName;
    private String dueDate;
    private double weight;
    private double mark;

    public Assignment(String code, String name, String date, double weight, double mark) {
        courseCode = code;
        assignmentName = name;
        dueDate = date;
        weight = weight;
        mark = mark;
    }

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

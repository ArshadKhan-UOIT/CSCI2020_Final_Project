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
        this.weight = weight;
        this.mark = mark;
    }

    public Assignment(String[] a) {
        courseCode = a[0];
        assignmentName = a[1];
        dueDate = a[2];
        this.weight = Double.parseDouble(a[3]);
        this.mark = Double.parseDouble(a[4]);
    }

    public String getCourseCode() { return courseCode; }
    public String getAssignmentName() { return assignmentName; }
    public String getDueDate() { return dueDate; }



    public double getMark() {
        return mark;
    }

    public double getWeight() {
        return weight;
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
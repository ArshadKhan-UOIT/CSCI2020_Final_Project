package main.java.CourseContent.DataStructures;
/*
 * Ryan Christopher
 */

/*
 * Exam Class:
 * This class creates an exam from the data in exam.csv
 */
public class Exam {
    //member variables
    private String courseCode;
    private String exam;
    private String date;
    private String time;
    private String location;
    private double weight;
    private String mark;

    //This constructor populates the member variables from the sent data
    public Exam(String s, String exam, String s1, String s2, String s3, double parseDouble, String parseDouble1) {
        courseCode = s;
        this.exam = exam;
        date = s1;
        time = s2;
        location = s3;
        weight = parseDouble;
        mark = parseDouble1;
    }

    //This constructor populates the member variables from a String array
    public Exam(String[] e) {
        courseCode = e[0];
        exam = e[1];
        date = e[2];
        time = e[3];
        location = e[4];
        this.weight = Double.parseDouble(e[5]);
        this.mark = (e[6]);
    }

    //get methods return the member variables
    public String getCourseCode() { return courseCode; }

    public String getExam() { return exam; }

    public String getDate() { return date; }

    public String getTime() { return time; }

    public String getLocation() { return location; }

    public String getMark() {
        return mark;
    }

    public double getWeight() {
        return weight;
    }

    //toString() prints all the member variabless
    @Override
    public String toString() {
        return "Exam{" +
                "courseCode='" + courseCode + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", weight='" + weight + '\'' +
                ", mark=" + mark +
                '}';
    }
}

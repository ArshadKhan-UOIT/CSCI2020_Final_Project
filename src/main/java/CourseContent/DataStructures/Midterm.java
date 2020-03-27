package main.java.CourseContent.DataStructures;
/*
 * Ryan Christopher
 */

/*
 * Midterm Class:
 * This class creates an midterm from the data in midterm.csv
 */
public class Midterm {
    //member variables
    private String courseCode;
    private String midterm;
    private String date;
    private String time;
    private String location;
    private double weight;
    private String mark;

    //This constructor populates the member variables from the sent data
    public Midterm(String s, String midterm, String s1, String s2, String s3, double parseDouble, String parseDouble1) {
        courseCode = s;
        this.midterm = midterm;
        date = s1;
        time = s2;
        location = s3;
        weight = parseDouble;
        mark = parseDouble1;
    }

    //This constructor populates the member variables from a String array
    public Midterm(String[] m) {
        courseCode = m[0];
        midterm = m[1];
        date = m[2];
        time = m[3];
        location = m[4];
        this.weight = Double.parseDouble(m[5]);
        this.mark = m[6];
    }

    //get methods return the member variables
    public String getCourseCode() { return courseCode; }

    public String getMidterm() { return midterm; }

    public String getDate() { return date; }

    public String getTime() { return time; }

    public String getLocation() { return location; }

    public double getWeight() {
        return weight;
    }

    public String getMark() {
        return mark;
    }

    //toString() prints all the member variables
    @Override
    public String toString() {
        return "Midterm{" +
                "courseCode='" + courseCode + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", weight=" + weight +
                ", mark=" + mark +
                '}';
    }

}
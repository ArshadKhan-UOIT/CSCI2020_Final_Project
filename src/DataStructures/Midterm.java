package DataStructures;

public class Midterm {
    private String courseCode;
    private String midterm;
    private String date;
    private String time;
    private String location;
    private double weight;
    private String mark;

    public Midterm(String s, String midterm, String s1, String s2, String s3, double parseDouble, String parseDouble1) {
        courseCode = s;
        this.midterm = midterm;
        date = s1;
        time = s2;
        location = s3;
        weight = parseDouble;
        mark = parseDouble1;
    }

    public Midterm(String[] m) {
        courseCode = m[0];
        midterm = m[1];
        date = m[2];
        time = m[3];
        location = m[4];
        this.weight = Double.parseDouble(m[5]);
        this.mark = m[6];
    }

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
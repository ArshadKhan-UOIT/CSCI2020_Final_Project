package DataStructures;

public class Exam {
    private String courseCode;
    private String date;
    private String time;
    private String location;
    private double weight;
    private double mark;

    public Exam(String s, String s1, String s2, String s3, double parseDouble, double parseDouble1) {
        courseCode = s;
        date = s1;
        time = s2;
        location = s3;
        weight = parseDouble;
        mark = parseDouble1;
    }

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

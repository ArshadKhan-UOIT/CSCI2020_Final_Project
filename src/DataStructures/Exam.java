package DataStructures;

public class Exam {
    private String courseCode;
    private String exam;
    private String date;
    private String time;
    private String location;
    private double weight;
    private double mark;

    public Exam(String s, String exam, String s1, String s2, String s3, double parseDouble, double parseDouble1) {
        courseCode = s;
        this.exam = exam;
        date = s1;
        time = s2;
        location = s3;
        weight = parseDouble;
        mark = parseDouble1;
    }

    public Exam(String[] e) {
        courseCode = e[0];
        exam = e[1];
        date = e[2];
        time = e[3];
        location = e[4];
        this.weight = Double.parseDouble(e[5]);
        this.mark = Double.parseDouble(e[6]);
    }

    public String getCourseCode() { return courseCode; }
    public String getExam() { return exam; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getLocation() { return location; }

    public double getMark() {
        return mark;
    }

    public double getWeight() {
        return weight;
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

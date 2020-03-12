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

    public Exam(String[] e) {
        courseCode = e[0];
        date = e[1];
        time = e[2];
        location = e[3];
        this.weight = Double.parseDouble(e[4]);
        this.mark = Double.parseDouble(e[5]);
    }

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

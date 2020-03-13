package DataStructures;

public class Midterm {
    private String courseCode;
    private String date;
    private String time;
    private String location;
    private double weight;
    private double mark;

    public Midterm(String s, String s1, String s2, String s3, double parseDouble, double parseDouble1) {
        courseCode = s;
        date = s1;
        time = s2;
        location = s3;
        weight = parseDouble;
        mark = parseDouble1;
    }

    public Midterm(String[] m) {
        courseCode = m[0];
        date = m[1];
        time = m[2];
        location = m[3];
        this.weight = Double.parseDouble(m[4]);
        this.mark = Double.parseDouble(m[5]);
    }


    public double getWeight() {
        return weight;
    }

    public double getMark() {
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
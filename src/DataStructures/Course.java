package DataStructures;

import CSV.CSVChanger;

import java.util.List;


public class Course implements Runnable {
    private String courseName;
    private String teacher;
    private String courseCode;
    private String days;
    private String time;
    private String location;

    private Assignment[] assignments;
    private Midterm[] midterms;
    private Exam[] exam;

    public Course(String[] courseInfo) {
        this.courseName = courseInfo[0];
        this.teacher = courseInfo[1];
        this.courseCode = courseInfo[2];
        this.days = courseInfo[3];
        this.time = courseInfo[4];
        this.location = courseInfo[5];
        this.assignments = makeAssignments();
        this.midterms = makeMidterms();
        this.exam = makeExam();
    }

    public Course(String courseName, String teacher, String courseCode, String days, String time, String location) {
        this.courseName = courseName;
        this.teacher = teacher;
        this.courseCode = courseCode;
        this.days = days;
        this.time = time;
        this.location = location;
        this.assignments = makeAssignments();
        this.midterms = makeMidterms();
        this.exam = makeExam();

    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getDays() {
        return days;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return this.location;
    }

    public Assignment[] getAssignments() {
        return assignments;
    }

    public Midterm[] getMidterms() {
        return midterms;
    }

    public Exam[] getExam() {
        return exam;
    }

    @Override
    public void run() {


    }

    private Assignment[] makeAssignments() {
        //find assignments with matching course code or name
        List<String[]> data = CSVChanger.read("assignments.csv", 5);

        for (int i = 0; i < data.size(); i++) {
            String[] str = (data.get(i));

            if (!str[0].equalsIgnoreCase(this.courseCode)) {
                data.remove(i);
                i--;
            }
        }

        Assignment[] a = new Assignment[data.size()];
        for (int i = 0; i < data.size(); i++) {
            a[i] = new Assignment(data.get(i));
        }
        return a;
    }

    private Midterm[] makeMidterms() {
        //find midterms with matching course code or name
        List<String[]> data = CSVChanger.read("midterms.csv", 7);
        for (int i = 0; i < data.size(); i++) {
            String[] str = (data.get(i));

            if (!str[0].equalsIgnoreCase(this.courseCode)) {
                data.remove(i);
                i--;
            }
        }

        Midterm[] m = new Midterm[data.size()];
        for (int i = 0; i < data.size(); i++) {
            m[i] = new Midterm(data.get(i));
        }

        return m;
    }

    private Exam[] makeExam() {
        //find exams with matching course code or name
        List<String[]> data = CSVChanger.read("exams.csv", 7);
        for (int i = 0; i < data.size(); i++) {
            String[] str = (data.get(i));

            if (!str[0].equalsIgnoreCase(this.courseCode)) {
                data.remove(i);
                i--;
            }
        }

        Exam[] e = new Exam[data.size()];
        for (int i = 0; i < data.size(); i++) {
            e[i] = new Exam(data.get(i));
        }

        return e;
    }

    @Override
    public String toString() {
        String str = "Course{" +
                "courseName='" + courseName + '\'' +
                ", teacher='" + teacher + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", days='" + days + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'';
//                ", \nassignments=" + assignments +
//                ", \nmidterms=" + midterms +
//                ", \nexam=" + exam +
//                '}';
        for (Assignment a : assignments) {
            str += a;
        }
        for (Midterm m : midterms) {
            str += m;
        }
        for (Exam e : exam) {
            str += e;
        }
        str += "}";

        return str;
    }

}
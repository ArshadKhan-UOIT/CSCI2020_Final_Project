package DataStructures;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class Course implements Runnable{
    private String courseName;
    private String teacher;
    private String courseCode;
    private String days;
    private String time;
    private String location;

    private List<Assignment> assignments;
    private List<Midterm> midterms;
    private List<Exam> exam;

    public Course (String[] course) {
        this.courseName = course[0];
        this.teacher = course[1];
        this.courseCode = course[2];
        this.days = course[3];
        this.time = course[4];
        this.location = course[5];
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
    public String getLocation() { return this.location; }

    @Override
    public void run() {


    }

    public double getGrade() {
        //calculate grades based off of assignment, midterms, etc
        double grade = 0.0;

        return grade;
    }

    private List<Assignment> makeAssignments() {
        //find assignments with matching course code or name
        //

        List<Assignment> data = null;
        try {
            Reader read = Files.newBufferedReader(Paths.get("DataFiles/assignments.csv"));
            data = new ArrayList<>();

            Iterable<CSVRecord> info = CSVFormat.DEFAULT.parse(read);

            for (CSVRecord record : info) {

                if (record.get(0).equals(courseCode))
                data.add(new Assignment(record.get(0),record.get(1),record.get(2),Double.parseDouble(record.get(3)),Double.parseDouble(record.get(4))));

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public List<Midterm> getMidterms() {
        return midterms;
    }

    public List<Exam> getExam() {
        return exam;
    }

    private List<Midterm> makeMidterms() {
        //find midterms with matching course code or name

        List<Midterm> data = null;
        try {
            Reader read = Files.newBufferedReader(Paths.get("DataFiles/midterms.csv"));
            data = new ArrayList<>();

            Iterable<CSVRecord> info = CSVFormat.DEFAULT.parse(read);

            for (CSVRecord record : info) {
                String checkCode = record.get(0);
                if (checkCode.equals(courseCode))
                    data.add(new Midterm(record.get(0),record.get(1),record.get(2),record.get(3),Double.parseDouble(record.get(4)),Double.parseDouble(record.get(5))));

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return data;

    }

    private List<Exam> makeExam() {
        //find exams with matching course code or name


        List<Exam> data = null;
        try {
            Reader read = Files.newBufferedReader(Paths.get("DataFiles/exams.csv"));
            data = new ArrayList<>();

            Iterable<CSVRecord> info = CSVFormat.DEFAULT.parse(read);

            for (CSVRecord record : info) {

                if (record.get(0).equals(courseCode))
                    data.add(new Exam(record.get(0),record.get(1),record.get(2),record.get(3),Double.parseDouble(record.get(4)),Double.parseDouble(record.get(5))));

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return data;

    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", teacher='" + teacher + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", days='" + days + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", \nassignments=" + assignments +
                ", \nmidterms=" + midterms +
                ", \nexam=" + exam +
                '}';
    }
}

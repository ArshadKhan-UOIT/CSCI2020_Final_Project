package main.java.CourseContent.DataStructures;

import main.java.CourseContent.CSV.CSVChanger;

import java.util.List;
/*
 * Ryan Christopher
 */

/*
 * Course Class:
 * This class creates a course from the data in courses.csv
 */
public class Course {
    //member variables
    private String courseName;
    private String teacher;
    private String courseCode;
    private String days;
    private String time;
    private String location;

    //arrays that hold the assignments, midterms and exams associated with the course
    private Assignment[] assignments;
    private Midterm[] midterms;
    private Exam[] exam;

    //constructor that populates the member variables from a String array input
    public Course(String[] courseInfo) {
        this.courseName = courseInfo[0];
        this.teacher = courseInfo[1];
        this.courseCode = courseInfo[2];
        this.days = courseInfo[3];
        this.time = courseInfo[4];
        this.location = courseInfo[5];
        //calls the corresponding function to fetch the assignments, midterms and exams associated with the course
        this.assignments = makeAssignments();
        this.midterms = makeMidterms();
        this.exam = makeExam();
    }

    //This constructor populates the member variables from the sent data
    public Course(String courseName, String teacher, String courseCode, String days, String time, String location) {
        this.courseName = courseName;
        this.teacher = teacher;
        this.courseCode = courseCode;
        this.days = days;
        this.time = time;
        this.location = location;
        //calls the corresponding function to fetch the assignments, midterms and exams associated with the course
        this.assignments = makeAssignments();
        this.midterms = makeMidterms();
        this.exam = makeExam();

    }

    //get methods to return the member variables
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

    //makeAssignments(): searches assignments.csv to retrieve and create the assignments associated with the course
    private Assignment[] makeAssignments() {
        List<String[]> data = CSVChanger.read("assignments.csv", 5);
        //loop through csv data
        for (int i = 0; i < data.size(); i++) {
            String[] str = (data.get(i));
            //remove if data is not associated with course
            if (!str[0].equalsIgnoreCase(this.courseCode)) {
                data.remove(i);
                i--;
            }
        }
        //create array of assignments for this course
        Assignment[] a = new Assignment[data.size()];
        for (int i = 0; i < data.size(); i++) {
            a[i] = new Assignment(data.get(i));
        }
        return a;
    }

    //makeMidterms(): searches midterms.csv to retrieve and create the midterms associated with the course
    private Midterm[] makeMidterms() {
        List<String[]> data = CSVChanger.read("midterms.csv", 7);
        //loop through csv data
        for (int i = 0; i < data.size(); i++) {
            String[] str = (data.get(i));
            //remove if data is not associated with course
            if (!str[0].equalsIgnoreCase(this.courseCode)) {
                data.remove(i);
                i--;
            }
        }
        //create array of midterms for this course
        Midterm[] m = new Midterm[data.size()];
        for (int i = 0; i < data.size(); i++) {
            m[i] = new Midterm(data.get(i));
        }
        return m;
    }

    //makeExams(): searches exams.csv to retrieve and create the exams associated with the course
    private Exam[] makeExam() {
        List<String[]> data = CSVChanger.read("exams.csv", 7);
        //loop through csv data
        for (int i = 0; i < data.size(); i++) {
            String[] str = (data.get(i));
            //remove if data is not associated with course
            if (!str[0].equalsIgnoreCase(this.courseCode)) {
                data.remove(i);
                i--;
            }
        }
        //create array of exams for this course
        Exam[] e = new Exam[data.size()];
        for (int i = 0; i < data.size(); i++) {
            e[i] = new Exam(data.get(i));
        }
        return e;
    }

    //toString() prints all the data associated to the course
    @Override
    public String toString() {
        String str = "Course{" +
                "courseName='" + courseName + '\'' +
                ", teacher='" + teacher + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", days='" + days + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'';
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
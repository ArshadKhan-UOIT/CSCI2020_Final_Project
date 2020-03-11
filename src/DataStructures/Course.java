package DataStructures;

import CSV.CSVChanger;

import java.util.Arrays;
import java.util.List;

public class Course implements Runnable {
    private String courseName;
    private String teacher;
    private String courseCode;

    private String days;
    private String time;

//    private Assignment[] assignments;
//    private Midterm[] midterms;
//    private Exam exam;

    public Course (String[] course) {
        this.courseName = course[0];
        this.teacher = course[1];
        this.courseCode = course[2];
        this.days = course[3];
        this.time = course[4];

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

    @Override
    public void run() {
        getAssignments();
        getMidterms();
        getExam();
    }

    private void getAssignments() {
        //find assignments with matching course code or name

    }

    private void getMidterms() {
        //find midterms with matching course code or name

    }

    private void getExam() {
        //find exams with matching course code or name

    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", teacher='" + teacher + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", days='" + days + '\'' +
                ", time='" + time + '\'' +
//                ", assignments=" + Arrays.toString(assignments) +
//                ", midterms=" + Arrays.toString(midterms) +
//                ", exam=" + exam +
                '}';
    }
}

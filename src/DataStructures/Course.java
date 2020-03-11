package DataStructures;

import java.io.*;
//import org.apache.commons.csv.*;

public class Course implements Runnable {
    private String courseName;
    private String teacher;
    private String courseCode;

    private String[] days;
    private String time;

    private Assignment[] assignments;
    private Midterm[] midterms;
    private Exam exam;

    public Course () {
        //gather information for the course through the files
        //uses csv thing from labs
//        try {
//            Reader in = new FileReader("data.csv");
//            Iterable<CSVRecord> records =
//                    CSVFormat.DEFAULT.withHeader("First Name", "Last Name", "SID",
//                            "Grade").parse(in);
//            for (CSVRecord record: records) {
//                String lastName = record.get("Last Name");
//                String firstName = record.get("First Name");
//                String grade = record.get("Grade");
//                System.out.println(firstName + " " + lastName + " (" +
//                        grade + ")");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
}

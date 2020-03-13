package Pages;

import DataStructures.Assignment;
import DataStructures.Midterm;
import DataStructures.Exam;
import DataStructures.Course;
import Windows.Window;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;


public class CoursesPage extends Page {
   
    public CoursesPage() {
        //Assignmets: course code, assignment, due date, weight , mark
        //what we want when we click a certain course name
        //assignment, due date, weight , mark
        System.out.println("Pages.CoursesPage created");
        //assignment stuff
        TableView<Assignment> asmtTable = new TableView();
//        asmtTable.setMaxWidth(502);
        TableColumn<Assignment, String> asmtCol = new TableColumn("Type");
        asmtCol.setMinWidth(200);
        TableColumn<Assignment, String> asmtDueDateCol = new TableColumn("Due Date");
        asmtDueDateCol.setMinWidth(100);
        TableColumn<Assignment, Double> asmtWightCol = new TableColumn("Weight (%)");
        asmtWightCol.setMinWidth(100);
        TableColumn<Assignment, Double> asmtMarkCol = new TableColumn("Mark (%)");
        asmtMarkCol.setMinWidth(100);
        //midterm stuff
        //CSCI2160, Feb, 5pm, UP1501, 15, 90
        //String courseCode, String midterm, String date, String time, String location, double weight, double mark;
        TableView<Midterm> midTable = new TableView();
//        midTable.setMaxWidth(502);
        TableColumn<Midterm, String> midCol = new TableColumn("Type");
        midCol.setMinWidth(200);
        TableColumn<Midterm, String> midDueDateCol = new TableColumn("Date");
        midDueDateCol.setMinWidth(100);
        TableColumn<Midterm, String> midTimeCol = new TableColumn("Time");
        midTimeCol.setMinWidth(100);
        TableColumn<Midterm, String> midLocationCol = new TableColumn("Location");
        midLocationCol.setMinWidth(100);
        TableColumn<Midterm, Double> midWightCol = new TableColumn("Weight (%)");
        midWightCol.setMinWidth(100);
        TableColumn<Midterm, Double> midMarkCol = new TableColumn("Mark (%)");
        midMarkCol.setMinWidth(100);
        //  Exam -  String courseCode,String exam,String date,String time,String location,double weight,double mark;
        TableView<Exam> examTable = new TableView();
//        midTable.setMaxWidth(502);
        TableColumn<Exam, String> examCol = new TableColumn("Type");
        examCol.setMinWidth(200);
        TableColumn<Exam, String> examDueDateCol = new TableColumn("Date");
        examDueDateCol.setMinWidth(100);
        TableColumn<Exam, String> examTimeCol = new TableColumn("Time");
        examTimeCol.setMinWidth(100);
        TableColumn<Exam, String> examLocationCol = new TableColumn("Location");
        examLocationCol.setMinWidth(100);
        TableColumn<Exam, Double> examWightCol = new TableColumn("Weight (%)");
        examWightCol.setMinWidth(100);
        TableColumn<Exam, Double> examMarkCol = new TableColumn("Mark (%)");
        examMarkCol.setMinWidth(100);
        //private String courseName;
        //Course - String courseName, String teacher,String courseCode,String days,String time,String location;
        TableView<Course> courseTable = new TableView();
//        midTable.setMaxWidth(502);
        TableColumn<Course, String> courseCodeCol = new TableColumn("Course Code");
        courseCodeCol.setMinWidth(200);
        TableColumn<Course, String> courseProfCol = new TableColumn("Prof");
        courseProfCol.setMinWidth(100);
        TableColumn<Course, String> courseDaysCol = new TableColumn("Days");
        courseDaysCol.setMinWidth(100);
        TableColumn<Course, String> courseTimeCol = new TableColumn("Times");
        courseTimeCol.setMinWidth(100);
        TableColumn<Course, String> courseLocationCol = new TableColumn("Location");
        courseLocationCol.setMinWidth(100);

//        ObservableList<Assignment> ObList = FXCollections.observableArrayList();
//        ObservableList<Assignment> dueDateObList = FXCollections.observableArrayList();
//        ObservableList<Assignment> weightObList = FXCollections.observableArrayList();
//        ObservableList<Assignment> markObList = FXCollections.observableArrayList();
        //List<Assignment> assignmentList = c.getAssignments();
//        assignmentList[i].getCourseCode()
        HBox buttons = new HBox();
        //display courses as buttons

        Button[] b =new Button[Window.courses.length];


        for (int i=0; i< Window.courses.length;i++) {
            b[i] = new Button(Window.courses[i].getCourseName());
            buttons.getChildren().add(b[i]);
            System.out.println(Window.courses[i]); //test to make sure has the correct info
        }
        //assignment
        asmtCol.setCellValueFactory(new PropertyValueFactory<>("assignmentName"));
        asmtDueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        asmtWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        asmtMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        asmtTable.setItems(getAssignmentData());
        asmtTable.getColumns().addAll(asmtCol, asmtDueDateCol, asmtWightCol, asmtMarkCol);
        //midterm, String courseCode, String midterm, String date, String time, String location, double weight, double mark;
        midCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        midDueDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        midTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        midLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        midWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        midMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        midTable.setItems(getMidtermData());
        midTable.getColumns().addAll(midCol, midDueDateCol, midTimeCol, midLocationCol, midWightCol, midMarkCol);
        //  Exam -  String courseCode,String exam,String date,String time,String location,double weight,double mark;
        examCol.setCellValueFactory(new PropertyValueFactory<>("exam"));
        examDueDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        examTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        examLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        examWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        examMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        examTable.setItems(getExamData());
        examTable.getColumns().addAll(examCol, examDueDateCol, examTimeCol, examLocationCol, examWightCol, examMarkCol);
        //Course - String courseName, String teacher,String courseCode,String days,String time,String location;
        courseCodeCol.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        courseProfCol.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        courseDaysCol.setCellValueFactory(new PropertyValueFactory<>("days"));
        courseTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        courseLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        courseTable.setItems(getCourseData());
        courseTable.getColumns().addAll(courseCodeCol, courseProfCol, courseDaysCol, courseTimeCol, courseLocationCol);



        mainPane.add(buttons,0,0);
        mainPane.add(courseTable,0,1);
        mainPane.add(examTable,0,2);
        mainPane.add(midTable,0,3);
        mainPane.add(asmtTable,0,4);
    }
    public ObservableList<Assignment> getAssignmentData() {
        ObservableList<Assignment> asmtObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
//            List<Assignment> assignmentList = c.getAssignments();
            Assignment[] assignmentList = c.getAssignments();
            for (Assignment a: assignmentList) {
                asmtObList.add(new Assignment(a.getCourseCode(),a.getAssignmentName(),a.getDueDate(),a.getWeight(),a.getMark()));
            }
        }
        return asmtObList;
    }
    public ObservableList<Midterm> getMidtermData() {
        ObservableList<Midterm> midObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
//            List<Assignment> assignmentList = c.getAssignments();
            Midterm[] midtermList = c.getMidterms();
            for (Midterm a: midtermList) {  //String courseCode, String midterm, String date, String time, String location, double weight, double mark;
                midObList.add(new Midterm(a.getCourseCode(),a.getMidterm(),a.getDate(),a.getTime(),a.getLocation(),a.getWeight(),a.getMark()));
            }
        }
        return midObList;
    }
    public ObservableList<Exam> getExamData() {
        ObservableList<Exam> examObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
//            List<Assignment> assignmentList = c.getAssignments();
            Exam[] examList = c.getExam();
            for (Exam a: examList) {          //  Exam -  String courseCode,String exam,String date,String time,String location,double weight,double mark;
                examObList.add(new Exam(a.getCourseCode(),a.getExam(),a.getDate(),a.getTime(),a.getLocation(),a.getWeight(),a.getMark()));
            }
        }
        return examObList;
    }
    public ObservableList<Course> getCourseData() {
        ObservableList<Course> courseObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
//            List<Assignment> assignmentList = c.getAssignments();
//            ArrayList<Course> list = new ArrayList<>();
//            for (Course a: list) {          //          //String courseName, String teacher,String courseCode,String days,String time,String location;
                courseObList.add(new Course(c.getCourseName(),c.getTeacher(),c.getCourseCode(),c.getDays(),c.getTime(),c.getLocation()));
//            }
        }
        return courseObList;
    }

    public static void main(String[] args) {
    }

}

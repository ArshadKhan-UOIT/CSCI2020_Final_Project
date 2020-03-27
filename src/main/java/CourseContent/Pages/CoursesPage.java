package main.java.CourseContent.Pages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.java.CourseContent.DataStructures.Assignment;
import main.java.CourseContent.DataStructures.Course;
import main.java.CourseContent.DataStructures.Exam;
import main.java.CourseContent.DataStructures.Midterm;
import main.java.CourseContent.Windows.Window;


public class CoursesPage extends Page {
    TableView<Course> courseTableView;      //initializing table view or table columns
    TableView<Exam> examTableView;
    TableView<Midterm> midtermTableView;
    TableView<Assignment> assignmentTableView;
    TableColumn<Course, String> courseCodeCol, courseProfCol, courseDaysCol, courseTimeCol, courseLocationCol;
    TableColumn<Exam, String> examCol, examDueDateCol, examTimeCol, examLocationCol;
    TableColumn<Exam, Double> examWightCol, examMarkCol;
    TableColumn<Midterm, String> midCol, midDueDateCol, midTimeCol, midLocationCol;
    TableColumn<Midterm, Double> midWightCol, midMarkCol;
    TableColumn<Assignment, String> asmtCol, asmtDueDateCol;
    TableColumn<Assignment, Double> asmtWightCol, asmtMarkCol;
    String[] courseCodes = {"MATH2050", "CSCI2160", "CSCI2040", "CSCI2072", "CSCI2020"};
    String[] courseContent = {"Asmt (%)", "Midterm (%)", "Exam (%)"};
    PieChart pieChart = new PieChart();
    BorderPane bPane = new BorderPane();
    GridPane centerPane = new GridPane();
    Pane rightPane = new Pane();

    public CoursesPage() {
        courseTableView = new TableView();
        examTableView = new TableView();
        midtermTableView = new TableView();
        assignmentTableView = new TableView();
        courseTableView.setMaxHeight(200);       //setting heights
        examTableView.setMaxHeight(200);
        midtermTableView.setMaxHeight(200);
        HBox buttons = new HBox();

        Button[] b = new Button[Window.courses.length];

        for (int i = 0; i < Window.courses.length; i++) {   //for buttons
            b[i] = new Button(Window.courses[i].getCourseName());
            buttons.getChildren().add(b[i]);
        }
        for (int i = 0; i < Window.courses.length; i++) {   //when the button is clicked
            final int index = i;
            b[i].setOnMouseClicked(e -> {
                getTableColumn(index);
                getPieGraph(index);
            });
        }
        bPane.setTop(buttons);      //adds everything
        bPane.setCenter(centerPane);
        bPane.setRight(rightPane);
        mainPane.add(bPane, 0, 0);
        getTableColumn(0);  //for the initial startup
        getPieGraph(0);
    }


    private void getPieGraph(int index) {   //for the pie graph
        double[][] courseWeightArr = new double[Window.courses.length][3]; //[index][content] -> [For each Course][Content for each Course (assignment,midterm,exam)]
        for (int i = 0; i < courseWeightArr[index].length; i++) {
            courseWeightArr[i][0] = 0; //initializing
            courseWeightArr[i][1] = 0; //initializing
            courseWeightArr[i][2] = 0; //initializing
        }
        for (Course c : Window.courses) {   //initializing the course weight 2D Array
            // putting in all the info for assignments, midterms, and exams for each course based on which button is clicked

            for (Assignment a : c.getAssignments()) {
                courseWeightArr[index][0] += a.getWeight();
            }

            for (Midterm m : c.getMidterms()) {
                courseWeightArr[index][1] += m.getWeight();
            }

            for (Exam e : c.getExam()) {
                courseWeightArr[index][2] += e.getWeight();
            }
        }

        Pane rightPane = new Pane();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();   //observal list for the pie chart pate
        for (int contentIndex = 0; contentIndex < courseWeightArr[index].length; contentIndex++) {  //filling the observal list
            pieChartData.add(new PieChart.Data(courseContent[contentIndex], courseWeightArr[index][contentIndex]));
        }
        pieChart.setData(pieChartData); //setting it to the pei chart
        pieChart.setTitle("Weight's");
        rightPane.getChildren().add(pieChart);  //adding it to the pane
        bPane.setRight(rightPane);
    }
    private void getTableColumn(int index) {        //initializes the table column for courses, midterms, assignments, and exams
        courseCodeCol = new TableColumn("Course Code");
        courseCodeCol.setMinWidth(200);
        courseProfCol = new TableColumn("Prof");
        courseProfCol.setMinWidth(200);
        courseDaysCol = new TableColumn("Days");
        courseDaysCol.setMinWidth(100);
        courseTimeCol = new TableColumn("Times");
        courseTimeCol.setMinWidth(100);
        courseLocationCol = new TableColumn("Location");
        courseLocationCol.setMinWidth(100);
        courseCodeCol.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        courseProfCol.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        courseDaysCol.setCellValueFactory(new PropertyValueFactory<>("days"));
        courseTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        courseLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        courseTableView.setItems(getCourseData(index));
        courseTableView.getColumns().setAll(courseCodeCol, courseProfCol, courseDaysCol, courseTimeCol, courseLocationCol);

        examCol = new TableColumn("Type");
        examCol.setMinWidth(200);
        examDueDateCol = new TableColumn("Date");
        examDueDateCol.setMinWidth(100);
        examTimeCol = new TableColumn("Time");
        examTimeCol.setMinWidth(100);
        examLocationCol = new TableColumn("Location");
        examLocationCol.setMinWidth(100);
        examWightCol = new TableColumn("Weight (%)");
        examWightCol.setMinWidth(100);
        examMarkCol = new TableColumn("Mark (%)");
        examMarkCol.setMinWidth(100);
        examCol.setCellValueFactory(new PropertyValueFactory<>("exam"));
        examDueDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        examTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        examLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        examWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        examMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        examTableView.setItems(getExamData(index));
        examTableView.getColumns().setAll(examCol, examDueDateCol, examTimeCol, examLocationCol, examWightCol, examMarkCol);

        midCol = new TableColumn("Type");
        midCol.setMinWidth(200);
        midDueDateCol = new TableColumn("Date");
        midDueDateCol.setMinWidth(100);
        midTimeCol = new TableColumn("Time");
        midTimeCol.setMinWidth(100);
        midLocationCol = new TableColumn("Location");
        midLocationCol.setMinWidth(100);
        midWightCol = new TableColumn("Weight (%)");
        midWightCol.setMinWidth(100);
        midMarkCol = new TableColumn("Mark (%)");
        midMarkCol.setMinWidth(100);
        midCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        midDueDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        midTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        midLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        midWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        midMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        midtermTableView.setItems(getMidtermData(index));
        midtermTableView.getColumns().setAll(midCol, midDueDateCol, midTimeCol, midLocationCol, midWightCol, midMarkCol);

        asmtCol = new TableColumn("Type");
        asmtCol.setMinWidth(200);
        asmtDueDateCol = new TableColumn("Due Date");
        asmtDueDateCol.setMinWidth(200);
        asmtWightCol = new TableColumn("Weight (%)");
        asmtWightCol.setMinWidth(150);
        asmtMarkCol = new TableColumn("Mark (%)");
        asmtMarkCol.setMinWidth(150);
        asmtCol.setCellValueFactory(new PropertyValueFactory<>("assignmentName"));
        asmtDueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        asmtWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        asmtMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        assignmentTableView.setItems(getAssignmentData(index));
        assignmentTableView.getColumns().setAll(asmtCol, asmtDueDateCol, asmtWightCol, asmtMarkCol);

        GridPane centerPane = new GridPane();

        centerPane.add(courseTableView, 0, 1);      //adds them all to the pane
        centerPane.add(examTableView, 0, 2);
        centerPane.add(midtermTableView, 0, 3);
        centerPane.add(assignmentTableView, 0, 4);

        bPane.setCenter(centerPane);    //adds the grid pane to borderpane
    }

    public ObservableList<Assignment> getAssignmentData(int index) {    //observal list for assignemnt data
        ObservableList<Assignment> asmtObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Assignment[] assignmentList = c.getAssignments();
            for (Assignment a : assignmentList) {
                if (a.getCourseCode().equals(courseCodes[index])) {
                    asmtObList.add(new Assignment(a.getCourseCode(), a.getAssignmentName(), a.getDueDate(), a.getWeight(), a.getMark()));
                }
            }
        }
        return asmtObList;
    }
    public ObservableList<Midterm> getMidtermData(int index) {      //observal list for midterm data
        ObservableList<Midterm> midObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Midterm[] midtermList = c.getMidterms();
            for (Midterm m : midtermList) {
                if (m.getCourseCode().equals(courseCodes[index]))
                    midObList.add(new Midterm(m.getCourseCode(), m.getMidterm(), m.getDate(), m.getTime(), m.getLocation(), m.getWeight(), m.getMark()));
            }
        }
        return midObList;
    }
    public ObservableList<Exam> getExamData(int index) {        //observal list for exam data
        ObservableList<Exam> examObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Exam[] examList = c.getExam();
            for (Exam e : examList) {
                if (e.getCourseCode().equals(courseCodes[index])) {
                    examObList.add(new Exam(e.getCourseCode(), e.getExam(), e.getDate(), e.getTime(), e.getLocation(), e.getWeight(), e.getMark()));
                }
            }
        }
        return examObList;
    }
    public ObservableList<Course> getCourseData(int index) {       //observal list for courses data
        ObservableList<Course> courseObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            if (c.getCourseCode().equals(courseCodes[index])) {
                courseObList.add(new Course(c.getCourseName(),c.getTeacher(),c.getCourseCode(),c.getDays(),c.getTime(),c.getLocation()));
            }
        }
        return courseObList;
    }
}
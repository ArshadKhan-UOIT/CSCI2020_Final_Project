package Pages;

import DataStructures.Assignment;
import DataStructures.Course;
import DataStructures.Exam;
import DataStructures.Midterm;
import Windows.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class CoursesPage extends Page {
    TableView<Course> courseTableView;
    TableView<Exam> examTableView;
    TableView<Midterm> midtermTableView;
    TableView<Assignment> assignmentTableView;
    String[] courseCodes = {"MATH2050", "CSCI2160", "CSCI2040", "CSCI2072", "CSCI2020"};
    BorderPane bPane = new BorderPane();
    GridPane centerPane = new GridPane();

    public CoursesPage() {
        courseTableView = new TableView();
        examTableView = new TableView();
        midtermTableView = new TableView();
        assignmentTableView = new TableView();
        System.out.println("Pages.CoursesPage created");
        HBox buttons = new HBox();

        Button[] b = new Button[Window.courses.length];

        for (int i = 0; i < Window.courses.length; i++) {
            b[i] = new Button(Window.courses[i].getCourseName());
            buttons.getChildren().add(b[i]);
//            System.out.println(Window.courses[i]); //test to make sure has the correct info
            int index = i;
//            b[i].setOnMouseClicked(e->{
//                courseTableView.getItems().clear();
//                examTableView.getItems().clear();
//                midtermTableView.getItems().clear();
//                assignmentTableView.getItems().clear();
//                courseTableView.refresh();
//                examTableView.refresh();
//                midtermTableView.refresh();
//                assignmentTableView.refresh();
//                getTableColumn0();
//            });
        }
        b[0].setOnMouseClicked(e -> {
            courseTableView.getItems().clear();
            examTableView.getItems().clear();
            midtermTableView.getItems().clear();
            assignmentTableView.getItems().clear();
//            courseTableView.refresh();
//            examTableView.refresh();
//            midtermTableView.refresh();
//            assignmentTableView.refresh();
            getTableColumn0();
        });
        b[1].setOnMouseClicked(e -> {
            courseTableView.getItems().clear();
            examTableView.getItems().clear();
            midtermTableView.getItems().clear();
            assignmentTableView.getItems().clear();
//            courseTableView.refresh();
//            examTableView.refresh();
//            midtermTableView.refresh();
//            assignmentTableView.refresh();
            getTableColumn1();
        });
        b[2].setOnMouseClicked(e -> {
            courseTableView.getItems().clear();
            examTableView.getItems().clear();
            midtermTableView.getItems().clear();
            assignmentTableView.getItems().clear();
//            courseTableView.refresh();
//            examTableView.refresh();
//            midtermTableView.refresh();
//            assignmentTableView.refresh();
            getTableColumn2();
        });
        b[3].setOnMouseClicked(e -> {
            courseTableView.getItems().clear();
            examTableView.getItems().clear();
            midtermTableView.getItems().clear();
            assignmentTableView.getItems().clear();
//            courseTableView.refresh();
//            examTableView.refresh();
//            midtermTableView.refresh();
//            assignmentTableView.refresh();
            getTableColumn3();
        });
        b[4].setOnMouseClicked(e -> {
            courseTableView.getItems().clear();
            courseTableView.getItems().clear();
            examTableView.getItems().clear();
            examTableView.getItems().clear();
            midtermTableView.getItems().clear();
            midtermTableView.getItems().clear();
            assignmentTableView.getItems().clear();
            assignmentTableView.getItems().clear();
//            courseTableView.refresh();
//            examTableView.refresh();
//            midtermTableView.refresh();
//            assignmentTableView.refresh();
            getTableColumn4();
        });

        bPane.setTop(buttons);
        bPane.setCenter(centerPane);
        mainPane.add(bPane, 0, 0);

    }


    private void getTableColumn0() {
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
        courseCodeCol.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        courseProfCol.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        courseDaysCol.setCellValueFactory(new PropertyValueFactory<>("days"));
        courseTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        courseLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        courseTableView.setItems(getCourseData0());
        courseTableView.getColumns().setAll(courseCodeCol, courseProfCol, courseDaysCol, courseTimeCol, courseLocationCol);

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
        examCol.setCellValueFactory(new PropertyValueFactory<>("exam"));
        examDueDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        examTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        examLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        examWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        examMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        examTableView.setItems(getExamData0());
        examTableView.getColumns().setAll(examCol, examDueDateCol, examTimeCol, examLocationCol, examWightCol, examMarkCol);

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
        midCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        midDueDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        midTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        midLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        midWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        midMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        midtermTableView.setItems(getMidtermData0());
        midtermTableView.getColumns().setAll(midCol, midDueDateCol, midTimeCol, midLocationCol, midWightCol, midMarkCol);

        TableColumn<Assignment, String> asmtCol = new TableColumn("Type");
        asmtCol.setMinWidth(200);
        TableColumn<Assignment, String> asmtDueDateCol = new TableColumn("Due Date");
        asmtDueDateCol.setMinWidth(100);
        TableColumn<Assignment, Double> asmtWightCol = new TableColumn("Weight (%)");
        asmtWightCol.setMinWidth(100);
        TableColumn<Assignment, Double> asmtMarkCol = new TableColumn("Mark (%)");
        asmtMarkCol.setMinWidth(100);
        asmtCol.setCellValueFactory(new PropertyValueFactory<>("assignmentName"));
        asmtDueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        asmtWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        asmtMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        assignmentTableView.getColumns().removeAll();
        assignmentTableView.setItems(getAssignmentData0());
        assignmentTableView.getColumns().setAll(asmtCol, asmtDueDateCol, asmtWightCol, asmtMarkCol);


        GridPane centerPane = new GridPane();

        centerPane.add(courseTableView, 0, 1);
        centerPane.add(examTableView, 0, 2);
        centerPane.add(midtermTableView, 0, 3);
        centerPane.add(assignmentTableView, 0, 4);

        bPane.setCenter(centerPane);
    }

    private void getTableColumn1() {
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
        courseCodeCol.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        courseProfCol.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        courseDaysCol.setCellValueFactory(new PropertyValueFactory<>("days"));
        courseTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        courseLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        courseTableView.setItems(getCourseData1());
        courseTableView.getColumns().setAll(courseCodeCol, courseProfCol, courseDaysCol, courseTimeCol, courseLocationCol);

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
        examCol.setCellValueFactory(new PropertyValueFactory<>("exam"));
        examDueDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        examTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        examLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        examWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        examMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        examTableView.setItems(getExamData1());
        examTableView.getColumns().setAll(examCol, examDueDateCol, examTimeCol, examLocationCol, examWightCol, examMarkCol);

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
        midCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        midDueDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        midTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        midLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        midWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        midMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        midtermTableView.setItems(getMidtermData1());
        midtermTableView.getColumns().setAll(midCol, midDueDateCol, midTimeCol, midLocationCol, midWightCol, midMarkCol);

        TableColumn<Assignment, String> asmtCol = new TableColumn("Type");
        asmtCol.setMinWidth(200);
        TableColumn<Assignment, String> asmtDueDateCol = new TableColumn("Due Date");
        asmtDueDateCol.setMinWidth(100);
        TableColumn<Assignment, Double> asmtWightCol = new TableColumn("Weight (%)");
        asmtWightCol.setMinWidth(100);
        TableColumn<Assignment, Double> asmtMarkCol = new TableColumn("Mark (%)");
        asmtMarkCol.setMinWidth(100);
        asmtCol.setCellValueFactory(new PropertyValueFactory<>("assignmentName"));
        asmtDueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        asmtWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        asmtMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        assignmentTableView.setItems(getAssignmentData1());
        assignmentTableView.getColumns().setAll(asmtCol, asmtDueDateCol, asmtWightCol, asmtMarkCol);

        GridPane centerPane = new GridPane();

        centerPane.add(courseTableView, 0, 1);
        centerPane.add(examTableView, 0, 2);
        centerPane.add(midtermTableView, 0, 3);
        centerPane.add(assignmentTableView, 0, 4);

        bPane.setCenter(centerPane);

    }

    private void getTableColumn2() {
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
        courseCodeCol.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        courseProfCol.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        courseDaysCol.setCellValueFactory(new PropertyValueFactory<>("days"));
        courseTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        courseLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        courseTableView.setItems(getCourseData2());
        courseTableView.getColumns().setAll(courseCodeCol, courseProfCol, courseDaysCol, courseTimeCol, courseLocationCol);

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
        examCol.setCellValueFactory(new PropertyValueFactory<>("exam"));
        examDueDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        examTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        examLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        examWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        examMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        examTableView.setItems(getExamData2());
        examTableView.getColumns().setAll(examCol, examDueDateCol, examTimeCol, examLocationCol, examWightCol, examMarkCol);

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
        midCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        midDueDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        midTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        midLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        midWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        midMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        midtermTableView.setItems(getMidtermData2());
        midtermTableView.getColumns().setAll(midCol, midDueDateCol, midTimeCol, midLocationCol, midWightCol, midMarkCol);

        TableColumn<Assignment, String> asmtCol = new TableColumn("Type");
        asmtCol.setMinWidth(200);
        TableColumn<Assignment, String> asmtDueDateCol = new TableColumn("Due Date");
        asmtDueDateCol.setMinWidth(100);
        TableColumn<Assignment, Double> asmtWightCol = new TableColumn("Weight (%)");
        asmtWightCol.setMinWidth(100);
        TableColumn<Assignment, Double> asmtMarkCol = new TableColumn("Mark (%)");
        asmtMarkCol.setMinWidth(100);
        asmtCol.setCellValueFactory(new PropertyValueFactory<>("assignmentName"));
        asmtDueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        asmtWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        asmtMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        assignmentTableView.setItems(getAssignmentData2());
        assignmentTableView.getColumns().setAll(asmtCol, asmtDueDateCol, asmtWightCol, asmtMarkCol);

        GridPane centerPane = new GridPane();

        centerPane.add(courseTableView, 0, 1);
        centerPane.add(examTableView, 0, 2);
        centerPane.add(midtermTableView, 0, 3);
        centerPane.add(assignmentTableView, 0, 4);

        bPane.setCenter(centerPane);

    }

    private void getTableColumn3() {
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
        courseCodeCol.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        courseProfCol.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        courseDaysCol.setCellValueFactory(new PropertyValueFactory<>("days"));
        courseTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        courseLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        courseTableView.setItems(getCourseData3());
        courseTableView.getColumns().setAll(courseCodeCol, courseProfCol, courseDaysCol, courseTimeCol, courseLocationCol);

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
        examCol.setCellValueFactory(new PropertyValueFactory<>("exam"));
        examDueDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        examTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        examLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        examWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        examMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        examTableView.setItems(getExamData3());
        examTableView.getColumns().setAll(examCol, examDueDateCol, examTimeCol, examLocationCol, examWightCol, examMarkCol);

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
        midCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        midDueDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        midTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        midLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        midWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        midMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        midtermTableView.setItems(getMidtermData3());
        midtermTableView.getColumns().setAll(midCol, midDueDateCol, midTimeCol, midLocationCol, midWightCol, midMarkCol);

        TableColumn<Assignment, String> asmtCol = new TableColumn("Type");
        asmtCol.setMinWidth(200);
        TableColumn<Assignment, String> asmtDueDateCol = new TableColumn("Due Date");
        asmtDueDateCol.setMinWidth(100);
        TableColumn<Assignment, Double> asmtWightCol = new TableColumn("Weight (%)");
        asmtWightCol.setMinWidth(100);
        TableColumn<Assignment, Double> asmtMarkCol = new TableColumn("Mark (%)");
        asmtMarkCol.setMinWidth(100);
        asmtCol.setCellValueFactory(new PropertyValueFactory<>("assignmentName"));
        asmtDueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        asmtWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        asmtMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        assignmentTableView.setItems(getAssignmentData3());
        assignmentTableView.getColumns().setAll(asmtCol, asmtDueDateCol, asmtWightCol, asmtMarkCol);

        GridPane centerPane = new GridPane();

        centerPane.add(courseTableView, 0, 1);
        centerPane.add(examTableView, 0, 2);
        centerPane.add(midtermTableView, 0, 3);
        centerPane.add(assignmentTableView, 0, 4);

        bPane.setCenter(centerPane);

    }

    private void getTableColumn4() {
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
        courseCodeCol.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        courseProfCol.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        courseDaysCol.setCellValueFactory(new PropertyValueFactory<>("days"));
        courseTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        courseLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        courseTableView.setItems(getCourseData4());
        courseTableView.getColumns().setAll(courseCodeCol, courseProfCol, courseDaysCol, courseTimeCol, courseLocationCol);

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
        examCol.setCellValueFactory(new PropertyValueFactory<>("exam"));
        examDueDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        examTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        examLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        examWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        examMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        examTableView.setItems(getExamData4());
        examTableView.getColumns().setAll(examCol, examDueDateCol, examTimeCol, examLocationCol, examWightCol, examMarkCol);

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
        midCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        midDueDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        midTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        midLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        midWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        midMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        midtermTableView.setItems(getMidtermData4());
        midtermTableView.getColumns().setAll(midCol, midDueDateCol, midTimeCol, midLocationCol, midWightCol, midMarkCol);

        TableColumn<Assignment, String> asmtCol = new TableColumn("Type");
        asmtCol.setMinWidth(200);
        TableColumn<Assignment, String> asmtDueDateCol = new TableColumn("Due Date");
        asmtDueDateCol.setMinWidth(100);
        TableColumn<Assignment, Double> asmtWightCol = new TableColumn("Weight (%)");
        asmtWightCol.setMinWidth(100);
        TableColumn<Assignment, Double> asmtMarkCol = new TableColumn("Mark (%)");
        asmtMarkCol.setMinWidth(100);
        asmtCol.setCellValueFactory(new PropertyValueFactory<>("assignmentName"));
        asmtDueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        asmtWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        asmtMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        assignmentTableView.setItems(getAssignmentData4());
        assignmentTableView.getColumns().setAll(asmtCol, asmtDueDateCol, asmtWightCol, asmtMarkCol);

        GridPane centerPane = new GridPane();

        centerPane.add(courseTableView, 0, 1);
        centerPane.add(examTableView, 0, 2);
        centerPane.add(midtermTableView, 0, 3);
        centerPane.add(assignmentTableView, 0, 4);

        bPane.setCenter(centerPane);

    }

    public ObservableList<Assignment> getAssignmentData0() {
        ObservableList<Assignment> asmtObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Assignment[] assignmentList = c.getAssignments();
            for (Assignment a : assignmentList) {
                if (a.getCourseCode().equals(courseCodes[0])) {
                    asmtObList.add(new Assignment(a.getCourseCode(), a.getAssignmentName(), a.getDueDate(), a.getWeight(), a.getMark()));
                }
            }
        }
        return asmtObList;
    }

    public ObservableList<Assignment> getAssignmentData1() {
        ObservableList<Assignment> asmtObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Assignment[] assignmentList = c.getAssignments();
            for (Assignment a : assignmentList) {
                if (a.getCourseCode().equals(courseCodes[1])) {
                    asmtObList.add(new Assignment(a.getCourseCode(), a.getAssignmentName(), a.getDueDate(), a.getWeight(), a.getMark()));
                }
            }
        }
        return asmtObList;
    }

    public ObservableList<Assignment> getAssignmentData2() {
        ObservableList<Assignment> asmtObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Assignment[] assignmentList = c.getAssignments();
            for (Assignment a : assignmentList) {
                if (a.getCourseCode().equals(courseCodes[2])) {
                    asmtObList.add(new Assignment(a.getCourseCode(), a.getAssignmentName(), a.getDueDate(), a.getWeight(), a.getMark()));
                }
            }
        }
        return asmtObList;
    }

    public ObservableList<Assignment> getAssignmentData3() {
        ObservableList<Assignment> asmtObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Assignment[] assignmentList = c.getAssignments();
            for (Assignment a : assignmentList) {
                if (a.getCourseCode().equals(courseCodes[3])) {
                    asmtObList.add(new Assignment(a.getCourseCode(), a.getAssignmentName(), a.getDueDate(), a.getWeight(), a.getMark()));
                }
            }
        }
        return asmtObList;
    }

    public ObservableList<Assignment> getAssignmentData4() {
        ObservableList<Assignment> asmtObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Assignment[] assignmentList = c.getAssignments();
            for (Assignment a : assignmentList) {
                if (a.getCourseCode().equals(courseCodes[4])) {
                    asmtObList.add(new Assignment(a.getCourseCode(), a.getAssignmentName(), a.getDueDate(), a.getWeight(), a.getMark()));
                }
            }
        }
        return asmtObList;
    }

    public ObservableList<Midterm> getMidtermData0() {
        ObservableList<Midterm> midObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Midterm[] midtermList = c.getMidterms();
            for (Midterm m : midtermList) {
                if (m.getCourseCode().equals(courseCodes[0]))
                    midObList.add(new Midterm(m.getCourseCode(), m.getMidterm(), m.getDate(), m.getTime(), m.getLocation(), m.getWeight(), m.getMark()));
            }
        }
        return midObList;
    }

    public ObservableList<Midterm> getMidtermData1() {
        ObservableList<Midterm> midObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Midterm[] midtermList = c.getMidterms();
            for (Midterm m : midtermList) {
                if (m.getCourseCode().equals(courseCodes[1]))
                    midObList.add(new Midterm(m.getCourseCode(), m.getMidterm(), m.getDate(), m.getTime(), m.getLocation(), m.getWeight(), m.getMark()));
            }
        }
        return midObList;
    }

    public ObservableList<Midterm> getMidtermData2() {
        ObservableList<Midterm> midObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Midterm[] midtermList = c.getMidterms();
            for (Midterm m : midtermList) {
                if (m.getCourseCode().equals(courseCodes[2]))
                    midObList.add(new Midterm(m.getCourseCode(), m.getMidterm(), m.getDate(), m.getTime(), m.getLocation(), m.getWeight(), m.getMark()));
            }
        }
        return midObList;
    }

    public ObservableList<Midterm> getMidtermData3() {
        ObservableList<Midterm> midObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Midterm[] midtermList = c.getMidterms();
            for (Midterm m : midtermList) {
                if (m.getCourseCode().equals(courseCodes[3]))
                    midObList.add(new Midterm(m.getCourseCode(), m.getMidterm(), m.getDate(), m.getTime(), m.getLocation(), m.getWeight(), m.getMark()));
            }
        }
        return midObList;
    }

    public ObservableList<Midterm> getMidtermData4() {
        ObservableList<Midterm> midObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Midterm[] midtermList = c.getMidterms();
            for (Midterm m : midtermList) {
                if (m.getCourseCode().equals(courseCodes[4]))
                    midObList.add(new Midterm(m.getCourseCode(), m.getMidterm(), m.getDate(), m.getTime(), m.getLocation(), m.getWeight(), m.getMark()));
            }
        }
        return midObList;
    }

    public ObservableList<Exam> getExamData0() {
        ObservableList<Exam> examObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Exam[] examList = c.getExam();
            for (Exam e : examList) {
                if (e.getCourseCode().equals(courseCodes[0])) {
                    examObList.add(new Exam(e.getCourseCode(), e.getExam(), e.getDate(), e.getTime(), e.getLocation(), e.getWeight(), e.getMark()));
                }
            }
        }
        return examObList;
    }

    public ObservableList<Exam> getExamData1() {
        ObservableList<Exam> examObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Exam[] examList = c.getExam();
            for (Exam e : examList) {
                if (e.getCourseCode().equals(courseCodes[1])) {
                    examObList.add(new Exam(e.getCourseCode(), e.getExam(), e.getDate(), e.getTime(), e.getLocation(), e.getWeight(), e.getMark()));
                }
            }
        }
        return examObList;
    }

    public ObservableList<Exam> getExamData2() {
        ObservableList<Exam> examObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Exam[] examList = c.getExam();
            for (Exam e : examList) {
                if (e.getCourseCode().equals(courseCodes[2])) {
                    examObList.add(new Exam(e.getCourseCode(), e.getExam(), e.getDate(), e.getTime(), e.getLocation(), e.getWeight(), e.getMark()));
                }
            }
        }
        return examObList;
    }

    public ObservableList<Exam> getExamData3() {
        ObservableList<Exam> examObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Exam[] examList = c.getExam();
            for (Exam e : examList) {
                if (e.getCourseCode().equals(courseCodes[3])) {
                    examObList.add(new Exam(e.getCourseCode(), e.getExam(), e.getDate(), e.getTime(), e.getLocation(), e.getWeight(), e.getMark()));
                }
            }
        }
        return examObList;
    }

    public ObservableList<Exam> getExamData4() {
        ObservableList<Exam> examObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            Exam[] examList = c.getExam();
            for (Exam e : examList) {
                if (e.getCourseCode().equals(courseCodes[4])) {
                    examObList.add(new Exam(e.getCourseCode(), e.getExam(), e.getDate(), e.getTime(), e.getLocation(), e.getWeight(), e.getMark()));
                }
            }
        }
        return examObList;
    }

    public ObservableList<Course> getCourseData0() {
        ObservableList<Course> courseObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            if (c.getCourseCode().equals(courseCodes[0])) {
                courseObList.add(new Course(c.getCourseName(), c.getTeacher(), c.getCourseCode(), c.getDays(), c.getTime(), c.getLocation()));
            }
        }
        return courseObList;
    }

    public ObservableList<Course> getCourseData1() {
        ObservableList<Course> courseObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            if (c.getCourseCode().equals(courseCodes[1])) {
                courseObList.add(new Course(c.getCourseName(), c.getTeacher(), c.getCourseCode(), c.getDays(), c.getTime(), c.getLocation()));
            }
        }
        return courseObList;
    }

    public ObservableList<Course> getCourseData2() {
        ObservableList<Course> courseObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            if (c.getCourseCode().equals(courseCodes[2])) {
                courseObList.add(new Course(c.getCourseName(), c.getTeacher(), c.getCourseCode(), c.getDays(), c.getTime(), c.getLocation()));
            }
        }
        return courseObList;
    }

    public ObservableList<Course> getCourseData3() {
        ObservableList<Course> courseObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            if (c.getCourseCode().equals(courseCodes[3])) {
                courseObList.add(new Course(c.getCourseName(), c.getTeacher(), c.getCourseCode(), c.getDays(), c.getTime(), c.getLocation()));
            }
        }
        return courseObList;
    }

    public ObservableList<Course> getCourseData4() {
        ObservableList<Course> courseObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            if (c.getCourseCode().equals(courseCodes[4])) {
                courseObList.add(new Course(c.getCourseName(), c.getTeacher(), c.getCourseCode(), c.getDays(), c.getTime(), c.getLocation()));
            }
        }
        return courseObList;
    }

    public static void main(String[] args) {
    }

}
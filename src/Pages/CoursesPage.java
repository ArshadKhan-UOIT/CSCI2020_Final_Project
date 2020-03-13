package Pages;

import DataStructures.Assignment;
import DataStructures.Course;
import Windows.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;


public class CoursesPage extends Page {
   
    public CoursesPage() {
        //Assignmets: course code, assignment, due date, weight , mark
        //what we want when we click a certain course name
        //assignment, due date, weight , mark
//        System.out.println("Pages.CoursesPage created");
        //assignment stuff
        TableView<Assignment> asmtTable = new TableView();
        asmtTable.setMaxWidth(502);
        TableColumn<Assignment, String> asmtCol = new TableColumn("Assignment");
        asmtCol.setMinWidth(200);
        TableColumn<Assignment, String> asmtDueDateCol = new TableColumn("Due Date");
        asmtDueDateCol.setMinWidth(100);
        TableColumn<Assignment, Double> asmtWightCol = new TableColumn("Weight");
        asmtWightCol.setMinWidth(100);
        TableColumn<Assignment, Double> asmtMarkCol = new TableColumn("Mark");
        asmtMarkCol.setMinWidth(100);
        //midterm stuff
        //CSCI2160, Feb, 5pm, UP1501, 15, 90
        //private String courseCode, String date, String time, String location, double weight, double mark;
//        TableView<Midterm> midTable = new TableView();
//        midTable.setMaxWidth(502);
//        TableColumn<Midterm, String> midCol = new TableColumn("Assignment");
//        midCol.setMinWidth(200);
//        TableColumn<Midterm, String> midDueDateCol = new TableColumn("Due Date");
//        midDueDateCol.setMinWidth(100);
//        TableColumn<Midterm, Double> midWightCol = new TableColumn("Weight");
//        midWightCol.setMinWidth(100);
//        TableColumn<Midterm, Double> midMarkCol = new TableColumn("Mark");
//        midMarkCol.setMinWidth(100);


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
//            System.out.println(Window.courses[i]); //test to make sure has the correct info
        }
        //assignment
        asmtCol.setCellValueFactory(new PropertyValueFactory<>("assignmentName"));
        asmtDueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        asmtWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        asmtMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        asmtTable.setItems(getAssignmentData());
        asmtTable.getColumns().addAll(asmtCol, asmtDueDateCol, asmtWightCol, asmtMarkCol);

        mainPane.add(buttons,0,0);
        mainPane.add(asmtTable,0,1);
    }
    public ObservableList<Assignment> getAssignmentData() {
        ObservableList<Assignment> ObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
//            List<Assignment> assignmentList = c.getAssignments();
            Assignment[] assignmentList = c.getAssignments();
            for (Assignment a: assignmentList) {
                ObList.add(new Assignment(a.getCourseCode(),a.getAssignmentName(),a.getDueDate(),a.getWeight(),a.getMark()));
            }
        }
        return ObList;
    }

    public static void main(String[] args) {
    }

}

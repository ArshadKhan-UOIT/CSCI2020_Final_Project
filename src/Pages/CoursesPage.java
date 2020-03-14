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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;


public class CoursesPage extends Page {
    TableView<Course> courseTableView;
    TableView<Exam> examTableView;
    TableView<Midterm> midtermTableView;
    TableView<Assignment> assignmentTableView;
    TableColumn<Course, String> courseCodeCol,courseProfCol,courseDaysCol,courseTimeCol,courseLocationCol;
    TableColumn<Exam, String> examCol,examDueDateCol,examTimeCol,examLocationCol;
    TableColumn<Exam, Double> examWightCol,examMarkCol;
    TableColumn<Midterm, String> midCol,midDueDateCol,midTimeCol,midLocationCol;
    TableColumn<Midterm, Double> midWightCol,midMarkCol;
    TableColumn<Assignment, String> asmtCol, asmtDueDateCol;
    TableColumn<Assignment, Double> asmtWightCol,asmtMarkCol;
    String[] courseCodes = {"MATH2050","CSCI2160","CSCI2040","CSCI2072","CSCI2020"};
    BorderPane bPane = new BorderPane();
    GridPane centerPane = new GridPane();
    public CoursesPage() {
        courseTableView = new TableView();
        examTableView = new TableView();
        midtermTableView = new TableView();
        assignmentTableView = new TableView();
        System.out.println("Pages.CoursesPage created");
        HBox buttons = new HBox();


        Button[] b =new Button[Window.courses.length];


        for (int i=0; i< Window.courses.length;i++) {
            b[i] = new Button(Window.courses[i].getCourseName());
            buttons.getChildren().add(b[i]);
//            System.out.println(Window.courses[i]); //test to make sure has the correct info
        }
        for (int i=0;i<5;i++) {
            final int index = i;
            b[i].setOnMouseClicked(e->{
                if (index==0) {
                    getTableColumn(index);
                } else if (index==1) {
                    getTableColumn(index);
                } else if (index==2) {
                    getTableColumn(index);
                } else if (index==3) {
                    getTableColumn(index);
                } else if (index==4) {
                    getTableColumn(index);
                }
            });
        }
        bPane.setTop(buttons);
        bPane.setCenter(centerPane);
        mainPane.add(bPane,0,0);
    }
    private void getTableColumn(int index) {
        courseCodeCol = new TableColumn("Course Code");
        courseCodeCol.setMinWidth(200);
        courseProfCol = new TableColumn("Prof");
        courseProfCol.setMinWidth(100);
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
        if (index==0) {
            courseTableView.setItems(getCourseData0());
        } else if (index==1) {
            courseTableView.setItems(getCourseData1());
        } else if (index==2) {
            courseTableView.setItems(getCourseData2());
        } else if (index==3) {
            courseTableView.setItems(getCourseData3());
        } else if (index==4) {
            courseTableView.setItems(getCourseData4());
        }
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
        if (index==0) {
            examTableView.setItems(getExamData0());
        } else if (index==1) {
            examTableView.setItems(getExamData1());
        } else if (index==2) {
            examTableView.setItems(getExamData2());
        } else if (index==3) {
            examTableView.setItems(getExamData3());
        } else if (index==4) {
            examTableView.setItems(getExamData4());
        }
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
        if (index==0) {
            midtermTableView.setItems(getMidtermData0());
        } else if (index==1) {
            midtermTableView.setItems(getMidtermData1());
        } else if (index==2) {
            midtermTableView.setItems(getMidtermData2());
        } else if (index==3) {
            midtermTableView.setItems(getMidtermData3());;
        } else if (index==4) {
            midtermTableView.setItems(getMidtermData4());
        }
        midtermTableView.getColumns().setAll(midCol, midDueDateCol, midTimeCol, midLocationCol, midWightCol, midMarkCol);

        asmtCol = new TableColumn("Type");
        asmtCol.setMinWidth(200);
        asmtDueDateCol = new TableColumn("Due Date");
        asmtDueDateCol.setMinWidth(100);
        asmtWightCol = new TableColumn("Weight (%)");
        asmtWightCol.setMinWidth(100);
        asmtMarkCol = new TableColumn("Mark (%)");
        asmtMarkCol.setMinWidth(100);
        asmtCol.setCellValueFactory(new PropertyValueFactory<>("assignmentName"));
        asmtDueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        asmtWightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        asmtMarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        if (index==0) {
            assignmentTableView.setItems(getAssignmentData0());
        } else if (index==1) {
            assignmentTableView.setItems(getAssignmentData1());
        } else if (index==2) {
            assignmentTableView.setItems(getAssignmentData2());
        } else if (index==3) {
            assignmentTableView.setItems(getAssignmentData3());
        } else if (index==4) {
            assignmentTableView.setItems(getAssignmentData4());
        }
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
        for (Course c: Window.courses) {
            Assignment[] assignmentList = c.getAssignments();
            for (Assignment a: assignmentList) {
                if (a.getCourseCode().equals(courseCodes[0])) {
                    asmtObList.add(new Assignment(a.getCourseCode(),a.getAssignmentName(),a.getDueDate(),a.getWeight(),a.getMark()));
                }
            }
        }
        return asmtObList;
    }
    public ObservableList<Assignment> getAssignmentData1() {
        ObservableList<Assignment> asmtObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            Assignment[] assignmentList = c.getAssignments();
            for (Assignment a: assignmentList) {
                if (a.getCourseCode().equals(courseCodes[1])) {
                    asmtObList.add(new Assignment(a.getCourseCode(),a.getAssignmentName(),a.getDueDate(),a.getWeight(),a.getMark()));
                }
            }
        }
        return asmtObList;
    }
    public ObservableList<Assignment> getAssignmentData2() {
        ObservableList<Assignment> asmtObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            Assignment[] assignmentList = c.getAssignments();
            for (Assignment a: assignmentList) {
                if (a.getCourseCode().equals(courseCodes[2])) {
                    asmtObList.add(new Assignment(a.getCourseCode(),a.getAssignmentName(),a.getDueDate(),a.getWeight(),a.getMark()));
                }
            }
        }
        return asmtObList;
    }
    public ObservableList<Assignment> getAssignmentData3() {
        ObservableList<Assignment> asmtObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            Assignment[] assignmentList = c.getAssignments();
            for (Assignment a: assignmentList) {
                if (a.getCourseCode().equals(courseCodes[3])) {
                    asmtObList.add(new Assignment(a.getCourseCode(),a.getAssignmentName(),a.getDueDate(),a.getWeight(),a.getMark()));
                }
            }
        }
        return asmtObList;
    }
    public ObservableList<Assignment> getAssignmentData4() {
        ObservableList<Assignment> asmtObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            Assignment[] assignmentList = c.getAssignments();
            for (Assignment a: assignmentList) {
                if (a.getCourseCode().equals(courseCodes[4])) {
                    asmtObList.add(new Assignment(a.getCourseCode(),a.getAssignmentName(),a.getDueDate(),a.getWeight(),a.getMark()));
                }
            }
        }
        return asmtObList;
    }
    public ObservableList<Midterm> getMidtermData0() {
        ObservableList<Midterm> midObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            Midterm[] midtermList = c.getMidterms();
            for (Midterm m: midtermList) {
                if (m.getCourseCode().equals(courseCodes[0]))
                midObList.add(new Midterm(m.getCourseCode(),m.getMidterm(),m.getDate(),m.getTime(),m.getLocation(),m.getWeight(),m.getMark()));
            }
        }
        return midObList;
    }
    public ObservableList<Midterm> getMidtermData1() {
        ObservableList<Midterm> midObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            Midterm[] midtermList = c.getMidterms();
            for (Midterm m: midtermList) {
                if (m.getCourseCode().equals(courseCodes[1]))
                    midObList.add(new Midterm(m.getCourseCode(),m.getMidterm(),m.getDate(),m.getTime(),m.getLocation(),m.getWeight(),m.getMark()));
            }
        }
        return midObList;
    }
    public ObservableList<Midterm> getMidtermData2() {
        ObservableList<Midterm> midObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            Midterm[] midtermList = c.getMidterms();
            for (Midterm m: midtermList) {
                if (m.getCourseCode().equals(courseCodes[2]))
                    midObList.add(new Midterm(m.getCourseCode(),m.getMidterm(),m.getDate(),m.getTime(),m.getLocation(),m.getWeight(),m.getMark()));
            }
        }
        return midObList;
    }
    public ObservableList<Midterm> getMidtermData3() {
        ObservableList<Midterm> midObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            Midterm[] midtermList = c.getMidterms();
            for (Midterm m: midtermList) {
                if (m.getCourseCode().equals(courseCodes[3]))
                    midObList.add(new Midterm(m.getCourseCode(),m.getMidterm(),m.getDate(),m.getTime(),m.getLocation(),m.getWeight(),m.getMark()));
            }
        }
        return midObList;
    }
    public ObservableList<Midterm> getMidtermData4() {
        ObservableList<Midterm> midObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            Midterm[] midtermList = c.getMidterms();
            for (Midterm m: midtermList) {
                if (m.getCourseCode().equals(courseCodes[4]))
                    midObList.add(new Midterm(m.getCourseCode(),m.getMidterm(),m.getDate(),m.getTime(),m.getLocation(),m.getWeight(),m.getMark()));
            }
        }
        return midObList;
    }
    public ObservableList<Exam> getExamData0() {
        ObservableList<Exam> examObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            Exam[] examList = c.getExam();
            for (Exam e: examList) {
                if (e.getCourseCode().equals(courseCodes[0])) {
                    examObList.add(new Exam(e.getCourseCode(),e.getExam(),e.getDate(),e.getTime(),e.getLocation(),e.getWeight(),e.getMark()));
                }
            }
        }
        return examObList;
    }
    public ObservableList<Exam> getExamData1() {
        ObservableList<Exam> examObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            Exam[] examList = c.getExam();
            for (Exam e: examList) {
                if (e.getCourseCode().equals(courseCodes[1])) {
                    examObList.add(new Exam(e.getCourseCode(),e.getExam(),e.getDate(),e.getTime(),e.getLocation(),e.getWeight(),e.getMark()));
                }
            }
        }
        return examObList;
    }
    public ObservableList<Exam> getExamData2() {
        ObservableList<Exam> examObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            Exam[] examList = c.getExam();
            for (Exam e: examList) {
                if (e.getCourseCode().equals(courseCodes[2])) {
                    examObList.add(new Exam(e.getCourseCode(),e.getExam(),e.getDate(),e.getTime(),e.getLocation(),e.getWeight(),e.getMark()));
                }
            }
        }
        return examObList;
    }
    public ObservableList<Exam> getExamData3() {
        ObservableList<Exam> examObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            Exam[] examList = c.getExam();
            for (Exam e: examList) {
                if (e.getCourseCode().equals(courseCodes[3])) {
                    examObList.add(new Exam(e.getCourseCode(),e.getExam(),e.getDate(),e.getTime(),e.getLocation(),e.getWeight(),e.getMark()));
                }
            }
        }
        return examObList;
    }
    public ObservableList<Exam> getExamData4() {
        ObservableList<Exam> examObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            Exam[] examList = c.getExam();
            for (Exam e: examList) {
                if (e.getCourseCode().equals(courseCodes[4])) {
                    examObList.add(new Exam(e.getCourseCode(),e.getExam(),e.getDate(),e.getTime(),e.getLocation(),e.getWeight(),e.getMark()));
                }
            }
        }
        return examObList;
    }
    public ObservableList<Course> getCourseData0() {
        ObservableList<Course> courseObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            if (c.getCourseCode().equals(courseCodes[0])) {
                courseObList.add(new Course(c.getCourseName(),c.getTeacher(),c.getCourseCode(),c.getDays(),c.getTime(),c.getLocation()));
            }
        }
        return courseObList;
    }
    public ObservableList<Course> getCourseData1() {
        ObservableList<Course> courseObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            if (c.getCourseCode().equals(courseCodes[1])) {
                courseObList.add(new Course(c.getCourseName(),c.getTeacher(),c.getCourseCode(),c.getDays(),c.getTime(),c.getLocation()));
            }
        }
        return courseObList;
    }
    public ObservableList<Course> getCourseData2() {
        ObservableList<Course> courseObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            if (c.getCourseCode().equals(courseCodes[2])) {
                courseObList.add(new Course(c.getCourseName(),c.getTeacher(),c.getCourseCode(),c.getDays(),c.getTime(),c.getLocation()));
            }
        }
        return courseObList;
    }
    public ObservableList<Course> getCourseData3() {
        ObservableList<Course> courseObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            if (c.getCourseCode().equals(courseCodes[3])) {
                courseObList.add(new Course(c.getCourseName(),c.getTeacher(),c.getCourseCode(),c.getDays(),c.getTime(),c.getLocation()));
            }
        }
        return courseObList;
    }
    public ObservableList<Course> getCourseData4() {
        ObservableList<Course> courseObList = FXCollections.observableArrayList();
        for (Course c: Window.courses) {
            if (c.getCourseCode().equals(courseCodes[4])) {
                courseObList.add(new Course(c.getCourseName(),c.getTeacher(),c.getCourseCode(),c.getDays(),c.getTime(),c.getLocation()));
            }
        }
        return courseObList;
    }

    public static void main(String[] args) {
    }

}

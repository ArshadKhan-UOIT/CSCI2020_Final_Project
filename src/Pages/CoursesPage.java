package Pages;

import DataStructures.Assignment;
import DataStructures.Midterm;
import DataStructures.Exam;
import DataStructures.Course;
import Windows.Window;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
    Arc[][] weights = new Arc[5][3];    //3 types of arc's per course (5)
    String[] courseCodes = {"MATH2050","CSCI2160","CSCI2040","CSCI2072","CSCI2020"};
    Color[] pieColours = {Color.DARKORANGE,Color.YELLOW,Color.GREEN};
    String[] courseContent = {"Asmt (%)","Midterm (%)","Exam (%)"};
    Rectangle[] lengend = new Rectangle[3];
    Text[] text = new Text[]{new Text(), new Text(), new Text()};
    BorderPane bPane = new BorderPane();
    GridPane centerPane = new GridPane();
    Pane rightPane = new Pane();
    Pane rightBottomPane = new Pane();
    public CoursesPage() {
        courseTableView = new TableView();
        examTableView = new TableView();
        midtermTableView = new TableView();
        assignmentTableView = new TableView();
        courseTableView.setMaxHeight(200);  //267
        examTableView.setMaxHeight(200);
        midtermTableView.setMaxHeight(200);
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
                getTableColumn(index);
                getPieGraph(index);
                generateLengend();
            });
        }
        for (int i=0;i<text.length;i++) {
            text[i].setText(courseContent[i]);
        }
        bPane.setTop(buttons);
        bPane.setCenter(centerPane);
//        bPane.setRight(rightPane);
        mainPane.add(bPane,0,0);
        mainPane.add(rightPane,1,0);
        mainPane.add(rightBottomPane,1,0);


    }

    private void getPieGraph(int index) {
        //y=635/3 = ~211    x = (1268-702)/2 = 283
        double xPosition, yPosition;
//        double[] asmntTotal = new double[5];
//        double[] midTotal = new double[5];
//        double[] examTotal = new double[5];
        double[][] courseWeightArr = new double[5][3]; //[index][content] -> [For each Course][Content for each Course (assignment,midterm,exam)]
        double[] overAllTotal = new double[5];
        xPosition = (mainPane.getWidth()-702)/3;
        yPosition = mainPane.getHeight()/3.0;
//        double windowHeight, windowWidth;
//        windowHeight = mainPane.getHeight();
//        windowWidth = mainPane.getWidth();
//        double tableViewHeight, tableViewWidth;
        //H 635.0 W 1268.0 initializes right away (main window)
        //TH 609.0 TW 702.0 initializes second time (tableview)
//        tableViewHeight = courseTableView.getHeight() + examTableView.getHeight() + midtermTableView.getHeight() + assignmentTableView.getHeight();
//        tableViewWidth = courseTableView.getWidth();

//        System.out.println("H " + windowHeight + " W " + windowWidth);
//        System.out.println("TH " + tableViewHeight + " TW " + tableViewWidth);
        for (int i=0;i<overAllTotal.length;i++) {
//            asmntTotal[i] = 0;
//            examTotal[i] = 0;
//            midTotal[i] = 0;
            courseWeightArr[i][0] = 0; //initializing
            courseWeightArr[i][1] = 0; //initializing
            courseWeightArr[i][2] = 0; //initializing
            overAllTotal[i] = 0;
        }
        for (Course c : Window.courses) {

            Assignment[] assignmentList = c.getAssignments();

            for (Assignment a : assignmentList) {
                if (a.getCourseCode().equals(courseCodes[index])) {
//                    asmntTotal[index] += a.getWeight();
                    courseWeightArr[index][0] += a.getWeight();
                    overAllTotal[index] += a.getWeight();
                }
            }
            Midterm[] midtermList = c.getMidterms();

            for (Midterm m : midtermList) {
                if (m.getCourseCode().equals(courseCodes[index])) {
//                    midTotal[index] += m.getWeight();
                    courseWeightArr[index][1] += m.getWeight();
                    overAllTotal[index] += m.getWeight();
                }
            }
            Exam[] examList = c.getExam();

            for (Exam e : examList) {
                if (e.getCourseCode().equals(courseCodes[index])) {
//                    examTotal[index] += e.getWeight();
                    courseWeightArr[index][2] += e.getWeight();
                    overAllTotal[index] += e.getWeight();
                }
            }
        }
//        System.out.println( "Index = " + index + "\teTot = " + examTotal[index] + "\tmTot = " + midTotal[index] + "\taTot = " + asmntTotal[index] + "\toTot = " + overAllTotal[index] + "\n");
//        System.out.println();
//        System.out.print("A\t");
//        for (int i=0;i<overAllTotal.length;i++) {
//            System.out.print(courseWeightArr[i][0] + " \t");
//        }
//        System.out.println();
//        System.out.print("M\t");
//        for (int i=0;i<overAllTotal.length;i++) {
//            System.out.print(courseWeightArr[i][1] + " \t");
//        }
//        System.out.println();
//        System.out.print("E\t");
//        for (int i=0;i<overAllTotal.length;i++) {
//            System.out.print(courseWeightArr[i][2] + " \t");
//        }
//        System.out.println();
//        output:
        //A	3.0 	0.0 	0.0 	0.0 	0.0
        //M	40.0 	0.0 	0.0 	0.0 	0.0
        //E	40.0 	0.0 	0.0 	0.0 	0.0
        //
        //A	0.0 	35.0 	0.0 	0.0 	0.0
        //M	0.0 	20.0 	0.0 	0.0 	0.0
        //E	0.0 	20.0 	0.0 	0.0 	0.0
        //
        //A	0.0 	0.0 	30.0 	0.0 	0.0
        //M	0.0 	0.0 	20.0 	0.0 	0.0
        //E	0.0 	0.0 	40.0 	0.0 	0.0
        //
        //A	0.0 	0.0 	0.0 	25.0 	0.0
        //M	0.0 	0.0 	0.0 	15.0 	0.0
        //E	0.0 	0.0 	0.0 	50.0 	0.0
        //
        //A	0.0 	0.0 	0.0 	0.0 	35.0
        //M	0.0 	0.0 	0.0 	0.0 	20.0
        //E	0.0 	0.0 	0.0 	0.0 	25.0
        double begin = 0,angle = 0,angleTotal =0;
        Pane rightPane = new Pane();
        for (int contentIndex = 0; contentIndex < courseWeightArr[index].length-1; contentIndex++) {
            angle = (courseWeightArr[index][contentIndex]/overAllTotal[index]) * 360;
            angleTotal += angle;
            weights[index][contentIndex] = arc(begin,angle,xPosition,yPosition,pieColours[contentIndex]);
            rightPane.getChildren().addAll(weights[index][contentIndex]);
            begin += angle;
        }
        weights[index][courseWeightArr[index].length-1] = arc(begin,360-angleTotal,xPosition,yPosition,pieColours[courseWeightArr[index].length-1]);
        rightPane.getChildren().add(weights[index][courseWeightArr[index].length-1]);
//        bPane.setRight(rightPane);
        mainPane.add(rightPane,1,0);
    }
    private void generateLengend() {
        Pane rightBottomPane = new Pane();
        double x = ((mainPane.getWidth()-702)/3)-120;
        double y = mainPane.getHeight()-200;
        int recWidth = 50;
        int recLength = 25;
        for (int i=0;i<lengend.length;i++) {
            lengend[i] = rect(x,y,recWidth,recLength,pieColours[i]);
            text[i].setX(x);
            text[i].setY(y+45);
            x+=100;
            rightBottomPane.getChildren().addAll(lengend[i], text[i]);
        }
        mainPane.add(rightBottomPane,1,0);
    }

    private Arc arc(double begin, double angle, double xPosition, double yPosition, Color color) {
        Arc arc = new Arc();
        arc.setCenterX(xPosition);
        arc.setCenterY(yPosition);
        arc.setRadiusX(150.0f);
        arc.setRadiusY(150.0f);
        arc.setStartAngle(begin);
        arc.setLength(angle);
        arc.setType(ArcType.ROUND);
        arc.setStroke(Color.BLACK);
        arc.setFill(color);
        return arc;
    }

    private Rectangle rect(double xPos, double yPos, double rectWidth, double rectHeight, Color fill) {
        Rectangle rect = new Rectangle();
        rect.setX(xPos);
        rect.setY(yPos);
        rect.setWidth(rectWidth);
        rect.setHeight(rectHeight);
        rect.setFill(fill);
        rect.setStroke(Color.BLACK);
        return rect;
    }

    private void getTableColumn(int index) {
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
        asmtDueDateCol.setMinWidth(200);
        asmtWightCol = new TableColumn("Weight (%)");
        asmtWightCol.setMinWidth(150);
        asmtMarkCol = new TableColumn("Mark (%)");
        asmtMarkCol.setMinWidth(150);
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
//        System.out.println(courseTableView.getHeight());

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

package Pages;

import DataStructures.Assignment;
import DataStructures.Course;
import DataStructures.Exam;
import DataStructures.Midterm;
import Windows.Window;
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
import javafx.scene.text.Text;


public class CoursesPage extends Page {
    TableView<Course> courseTableView;
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
    Text[] text = new Text[]{new Text(), new Text(), new Text()};
    BorderPane bPane = new BorderPane();
    GridPane centerPane = new GridPane();
    Pane rightPane = new Pane();

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


        Button[] b = new Button[Window.courses.length];


        for (int i = 0; i < Window.courses.length; i++) {
            b[i] = new Button(Window.courses[i].getCourseName());
            buttons.getChildren().add(b[i]);
//            System.out.println(Window.courses[i]); //test to make sure has the correct info
        }
        for (int i = 0; i < 5; i++) {
            final int index = i;
            b[i].setOnMouseClicked(e -> {
                getTableColumn(index);
                getPieGraph(index);
            });
        }
        for (int i = 0; i < text.length; i++) {
            text[i].setText(courseContent[i]);
        }
        bPane.setTop(buttons);
        bPane.setCenter(centerPane);
        bPane.setRight(rightPane);
        mainPane.add(bPane, 0, 0);
//        mainPane.add(rightPane,2,0);

    }

    private void getPieGraph(int index) {
        //y=635/3 = ~211    x = (1268-702)/2 = 283
        double xPosition, yPosition;
//        double[] asmntTotal = new double[5];
//        double[] midTotal = new double[5];
//        double[] examTotal = new double[5];
        double[][] courseWeightArr = new double[5][3]; //[index][content] -> [For each Course][Content for each Course (assignment,midterm,exam)]
        double[] overAllTotal = new double[5];
        xPosition = (mainPane.getWidth() - 702) / 3;
        yPosition = mainPane.getHeight() / 3.0;
        for (int i = 0; i < overAllTotal.length; i++) {
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
        Pane rightPane = new Pane();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int contentIndex = 0; contentIndex < courseWeightArr[index].length; contentIndex++) {
            pieChartData.add(new PieChart.Data(courseContent[contentIndex], courseWeightArr[index][contentIndex]));
        }
        pieChart.setData(pieChartData);
        pieChart.setTitle("Weight's");
        rightPane.getChildren().add(pieChart);
        bPane.setRight(rightPane);
//        mainPane.add(rightPane,2,0);
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

        centerPane.add(courseTableView, 0, 1);
        centerPane.add(examTableView, 0, 2);
        centerPane.add(midtermTableView, 0, 3);
        centerPane.add(assignmentTableView, 0, 4);

        bPane.setCenter(centerPane);
//        System.out.println(courseTableView.getHeight());

    }

    public ObservableList<Assignment> getAssignmentData(int index) {
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
    public ObservableList<Midterm> getMidtermData(int index) {
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
    public ObservableList<Exam> getExamData(int index) {
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
    public ObservableList<Course> getCourseData(int index) {
        ObservableList<Course> courseObList = FXCollections.observableArrayList();
        for (Course c : Window.courses) {
            if (c.getCourseCode().equals(courseCodes[index])) {
                courseObList.add(new Course(c.getCourseName(), c.getTeacher(), c.getCourseCode(), c.getDays(), c.getTime(), c.getLocation()));
            }
        }
        return courseObList;
    }
    public static void main(String[] args) {
    }

}
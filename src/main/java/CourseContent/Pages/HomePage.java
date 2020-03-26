package main.java.CourseContent.Pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.java.CourseContent.CSV.CSVChanger;
import main.java.CourseContent.DataStructures.Assignment;
import main.java.CourseContent.DataStructures.Course;
import main.java.CourseContent.DataStructures.Exam;
import main.java.CourseContent.DataStructures.Midterm;
import main.java.CourseContent.Windows.Window;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/*
 * Ryan Christopher
 */

/*
 * HomePage Class:
 * This class is a child class of Page and is the homepage of the main window that displays your to do list,
 * today's schedule and some grades
 */
public class HomePage extends Page {
    //default constructor sets up mainPane which is a member variable inherited from the Page class
    public HomePage() {
        //pane setup
        mainPane.setGridLinesVisible(true);

        //set row and column constraints so each section is equal
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(50);
        mainPane.getColumnConstraints().addAll(column, column); // each get 50% of width
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(100);
        mainPane.getRowConstraints().addAll(row);

        //create new grid pane for the content on the left side
        GridPane left = new GridPane();
        //set column constraints
        column = new ColumnConstraints();
        column.setPercentWidth(100);
        left.getColumnConstraints().addAll(column);
        //set row constraints
        row = new RowConstraints();
        row.setPercentHeight(50);
        left.getRowConstraints().addAll(row, row); // each get 50% of height
        left.setPadding(new Insets(10, 10, 10, 10));

        //create the to do section and schedule section and add them to the left pane
        left.add(createToDo(), 0, 0);
        left.add(createSchedule(), 0, 1);

        //add left pane to mainPane
        mainPane.add(left, 0, 0);
        //create the grades section and add to the mainPane
        mainPane.add(createMyGrades(), 1, 0);
    }

    //createToDo() initializes the To Do section which displays items from ToDoList.csv
    public Pane createToDo() {
        //create pane and banner text
        VBox toDoPane = new VBox();
        Text toDoBanner = new Text("To Do:");
        toDoBanner.setFont(Font.font("AnjaliOldLipi", FontWeight.BOLD, FontPosture.REGULAR, 30));
        //add banner to pane
        toDoPane.getChildren().add(toDoBanner);
        //retrieve to do list
        List<String[]> activities = CSVChanger.read("ToDoList.csv", 3);

        Text[] list = new Text[activities.size()];
        String[] str;
        //create Text objects and add to the pane
        for (int i = 0; i < activities.size(); i++) {
            str = activities.get(i);
            list[i] = new Text(String.format("- %s (%s %s)", str[0], str[1], str[2]));

            toDoPane.getChildren().add(list[i]);

        }
        //return the pane
        return toDoPane;

    }

    //createSchedule() initializes the schedule section which displays your school schedule for the current day
    public Pane createSchedule() {
        //create Pane and banner text
        VBox schedulePane = new VBox();
        Text scheduleBanner = new Text("Schedule:");
        scheduleBanner.setFont(Font.font("AnjaliOldLipi", FontWeight.BOLD, FontPosture.REGULAR, 30));
        //add banner to pane
        schedulePane.getChildren().add(scheduleBanner);

        //Find the day from the local date and add it to the pane
        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek = DayOfWeek.from(date);
        Text dow = new Text(String.valueOf(dayOfWeek));
        schedulePane.getChildren().add(dow);

        ArrayList<Course> list = new ArrayList<>();
        //Find courses with date equal to current day of the week
        for (Course c : Window.courses) {
            String courseDays = c.getDays();
            String currentDay = String.valueOf(dayOfWeek);

            for (int i = 0; i < courseDays.length(); i++) {
                //if current day is thursday, look for 'R', if it is not thursday, look for the first letter of currentDay
                if (currentDay.equalsIgnoreCase("thursday") && courseDays.charAt(i) == 'R') {
                    //add course to list
                    list.add(c);
                } else if (currentDay.charAt(0) == courseDays.charAt(i) && !(currentDay.equalsIgnoreCase("thursday") && courseDays.charAt(i) == 'T')) {
                    //add course to list
                    list.add(c);

                }
            }
        }
        //loop through list and sort it based on the the time of the course
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (Integer.parseInt(list.get(i).getTime().substring(0, 2)) > Integer.parseInt(list.get(j).getTime().substring(0, 2))) {
                    Course temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        //add courses to the pane
        for (Course c : list) {
            Text entry = new Text("- " + c.getCourseName() + "\n\t-> " + c.getTime() + ", " + c.getLocation());
            schedulePane.getChildren().add(entry);

        }
        //return the pane
        return schedulePane;
    }

    //createMyGrades() initializes the grades section which creates a bar chart based on course grades and weights
    public Pane createMyGrades() {
        //create pane and banner text
        GridPane gradePane = new GridPane();
        gradePane.setGridLinesVisible(false);
        gradePane.setAlignment(Pos.CENTER);
        Text gradeBanner = new Text("My Grades:");
        gradeBanner.setFont(Font.font("AnjaliOldLipi", FontWeight.BOLD, FontPosture.REGULAR, 30));
        //add banner to pane
        gradePane.add(gradeBanner, 0, 0);

        //create 3 series for each type of mark
        XYChart.Series<String, Number> aSeries = new XYChart.Series<>();
        aSeries.setName("Assignments");
        XYChart.Series<String, Number> mSeries = new XYChart.Series<>();
        mSeries.setName("Midterms");
        XYChart.Series<String, Number> eSeries = new XYChart.Series<>();
        eSeries.setName("Exams");

        //loop through courses and calculate the total of each grade category and the total received
        for (Course c : Window.courses) {
            double asmnt = 0;
            double asmntTotal = 0;
            double mid = 0;
            double midTotal = 0;
            double exam = 0;
            double examTotal = 0;

            Assignment[] assignmentList = c.getAssignments();

            for (Assignment a : assignmentList) {
                //if mark does not equal N/A then calculate the mark and add it
                if(a.getMark().equals("N/A")==false){
                    asmnt += Double.parseDouble(a.getMark()) * a.getWeight() / 100;
                }
                asmntTotal += a.getWeight();
            }
            Midterm[] midtermList = c.getMidterms();

            for (Midterm m : midtermList) {
                //if mark does not equal N/A then calculate the mark and add it
                if(m.getMark().equals("N/A")==false){
                    mid += Double.parseDouble(m.getMark()) * m.getWeight() / 100;
                }
                midTotal += m.getWeight();
            }
            Exam[] examList = c.getExam();

            for (Exam e : examList) {
                //if mark does not equal N/A then calculate the mark and add it
                if(e.getMark().equals("N/A")==false){
                    exam += Double.parseDouble(e.getMark()) * e.getWeight() / 100;
                }
                examTotal += e.getWeight();
            }

            //add the totals to the chart
            aSeries.getData().add(new XYChart.Data<>(c.getCourseCode(), asmnt));
            aSeries.getData().add(new XYChart.Data<>(c.getCourseCode() + " Total", asmntTotal));
            mSeries.getData().add(new XYChart.Data<>(c.getCourseCode(), mid));
            mSeries.getData().add(new XYChart.Data<>(c.getCourseCode() + " Total", midTotal));
            eSeries.getData().add(new XYChart.Data<>(c.getCourseCode(), exam));
            eSeries.getData().add(new XYChart.Data<>(c.getCourseCode() + " Total", examTotal));
        }

        //create barchart
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(100); // making 100% the max value of the chart
        StackedBarChart<String, Number> gradeChart = new StackedBarChart<>(xAxis, yAxis);
        gradeChart.setCategoryGap(5);
        xAxis.setLabel("Class");
        yAxis.setLabel("Grade (%)");
        gradeChart.getData().addAll(aSeries, mSeries, eSeries);
        //add bar chart to pane
        gradePane.add(gradeChart, 0, 1);
        //return pane
        return gradePane;
    }
}

package Pages;

import CSV.CSVChanger;
import DataStructures.Assignment;
import DataStructures.Course;
import DataStructures.Exam;
import DataStructures.Midterm;
import Windows.Window;
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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends Page {

    public HomePage() {
        //pane setup
        mainPane.setGridLinesVisible(true);

        //set row and column constraints so each section is equal
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(50);
        mainPane.getColumnConstraints().addAll(column, column); // each get 50% of width
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(100);
        mainPane.getRowConstraints().addAll(row); // each get 50% of height

        //(0,0) = To do
        //(0,1) = Schedule
        //(1,1) = My Grades
        //add everything to the main pane

        GridPane left = new GridPane();

        column = new ColumnConstraints();
        column.setPercentWidth(100);
        left.getColumnConstraints().addAll(column); // each get 50% of width

        row = new RowConstraints();
        row.setPercentHeight(50);
        left.getRowConstraints().addAll(row, row); // each get 50% of height
        left.setPadding(new Insets(10, 10, 10, 10));

        left.add(createToDo(), 0, 0);
        left.add(createSchedule(), 0, 1);
//        left.setGridLinesVisible(true);

//        mainPane.add(createToDo(), 0, 0);
//        mainPane.add(createSchedule(), 0, 1);
//        mainPane.add(createMyCourses(), 1, 0);
        mainPane.add(left, 0, 0);
        mainPane.add(createMyGrades(), 1, 0);
    }

    //method to initialize To Do section
    public Pane createToDo() {
        //To Do Pane
        VBox toDoPane = new VBox();
        Text toDoBanner = new Text("To Do:");
        toDoBanner.setFont(Font.font("AnjaliOldLipi", FontWeight.BOLD, FontPosture.REGULAR, 30));

        toDoPane.getChildren().add(toDoBanner);

        List<String[]> activities = CSVChanger.read("ToDoList.csv", 3);

        Text[] list = new Text[activities.size()];
        String[] str;
        //call method to get to do list
        for (int i = 0; i < activities.size(); i++) {
            str = activities.get(i);
            list[i] = new Text(String.format("- %s (%s %s)", str[0], str[1], str[2]));

            toDoPane.getChildren().add(list[i]);

        }
        return toDoPane;

    }

    //method to initialize Schedule section
    public Pane createSchedule() {
        //Schedule Pane
        VBox schedulePane = new VBox();
        Text scheduleBanner = new Text("Schedule:");
        scheduleBanner.setFont(Font.font("AnjaliOldLipi", FontWeight.BOLD, FontPosture.REGULAR, 30));
        schedulePane.getChildren().add(scheduleBanner);

        // Find the day from the local date
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
                if (currentDay.equalsIgnoreCase("thursday") && courseDays.charAt(i) == 'R') {
                    list.add(c);
                } else if (currentDay.charAt(0) == courseDays.charAt(i) && !(currentDay.equalsIgnoreCase("thursday") && courseDays.charAt(i) == 'T')) {
                    list.add(c);

                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (Integer.parseInt(list.get(i).getTime().substring(0, 2)) > Integer.parseInt(list.get(j).getTime().substring(0, 2))) {
                    Course temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        for (Course c : list) {
            Text entry = new Text("- " + c.getCourseName() + "\n\t-> " + c.getTime() + ", " + c.getLocation());
            schedulePane.getChildren().add(entry);

        }

        return schedulePane;
    }

    //method to initialize My Grades section
    public Pane createMyGrades() {

        //My Grades Pane
        GridPane gradePane = new GridPane();
        gradePane.setGridLinesVisible(false);
        gradePane.setAlignment(Pos.CENTER);

        Text gradeBanner = new Text("My Grades:");
        gradeBanner.setFont(Font.font("AnjaliOldLipi", FontWeight.BOLD, FontPosture.REGULAR, 30));
        //create 3 series
        XYChart.Series<String, Number> aSeries = new XYChart.Series<>();
        aSeries.setName("Assignments");
        XYChart.Series<String, Number> mSeries = new XYChart.Series<>();
        mSeries.setName("Midterms");
        XYChart.Series<String, Number> eSeries = new XYChart.Series<>();
        eSeries.setName("Exams");

        for (Course c : Window.courses) {
            double asmnt = 0;
            double asmntTotal = 0;
            double mid = 0;
            double midTotal = 0;
            double exam = 0;
            double examTotal = 0;

            Assignment[] assignmentList = c.getAssignments();

            for (Assignment a : assignmentList) {
                asmnt += a.getMark() * a.getWeight() / 100;
                asmntTotal += a.getWeight();
            }
            Midterm[] midtermList = c.getMidterms();

            for (Midterm m : midtermList) {
                mid += m.getMark() * m.getWeight() / 100;
                midTotal += m.getWeight();
            }
            Exam[] examList = c.getExam();

            for (Exam e : examList) {
                exam += e.getMark() * e.getWeight() / 100;
                examTotal += e.getWeight();
            }

            //make data and add it to the chart
            aSeries.getData().add(new XYChart.Data<>(c.getCourseCode(), asmnt));
            aSeries.getData().add(new XYChart.Data<>(c.getCourseCode() + " Total", asmntTotal));
            mSeries.getData().add(new XYChart.Data<>(c.getCourseCode(), mid));
            mSeries.getData().add(new XYChart.Data<>(c.getCourseCode() + " Total", midTotal));
            eSeries.getData().add(new XYChart.Data<>(c.getCourseCode(), exam));
            eSeries.getData().add(new XYChart.Data<>(c.getCourseCode() + " Total", examTotal));
        }

        //add banner to pane
        gradePane.add(gradeBanner, 0, 0);

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

        return gradePane;
    }
}

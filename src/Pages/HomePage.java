package Pages;

import DataStructures.Course;
import Windows.Window;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class HomePage extends Page {

    public HomePage () {
        //pane setup
        mainPane.setGridLinesVisible(true);

        //set row and column constraints so each section is equal
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(50);
        mainPane.getColumnConstraints().addAll(column, column); // each get 50% of width
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(50);
        mainPane.getRowConstraints().addAll(row, row); // each get 50% of width

        //(0,0) = To do
        //(0,1) = Schedule
        //(1,0) = My Courses
        //(1,1) = My Grades
        //add everything to the main pane
        mainPane.add(createToDo(),0,0);
        mainPane.add(createSchedule(),0,1);
        mainPane.add(createMyCourses(),1,0);
        mainPane.add(createMyGrades(),1,1);


        //        mainPane.add(new ButtonBar(""),0,1);

    }

    public static void main(String[] args) {

    }

    //method to initialize To Do section
    public Pane createToDo() {
        //To Do Pane
        VBox toDoPane = new VBox();
        Text toDoBanner = new Text("To Do:");
        toDoBanner.setFont(Font.font("AnjaliOldLipi", FontWeight.BOLD, FontPosture.REGULAR, 20));

        toDoPane.getChildren().add(toDoBanner);

        //call method to get to do list
//        Text[] list = getToDo(); // getToDo() will return array of Text objects with text formatted "- blahblah (Date)"
//        for (Text x: list) {
//            toDoPane.getChildren().add();

//        }
        return toDoPane;

    }

    //method to initialize Schedule section
    public Pane createSchedule() {
        //Schedule Pane
        VBox schedulePane = new VBox();
        Text scheduleBanner = new Text("Schedule:");
        scheduleBanner.setFont(Font.font("AnjaliOldLipi", FontWeight.BOLD, FontPosture.REGULAR, 20));
        schedulePane.getChildren().add(scheduleBanner);


        // Find the day from the local date
        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek= DayOfWeek.from(date);
        Text dow = new Text(String.valueOf(dayOfWeek));
        schedulePane.getChildren().add(dow);

//        System.out.println(dayOfWeek);
        ArrayList<Course> list = new ArrayList<>();
        //Find courses with date equal to current day of the week
        for (Course c: Window.courses) {
            String courseDays = c.getDays();
            String currentDay = "Friday";//String.valueOf(dayOfWeek);

            for (int i=0;i< courseDays.length();i++) {
                if (currentDay.equalsIgnoreCase("thursday") && courseDays.charAt(i)=='R')
                {
                    list.add(c);
                }
                else if (currentDay.equalsIgnoreCase("thursday") && courseDays.charAt(i) == 'T') {

                }
                else if (currentDay.charAt(0) == courseDays.charAt(i) ) {
                    list.add(c);

                }
            }

        }

        for (int i=0; i< list.size(); i++) {
            for (int j=i; j< list.size(); j++) {
                if(Integer.parseInt(list.get(i).getTime().substring(0,2)) > Integer.parseInt(list.get(j).getTime().substring(0,2))) {
                    Course temp = list.get(i);

                    list.set(i,list.get(j));
                    list.set(j,temp);
                }
            }

        }
        for (Course c: list) {
            Text entry = new Text("- " + c.getCourseName()+ ", " + c.getTime() + ", " + c.getLocation());
            schedulePane.getChildren().add(entry);

        }


        return schedulePane;
    }


    //method to initialize My Courses section
    public Pane createMyCourses() {
        //My Courses pane
        VBox coursesPane = new VBox();
        Text coursesBanner = new Text("My Courses:");
        coursesBanner.setFont(Font.font("AnjaliOldLipi", FontWeight.BOLD, FontPosture.REGULAR, 20));


        coursesPane.getChildren().add(coursesBanner);

        return coursesPane;
    }


    //method to initialize My Grades section
    public Pane createMyGrades() {

        //My Grades Pane
        GridPane gradePane = new GridPane();
        gradePane.setGridLinesVisible(true);
        gradePane.setAlignment(Pos.CENTER);

        Text gradeBanner = new Text("My Grades:");
        gradeBanner.setFont(Font.font("AnjaliOldLipi", FontWeight.BOLD, FontPosture.REGULAR, 20));

        //add banner to pane
//        try {
//            getGrades();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        gradePane.add(gradeBanner,0,0);

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(100); // making 100% the max value of the chart
        BarChart<String, Number> gradeChart = new BarChart<>(xAxis,yAxis);

        gradeChart.setAnimated(false);
        gradeChart.setCategoryGap(0);
        xAxis.setLabel("Class");
        yAxis.setLabel("Grade (%)");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
//        series1.getData().add(new XYChart.Data<>("blah", 125));
        gradeChart.getData().addAll(series1);
        //add bar chart to pane
        gradePane.add(gradeChart,0,1);

        return gradePane;
    }

    public void getGrades() throws FileNotFoundException {
        //open file, get grades, return grades and courses
        File gradeFile = new File("DataFiles/grades.csv");
        Scanner input;
        if (gradeFile.exists()) {
            input = new Scanner(gradeFile);
            System.out.println("File exists");
        }

    }

}

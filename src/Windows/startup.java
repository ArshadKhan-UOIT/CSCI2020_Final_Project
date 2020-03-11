package Windows;

import CSV.CSVChanger;
import DataStructures.Assignment;
import DataStructures.Course;
import DataStructures.Exam;
import DataStructures.Midterm;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;
/*
 * The Windows.startup class is for loading all the required information for when the window is displayed.
 * This class will open a loading window and create multiple threads to retrieve each category of information
 * Then it will send the information to window where the rest of the program can use it
 */
public class startup extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        //class to handle Windows.startup window before homepage window appears

        VBox titleBox = new VBox();
        Text banner = new Text("Loading...");
        Image bannerImage = new Image("file:DataFiles/CourseContent.png");
        ImageView imageView1 = new ImageView(bannerImage);
        titleBox.getChildren().add(imageView1);
        titleBox.getChildren().add(banner);
        titleBox.setAlignment(Pos.CENTER);
        banner.setFont(Font.font("URWGothicLSemi-Bold", FontWeight.BOLD, FontPosture.REGULAR, 35));
        banner.setFill( new Color( 0.427,0.51,0.792,1.0));

        primaryStage.setTitle("Course Content");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(titleBox,300, 300, Color.BLACK));
        primaryStage.show();

        //text fade in then out
        FadeTransition ft = new FadeTransition(Duration.millis(3000), banner);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();

        //initialize courses from csv
        List<String[]> data = CSVChanger.read("courses.csv",5);
        Course[] courses = new Course[data.size()];
        for (int i=0; i < data.size(); i++) {
            courses[i] = new Course(data.get(i));
        }
        data = CSVChanger.read("assignments.csv",4);
        Assignment[] assignments = new Assignment[data.size()];
        for (int i=0; i < data.size(); i++) {
            assignments[i] = new Assignment(data.get(i));
        }
        data = CSVChanger.read("midterms.csv",5);
        Midterm[] midterms = new Midterm[data.size()];
        for (int i=0; i < data.size(); i++) {
            midterms[i] = new Midterm(data.get(i));
        }
        data = CSVChanger.read("exams.csv",5);
        Exam[] exams = new Exam[data.size()];
        for (int i=0; i < data.size(); i++) {
            exams[i] = new Exam(data.get(i));
        }

        primaryStage.close();
//        System.out.println(courses[1]);

//        data.add(new String[]{"Software Systems", "Arshad", "CSCI2020", "Monday and Tuesday","3:40-5pm"});
//        System.out.println(String.valueOf(data.get(0)[1]));
//        CSVChanger.write("courses.csv",data);

        Window win = new Window(courses,assignments,midterms,exams);
        win.start(new Stage());


    }
}

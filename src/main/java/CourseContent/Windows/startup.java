package main.java.CourseContent.Windows;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.CourseContent.CSV.CSVChanger;
import main.java.CourseContent.DataStructures.Course;

import java.util.List;

/*
 * startup Class
 * The startup class is for getting the username loading all the required information for when the window is displayed.
 */
public class startup extends Application {
    @Override
    public void start(Stage primaryStage) {
        //create the image, text and text field to be displayed
        VBox titleBox = new VBox();
        titleBox.setPadding(new Insets(20));
        titleBox.setSpacing(10);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        //create the logo
        Image image = new Image("file:DataFiles/CourseContent.png");
        ImageView bannerImage = new ImageView(image);

        //create banner text
        Text banner = new Text("Enter\nUsername");
        banner.setTextAlignment(TextAlignment.CENTER);
        banner.setFont(Font.font("URWGothicLSemi-Bold", FontWeight.BOLD, FontPosture.REGULAR, 35));
        banner.setFill(new Color(0.427, 0.51, 0.792, 1.0));

        //create text field
        TextField name = new TextField();

        //create continue button
        Button continueButton = new Button("Continue");
        continueButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #6D82CA; ");

        //add everything to the pane
        titleBox.getChildren().addAll(bannerImage, banner, name, continueButton);

        //finalize window and display it
        primaryStage.setTitle("Course Content");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(titleBox, 300, 300));
        primaryStage.show();
        primaryStage.centerOnScreen();

        //text fade in then out
        FadeTransition ft = new FadeTransition(Duration.millis(3000), banner);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();

        //initialize courses from csv
        List<String[]> data = CSVChanger.read("courses.csv", 6);
        Course[] courses = new Course[data.size()];
        for (int i = 0; i < data.size(); i++) {
            courses[i] = new Course(data.get(i));
        }

        //when button is pressed run window and close startup
        continueButton.setOnMouseClicked(e -> {
            String username = name.getText();
            if (!username.equalsIgnoreCase("")) {
                Runnable win = new Window(courses, username);
                Thread window = new Thread(win);
                window.run();
                primaryStage.close();
            } else {
                name.setPromptText("Username Required");
            }
        });

    }
}

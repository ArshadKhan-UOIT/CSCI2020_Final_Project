package main.java.CourseContent.Pages;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.CourseContent.CSV.CSVChanger;
import main.java.CourseContent.Windows.Window;

import java.util.ArrayList;
import java.util.List;
/*
 * Ryan Christopher
 */

/*
 * AddInfo Class:
 * This class serves as the parent class for the other "Add" classes that are used to add info to the csv files
 */
public abstract class AddInfo extends Application implements Runnable {
    //member variables
    protected List<String[]> str = new ArrayList<>();
    protected String[] strArr;
    protected Text[] prompts;
    protected TextField[] info;
    protected String file, title;
    protected GridPane pane = new GridPane();
    protected Button addButton = new Button("Add");

    //default constructor that initializes some arrays
    public AddInfo() {
        strArr = new String[0];
        prompts = new Text[0];
        info = new TextField[0];
    }

    //constructor that initializes arrays with the number of inputs for the window as well as the file name and window title sent in
    //adding a course requires 6 inputs,
    //adding an assignment requires 5 inputs,
    //adding an exam requires 7 inputs,
    //adding a midterm requires 7 inputs,
    //adding a to do list item requires 3 inputs,
    public AddInfo(int numInputs, String f, String t) {
        strArr = new String[numInputs];
        prompts = new Text[numInputs];
        info = new TextField[numInputs];
        file = f;
        title = t;
    }

    //start() function sets up and displays the window
    @Override
    public void start(Stage primaryStage) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        //add prompts for the inputs on the windows
        for (int i = 0; i < info.length; i++) {
            pane.add(prompts[i], 0, i);
            info[i] = new TextField();
            pane.add(info[i], 1, i);
        }

        //set functionality of addButton when clicked
        addButton.setOnMouseClicked(e -> {
            //when clicked, get text from text fields, put text into String[], send String[] to CSVChanger.write
            boolean isEmpty = false;
            //loop through text fields, retrieving text and adding to String[]
            for (int i = 0; i < info.length; i++) {
                strArr[i] = info[i].getText();
                //if any text fields are empty, prompt user to enter required data
                if (strArr[i].equals("")) {
                    isEmpty = true;
                    info[i].setPromptText("Enter info");
                }
            }
            //if no text fields were empty, write dat to csv
            if (!isEmpty) {
                str.add(strArr);
                //write new data to the specified csv file
                CSVChanger.write(file, str);
                //refresh courses held in Window class
                Window.setCourses();
                //close window
                primaryStage.close();

            }
        });
        //add addButton to the window
        pane.add(addButton, 1, info.length);

        //display window
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    @Override
    public abstract void run();

}

package main.java.CourseContent.Pages;

import javafx.scene.text.Text;
import javafx.stage.Stage;


/*
 * AddExamPage Class:
 * This class is a child class of AddInfo and is used to add exams to the exams.csv file
 */
public class AddExamPage extends AddInfo {
    //constructor calls the super class constructor and sends the number of inputs for the window
    // as well as the file name and window title
    public AddExamPage() {
        super(7, "exams.csv", "Add Exam");
    }

    //run() function starts the new window on a separate thread than the main window and changes the prompts in the
    //parent class to match the specific type of info being added
    public void run() {
        //set prompts
        Text[] texts = {new Text("Course Code"), new Text("Name"), new Text("Date"), new Text("Time"), new Text("Location"), new Text("Weight"), new Text("Mark(N/A if none)")};
        for (int i = 0; i < texts.length; i++) {
            prompts[i] = texts[i];

        }
        start(new Stage());
    }
}

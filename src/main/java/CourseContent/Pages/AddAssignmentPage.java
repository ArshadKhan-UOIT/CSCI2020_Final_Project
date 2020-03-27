package main.java.CourseContent.Pages;

import javafx.scene.text.Text;
import javafx.stage.Stage;


/*
 * AddAssignmentPage Class:
 * This class is a child class of AddInfo and is used to add assignments to the assignments.csv file
 */
public class AddAssignmentPage extends AddInfo {

    //constructor calls the super class constructor and sends the number of inputs for the window
    // as well as the file name and window title
    public AddAssignmentPage() {
        super(5, "assignments.csv", "Add Assignment");
    }

    //run() function starts the new window on a separate thread than the main window and changes the prompts in the
    //parent class to match the specific type of info being added
    public void run() {
        Text[] texts = {new Text("Course Code"), new Text("Assignment Name"), new Text("Due Date"), new Text("Weight"), new Text("Mark(N/A if none)")};
        for (int i = 0; i < texts.length; i++) {
            prompts[i] = texts[i];

        }
        start(new Stage());
    }

}

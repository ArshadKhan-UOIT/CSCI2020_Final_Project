package main.java.CourseContent.Pages;

import javafx.scene.text.Text;
import javafx.stage.Stage;


/*
 * AddCoursePage Class:
 * This class is a child class of AddInfo and is used to add to do list items to the ToDoList.csv file
 */
public class AddToDoPage extends AddInfo {
    //constructor calls the super class constructor and sends the number of inputs for the window
    // as well as the file name and window title
    public AddToDoPage() {
        super(3, "ToDoList.csv", "Add To Do");
    }

    //run() function starts the new window on a separate thread than the main window and changes the prompts in the
    //parent class to match the specific type of info being added
    public void run() {
        //set prompts
        Text[] texts = {new Text("To Do"), new Text("Complete by Month"), new Text("Complete by Day")};
        for (int i = 0; i < texts.length; i++) {
            prompts[i] = texts[i];

        }
        start(new Stage());
    }
}

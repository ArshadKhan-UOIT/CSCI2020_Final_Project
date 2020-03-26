package main.java.CourseContent.Pages;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddMidtermPage extends AddInfo {

    public AddMidtermPage() {
        super(7, "midterms.csv", "Add Midterm");
    }

    public void run() {
        //set prompts
        Text[] texts = {new Text("Course Code"), new Text("Midterm Name"), new Text("Date"), new Text("Time"), new Text("Location"), new Text("Weight"), new Text("Mark(N/A if none)")};
        for (int i = 0; i < texts.length; i++) {
            prompts[i] = texts[i];

        }
        start(new Stage());
    }
}

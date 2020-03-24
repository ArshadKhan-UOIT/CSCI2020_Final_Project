package Pages;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddCoursePage extends AddInfo {

    public AddCoursePage() {
        super(6, "courses.csv", "Add Course");
    }

    public void run() {
        //set prompts
        Text[] texts = {new Text("Course Name"), new Text("Teacher"), new Text("Course Code"), new Text("Days"), new Text("Time"), new Text("Location")};
        for (int i = 0; i < texts.length; i++) {
            prompts[i] = texts[i];

        }
        start(new Stage());
    }
}

package Pages;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddAssignmentPage extends AddInfo {

    public AddAssignmentPage() {
        super(5, "assignments.csv", "Add Assignment");

    }

    public void run() {
        //set prompts
        Text[] texts = {new Text("Course Code"), new Text("Assignment Name"), new Text("Due Date"), new Text("Weight"), new Text("Mark(N/A if none)")};
        for (int i = 0; i < texts.length; i++) {
            prompts[i] = texts[i];

        }
        start(new Stage());
    }

}

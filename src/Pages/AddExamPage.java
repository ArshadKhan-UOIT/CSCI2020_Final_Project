package Pages;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddExamPage extends AddInfo {

    public AddExamPage() {
        super(7, "exams.csv", "Add Exam");
    }

    public void run() {
        //set prompts
        Text[] texts = {new Text("Course Code"), new Text("Name"), new Text("Date"), new Text("Time"), new Text("Location"), new Text("Weight"), new Text("Mark(N/A if none)")};
        for (int i = 0; i < texts.length; i++) {
            prompts[i] = texts[i];

        }
        start(new Stage());
    }
}

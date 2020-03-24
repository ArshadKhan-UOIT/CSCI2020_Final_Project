package Pages;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddToDoPage extends AddInfo {

    public AddToDoPage() {
        super(3, "ToDoList.csv", "Add To Do");
    }

    public void run() {
        //set prompts
        Text[] texts = {new Text("To Do"), new Text("Complete by Month"), new Text("Complete by Day")};
        for (int i = 0; i < texts.length; i++) {
            prompts[i] = texts[i];

        }
        start(new Stage());
    }
}

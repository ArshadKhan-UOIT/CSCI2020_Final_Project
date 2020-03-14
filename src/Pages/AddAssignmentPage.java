package Pages;

import CSV.CSVChanger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AddAssignmentPage extends Application implements Runnable {
    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        List<String[]> str = new ArrayList<>();
        String[] strArr = new String[5];
        Text[] prompts = {new Text("Course Code"), new Text("Assignment Name"), new Text("Due Date"), new Text("Weight"), new Text("Mark")};
        TextField[] info = new TextField[5];

        for (int i = 0; i < info.length; i++) {
            pane.add(prompts[i], 0, i);
            info[i] = new TextField();
            pane.add(info[i], 1, i);
        }

        Button addButton = new Button("Add");
        addButton.setOnMouseClicked(e -> {
            //get text from text fields put it into String[], send to csvChanger.write
            boolean isEmpty = false;
            for (int i = 0; i < info.length; i++) {
                strArr[i] = info[i].getText();
                if (strArr[i].equals("")) {
                    isEmpty = true;
                    info[i].setPromptText("Enter info");
                }
            }
            if (!isEmpty) {
                str.add(strArr);
                CSVChanger.write("assignments.csv", str);

                primaryStage.close();

            }
        });
        pane.add(addButton, 1, 6);

        primaryStage.setTitle("Add Assignment");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    @Override
    public void run() {
        start(new Stage());
    }
}

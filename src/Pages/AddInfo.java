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

public abstract class AddInfo extends Application implements Runnable {
    List<String[]> str = new ArrayList<>();
    String[] strArr;
    Text[] prompts;
    TextField[] info;
    String file, title;
    String[] promptText;

    public AddInfo(int numInputs, String f, String t) {
        strArr = new String[numInputs];
        prompts = new Text[numInputs];
        info = new TextField[numInputs];
        file = f;
        title = t;
    }


    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);


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
                CSVChanger.write(file, str);

                primaryStage.close();

            }
        });
        pane.add(addButton, 1, info.length);

        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    @Override
    public abstract void run();

}

package Pages;

import javafx.application.Application;
import javafx.stage.Stage;

public class AddInfoPage extends Application implements Runnable {

    @Override
    public void run() {
        start(new Stage());
    }

    @Override
    public void start(Stage primaryStage) {


        primaryStage.setTitle("Add Info");
        primaryStage.show();
    }
}

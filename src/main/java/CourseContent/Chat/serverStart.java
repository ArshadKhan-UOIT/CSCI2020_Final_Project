package main.java.CourseContent.Chat;

import javafx.application.Application;
import javafx.stage.Stage;

public class serverStart extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        server s = new server();
        s.startServer();
    }
}

package main.java.CourseContent.Chat;

import javafx.application.Application;
import javafx.stage.Stage;

//Simple class just to run serverStart.start
public class serverStart extends Application {

    //This function will make an instance of server and start the server. This is separate to make sure that there
    //is only 1 instance of it running.
    @Override
    public void start(Stage stage) throws Exception {
        server s = new server();
        s.startServer();
    }
}

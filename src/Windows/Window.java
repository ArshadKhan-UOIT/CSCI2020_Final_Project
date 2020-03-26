package Windows;

import CSV.CSVChanger;
import Chat.client;
import Chat.server;
import DataStructures.Course;
import Pages.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;

/*
 * This class is for the main window that will be displayed on screen at all times.
 * When a button is pressed the window its does not change but the center of the border pane is changed to the page
 * corresponding to the button
 */
public class Window extends Application implements Runnable {
    public TextArea saveText;
    public static Course[] courses;
    public static BorderPane mainPane = new BorderPane();
    private Rectangle2D screen = Screen.getPrimary().getVisualBounds();


    public Window() {
        courses = getCourses();
    }

    public Window(Course[] c) {
        courses = c;
    }

    public static Course[] getCourses() {
        //initialize courses from csv
        List<String[]> data = CSVChanger.read("courses.csv", 6);

        Course[] c = new Course[data.size()];

        for (int i = 0; i < data.size(); i++) {
            c[i] = new Course(data.get(i));
        }
        return c;
    }

    public static void setCourses() {
        Window.courses = getCourses();
    }

    @Override
    public void run() {
        start(new Stage());
    }

    @Override
    public void start(Stage primaryStage) {
        //window setup

        BorderPane mainPane = new BorderPane();
        saveText = new TextArea();
        HBox topPane = new HBox();

        Button b1 = new Button("Home");
        Button b2 = new Button("My Courses");
        Button b3 = new Button("Schedule");
        Button b4 = new Button("My Grades");
        MenuButton b5 = new MenuButton("Edit Info");

        System.out.println(screen.getWidth());

        b1.setPrefSize(screen.getWidth() / 5, screen.getHeight() * 0.05);
        b1.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Home"));

        b2.setPrefSize(screen.getWidth() / 5, screen.getHeight() * 0.05);
        b2.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Courses"));

        b3.setPrefSize(screen.getWidth() / 5, screen.getHeight() * 0.05);
        b3.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Schedule"));

        b4.setPrefSize(screen.getWidth() / 5, screen.getHeight() * 0.05);
        b4.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Grades"));

        b5.setPrefSize(screen.getWidth() / 5, screen.getHeight() * 0.05);

        MenuItem course = new MenuItem("Add Course");
        course.setOnAction(e -> {
            //start new thread to open window
            Runnable addCourse = new AddCoursePage();
            Thread addCourseThread = new Thread(addCourse);
            addCourseThread.run();
        });
        MenuItem assignment = new MenuItem("Add Assignment");
        assignment.setOnAction(e -> {
            //start new thread to open window
            Runnable addAssignment = new AddAssignmentPage();
            Thread addAssignmentThread = new Thread(addAssignment);
            addAssignmentThread.run();
        });
        MenuItem midterm = new MenuItem("Add Midterm");
        midterm.setOnAction(e -> {
            //start new thread to open window
            Runnable addMidterm = new AddMidtermPage();
            Thread addMidtermThread = new Thread(addMidterm);
            addMidtermThread.run();
        });
        MenuItem exam = new MenuItem("Add Exam");
        exam.setOnAction(e -> {
            //start new thread to open window
            Runnable addExam = new AddExamPage();
            Thread addExamThread = new Thread(addExam);
            addExamThread.run();
        });
        MenuItem toDo = new MenuItem("Add To Do");
        toDo.setOnAction(e -> {
            //start new thread to open window
            Runnable addToDo = new AddToDoPage();
            Thread addToDoThread = new Thread(addToDo);
            addToDoThread.run();
        });
        MenuItem change = new MenuItem("Change Grade");
        change.setOnAction(e -> {
            //start new thread to open window
            Runnable addChange = new ChangeGradePage();
            Thread addChangeThread = new Thread(addChange);
            addChangeThread.run();
        });
        MenuItem remove = new MenuItem("Remove Item");
        remove.setOnAction(e -> {
            //start new thread to open window
            Runnable removeItem = new RemoveEntry();
            Thread removeThread = new Thread(removeItem);
            removeThread.run();
        });

        b5.getItems().addAll(course, assignment, midterm, exam, toDo, change, remove);

        //add buttons
        topPane.getChildren().addAll(b1, b2, b3, b4, b5);
        mainPane.setTop(topPane);

        HomePage home = new HomePage();
        mainPane.setCenter(home.getMainPane());

        VBox chatPane = new VBox();
        chatPane.setMaxSize(screen.getWidth() * 0.2, screen.getHeight() * 0.03);
        Button chatButton = new Button("Chat");
        chatButton.setPrefSize(screen.getWidth() * 0.2, screen.getHeight() * 0.03);
        chatButton.setOnMouseClicked(e -> {
            try {
                expandChat(chatButton, chatPane);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        chatPane.getChildren().add(chatButton);

        StackPane pane = new StackPane();
        pane.setAlignment(Pos.BOTTOM_RIGHT);
        pane.getChildren().addAll(mainPane, chatPane);

        //final window setup
        primaryStage.setTitle("Course Content");
        primaryStage.setMinWidth(screen.getWidth() / 2);
        primaryStage.setMinHeight(screen.getHeight() / 2);
        primaryStage.setScene(new Scene(pane, screen.getWidth() * 0.66, screen.getHeight() * 0.66));
        primaryStage.show();

    }

    private void expandChat(Button chatButton, VBox chatPane) throws Exception {
        saveText.setMinSize(screen.getWidth() * 0.2, screen.getHeight() * 0.4);
        //add chat text
        chatPane.getChildren().add(saveText);

        TextField enterText = new TextField();
        enterText.setPrefWidth(screen.getWidth() * 0.17);
        Button send = new Button("Send");
        send.setPrefWidth(screen.getWidth() * 0.03);

        HBox bottomPane = new HBox();
        bottomPane.getChildren().addAll(enterText, send);
        chatPane.getChildren().add(bottomPane);
        chatButton.setOnMouseClicked(e -> closeChat(chatButton, chatPane));

        server s = new server();
        s.start(new Stage());
        client c = new client(saveText,send,enterText);
        c.runChat();

    }

    private void closeChat(Button chatButton, VBox chatPane) {
        saveText = (TextArea) chatPane.getChildren().get(1);
        chatPane.getChildren().removeAll(chatPane.getChildren());
        chatButton.setOnMouseClicked(e -> {
            try {
                expandChat(chatButton, chatPane);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        chatPane.getChildren().add(chatButton);
    }

    public void changePage(Stage stage, BorderPane pane, String page) {
        switch (page) {
            case "Home":
                stage.setTitle("Home");
                HomePage home = new HomePage();
                pane.setCenter(home.getMainPane());

                break;
            case "Courses":
                stage.setTitle("My Courses");
                CoursesPage cPage = new CoursesPage();
                pane.setCenter(cPage.getMainPane());
                break;
            case "Schedule":
                stage.setTitle("Schedule");
                SchedulePage sPage = new SchedulePage();
                pane.setCenter(sPage.getMainPane());

                break;
            case "Grades":
                stage.setTitle("Grades");
                GradesPage gPage = new GradesPage();
                pane.setCenter(gPage.getMainPane());

                break;

        }
    }
}

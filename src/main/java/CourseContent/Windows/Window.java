package main.java.CourseContent.Windows;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;
import main.java.CourseContent.CSV.CSVChanger;
import main.java.CourseContent.Chat.client;
import main.java.CourseContent.DataStructures.Course;
import main.java.CourseContent.Pages.*;

import java.util.List;
/*
 * Window Class:
 * This class is for the main window that will be displayed on screen at all times.
 * When a button is pressed the window does not change but the center of the border pane is changed to the page
 * corresponding to the button
 */
public class Window extends Application implements Runnable {
    public TextArea saveText;
    private String username;
    public static Course[] courses;
    private Rectangle2D screen = Screen.getPrimary().getVisualBounds();

    //default constructor calls getCourses() to populate 'courses' variable
    public Window() {
        courses = getCourses();
    }

    //constructor to populate 'courses' and 'username' variables from sent in data
    public Window(Course[] c, String u) {
        courses = c;
        username = u;
    }

    /*
     * getCourses() function read courses.csv and creates an instance of the Course class that get put into the 'courses'
     * array
     */
    public static Course[] getCourses() {
        //initialize courses from csv
        List<String[]> data = CSVChanger.read("courses.csv", 6);

        Course[] c = new Course[data.size()];
        for (int i = 0; i < data.size(); i++) {
            c[i] = new Course(data.get(i));
        }
        //return the courses from csv
        return c;
    }

    /*
     * setCourses() function updates the 'courses' variable by calling getCourses()
     */
    public static void setCourses() {
        Window.courses = getCourses();
    }

    /*
     * run() function allows this class to run on a new thread
     */
    @Override
    public void run() {
        //call start() function to set up and display the main window
        start(new Stage());
    }

    /*
     * start() function sets up and displays the main window
     */
    @Override
    public void start(Stage primaryStage) {
        //window setup
        BorderPane mainPane = new BorderPane();
        saveText = new TextArea();
        HBox topPane = new HBox();

        //create maine window buttons
        Button homeButton = new Button("Home");
        Button coursesButton = new Button("My Courses");
        Button scheduleButton = new Button("Schedule");
        Button gradesButton = new Button("My Grades");
        MenuButton editButton = new MenuButton("Edit Info");

        //set button sizes
        homeButton.setPrefSize(screen.getWidth() / 5, screen.getHeight() * 0.05);
        coursesButton.setPrefSize(screen.getWidth() / 5, screen.getHeight() * 0.05);
        scheduleButton.setPrefSize(screen.getWidth() / 5, screen.getHeight() * 0.05);
        gradesButton.setPrefSize(screen.getWidth() / 5, screen.getHeight() * 0.05);
        editButton.setPrefSize(screen.getWidth() / 5, screen.getHeight() * 0.05);

        //set listeners for buttons to call changePane() to change corresponding page
        homeButton.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Home"));
        coursesButton.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Courses"));
        scheduleButton.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Schedule"));
        gradesButton.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Grades"));

        //create menu items for 'editButton' and their listeners that will create a new thread and open a new window
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
        //add menu items to 'editButton'
        editButton.getItems().addAll(course, assignment, midterm, exam, toDo, change, remove);

        //add all buttons to top pane
        topPane.getChildren().addAll(homeButton, coursesButton, scheduleButton, gradesButton, editButton);
        //set top of border pane to top pane
        mainPane.setTop(topPane);

        //create an instance of HomePage and set the center pane to it
        HomePage home = new HomePage();
        mainPane.setCenter(home.getMainPane());

        //create chat pane
        VBox chatPane = new VBox();
        chatPane.setMaxSize(screen.getWidth() * 0.2, screen.getHeight() * 0.03);
        //create chat button in bottom right
        Button chatButton = new Button("Chat");
        chatButton.setPrefSize(screen.getWidth() * 0.2, screen.getHeight() * 0.03);
        //set chatButton to call expandChat() when clicked
        chatButton.setOnMouseClicked(e -> {
            try {
                expandChat(chatButton, chatPane);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //add chatButton to chatPane
        chatPane.getChildren().add(chatButton);

        //create a stack pane to put the chat pane on top of the border pane holding the main content of the window
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.BOTTOM_RIGHT);
        pane.getChildren().addAll(mainPane, chatPane);

        //final window setup
        primaryStage.setTitle("Course Content");
        primaryStage.setMinWidth(screen.getWidth() / 2);
        primaryStage.setMinHeight(screen.getHeight() / 2);
        primaryStage.setScene(new Scene(pane, screen.getWidth() * 0.66, screen.getHeight() * 0.66));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    /*
     * expandChat() function opens the chat area at the bottom left of the window when called.
     * It takes in the chatPane and chatButton from the start() function to alter them
     */
    private void expandChat(Button chatButton, VBox chatPane) throws Exception {
        saveText.setMinSize(screen.getWidth() * 0.2, screen.getHeight() * 0.4);
        //add chat text
        chatPane.getChildren().add(saveText);
        //create text field to hold the text being sent to the chat
        TextField enterText = new TextField();
        enterText.setPrefWidth(screen.getWidth() * 0.17);
        //create the send button
        Button send = new Button("Send");
        send.setPrefWidth(screen.getWidth() * 0.03);

        //add send button and enterText to the chat pane
        HBox bottomPane = new HBox();
        bottomPane.getChildren().addAll(enterText, send);
        chatPane.getChildren().add(bottomPane);
        //change functionality of chatButton so when it is clicked it now calls closeChat()
        chatButton.setOnMouseClicked(e -> closeChat(chatButton, chatPane));

        //create a new client to connect to the server
        client c = new client(saveText, send, enterText);
        c.runChat(username);

    }

    /*
     * closeChat() function closes the chat area at the bottom left of the window when called.
     * It takes in the chatPane and chatButton from the expandChat() function to alter them
     */
    private void closeChat(Button chatButton, VBox chatPane) {
        //remove everything from chatPane
        saveText = (TextArea) chatPane.getChildren().get(1);
        chatPane.getChildren().removeAll(chatPane.getChildren());
        //change functionality of chatButton
        chatButton.setOnMouseClicked(e -> {
            try {
                expandChat(chatButton, chatPane);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //add chatButton back to chatPane
        chatPane.getChildren().add(chatButton);
    }

    /*
     * changePane() function changes what is being displayed in the center of the border pane in the main window.
     * it takes in a stage, a border pane and a string corresponding to the page that will be displayed.
     */
    public void changePage(Stage stage, BorderPane pane, String page) {
        //create an instance of the class corresponding to the string sent in and set the center of the border pane
        //to the new page
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

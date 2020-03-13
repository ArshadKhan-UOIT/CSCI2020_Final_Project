<<<<<<< HEAD
package Windows;

import DataStructures.Assignment;
import DataStructures.Course;
import DataStructures.Exam;
import DataStructures.Midterm;
import Pages.CoursesPage;
import Pages.GradesPage;
import Pages.HomePage;
import Pages.SchedulePage;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*
 * This class is for the main window that will be displayed on screen at all times.
 * When a button is pressed the window its does not change but the center of the border pane is changed to the page
 * corresponding to the button
 */
public class Window extends Application {
    public static Course[] courses;
//    public Assignment[] assignments;
//    public Midterm[] midterms;
//    public Exam[] exams;

    public Window(Course[] c) {
        courses = c;
//        assignments = a;
//        midterms = m;
//        exams = e;
    }


    public void makeWindow(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        //window setup
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        BorderPane mainPane = new BorderPane();
        HBox topPane = new HBox();
//        mainPane.setPadding(new Insets(screen.getHeight()*0.1,screen.getWidth()*0.05,screen.getHeight()*0.05,screen.getWidth()*0.05));

        Button b1 = new Button("Home");
        Button b2 = new Button("My Courses");
        Button b3 = new Button("Schedule");
        Button b4 = new Button("To Do");
        Button b5 = new Button("My Grades");
        Button b6 = new Button("Assignments");
        Button b7 = new Button("Midterms");
        Button b8 = new Button("Final Exams");
        Button b9 = new Button("Add Info");

        b1.setPrefSize(screen.getWidth()/9, screen.getHeight()*0.05);
        b1.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Home"));

        b2.setPrefSize(screen.getWidth()/9, screen.getHeight()*0.05);
        b2.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Courses"));

        b3.setPrefSize(screen.getWidth()/9, screen.getHeight()*0.05);
        b3.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Schedule"));

        b4.setPrefSize(screen.getWidth()/9, screen.getHeight()*0.05);
        b4.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "ToDo"));

        b5.setPrefSize(screen.getWidth()/9, screen.getHeight()*0.05);
        b5.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Grades"));

        b6.setPrefSize(screen.getWidth()/9, screen.getHeight()*0.05);
        b6.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Assignments"));

        b7.setPrefSize(screen.getWidth()/9, screen.getHeight()*0.05);
        b7.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Midterms"));

        b8.setPrefSize(screen.getWidth()/9, screen.getHeight()*0.05);
        b8.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "FinalExams"));

        b9.setPrefSize(screen.getWidth()/9, screen.getHeight()*0.05);
        b9.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "AddInfo"));

        //add buttons
        topPane.getChildren().addAll(b1,b2,b3,b4,b5,b6,b7,b8,b9);
        topPane.setAlignment(Pos.CENTER);
        mainPane.setTop(topPane);
        HomePage home = new HomePage();
        mainPane.setCenter(home.getMainPane());

        //final window setup
        primaryStage.setTitle("Course Content");
        primaryStage.setMinWidth(screen.getWidth()/2);
        primaryStage.setMinHeight(screen.getHeight()/2);
        primaryStage.setScene(new Scene(mainPane,screen.getWidth()*0.66, screen.getHeight()*0.66));
        primaryStage.show();

    }
    public void changePage(Stage stage, BorderPane pane, String page) {

        if (page.equals("Home")) {
            stage.setTitle("Home");
            HomePage home = new HomePage();
            pane.setCenter(home.getMainPane());

        }
        else if (page.equals("Courses")) {
            stage.setTitle("My Courses");
            CoursesPage cPage = new CoursesPage();
            pane.setCenter(cPage.getMainPane());
        }
        else if (page.equals("Schedule")) {
            stage.setTitle("Schedule");
            SchedulePage sPage = new SchedulePage();
            pane.setCenter(sPage.getMainPane());

        }
        else if (page.equals("ToDo")) {
            stage.setTitle("To Do");
            //
            pane.setCenter(new TextArea());

        }
        else if (page.equals("Grades")) {
            stage.setTitle("Grades");
            GradesPage gPage = new GradesPage();
            pane.setCenter(gPage.getMainPane());

        }
        else if (page.equals("Assignments")) {
            stage.setTitle("Assignments");
            //
            pane.setCenter(new TextArea());

        }
        else if (page.equals("Midterms")) {
            stage.setTitle("Midterms");
            //
            pane.setCenter(new TextArea());

        }
        else if (page.equals("FinalExams")) {
            stage.setTitle("Final Exams");
            //
            pane.setCenter(new TextArea());

        }
        else if (page.equals("AddInfo")) {
            stage.setTitle("Add Info");
            //
            pane.setCenter(new TextArea());

        }
    }
}
=======
package Windows;

import DataStructures.Course;
import Pages.CoursesPage;
import Pages.GradesPage;
import Pages.HomePage;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*
 * This class is for the main window that will be displayed on screen at all times.
 * When a button is pressed the window its does not change but the center of the border pane is changed to the page
 * corresponding to the button
 */
public class Window extends Application implements Runnable {
    public static Course[] courses;

    public Window(Course[] c) {
        courses = c;
    }

    @Override
    public void run() {
        start(new Stage());
    }

    @Override
    public void start(Stage primaryStage) {
        //window setup
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        BorderPane mainPane = new BorderPane();
        HBox topPane = new HBox();

        Button b1 = new Button("Home");
        Button b2 = new Button("My Courses");
        Button b3 = new Button("Schedule");
//        Button b4 = new Button("To Do");
        Button b5 = new Button("My Grades");
//        Button b6 = new Button("Assignments");
//        Button b7 = new Button("Midterms");
//        Button b8 = new Button("Final Exams");
        Button b9 = new Button("Add Info");

        b1.setPrefSize(screen.getWidth() / 9, screen.getHeight() * 0.05);
        b1.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Home"));

        b2.setPrefSize(screen.getWidth() / 9, screen.getHeight() * 0.05);
        b2.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Courses"));

        b3.setPrefSize(screen.getWidth() / 9, screen.getHeight() * 0.05);
        b3.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Schedule"));

//        b4.setPrefSize(screen.getWidth()/9, screen.getHeight()*0.05);
//        b4.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "ToDo"));

        b5.setPrefSize(screen.getWidth() / 9, screen.getHeight() * 0.05);
        b5.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Grades"));

//        b6.setPrefSize(screen.getWidth()/9, screen.getHeight()*0.05);
//        b6.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Assignments"));
//
//        b7.setPrefSize(screen.getWidth()/9, screen.getHeight()*0.05);
//        b7.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "Midterms"));
//
//        b8.setPrefSize(screen.getWidth()/9, screen.getHeight()*0.05);
//        b8.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "FinalExams"));

        b9.setPrefSize(screen.getWidth() / 9, screen.getHeight() * 0.05);
        b9.setOnMouseClicked(e -> changePage(primaryStage, mainPane, "AddInfo"));

        //add buttons
        topPane.getChildren().addAll(b1, b2, b3, b5, b9);
        topPane.setAlignment(Pos.CENTER);
        mainPane.setTop(topPane);
        HomePage home = new HomePage();
        mainPane.setCenter(home.getMainPane());

        //final window setup
        primaryStage.setTitle("Course Content");
        primaryStage.setMinWidth(screen.getWidth() / 2);
        primaryStage.setMinHeight(screen.getHeight() / 2);
        primaryStage.setScene(new Scene(mainPane, screen.getWidth() * 0.66, screen.getHeight() * 0.66));
        primaryStage.show();

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
                //
                pane.setCenter(new TextArea());

                break;
            case "ToDo":
                stage.setTitle("To Do");
                //
                pane.setCenter(new TextArea());

                break;
            case "Grades":
                stage.setTitle("Grades");
                GradesPage gPage = new GradesPage();
                pane.setCenter(gPage.getMainPane());

                break;
            case "Assignments":
                stage.setTitle("Assignments");
                //
                pane.setCenter(new TextArea());

                break;
            case "Midterms":
                stage.setTitle("Midterms");
                //
                pane.setCenter(new TextArea());

                break;
            case "FinalExams":
                stage.setTitle("Final Exams");
                //
                pane.setCenter(new TextArea());

                break;
            case "AddInfo":
                stage.setTitle("Add Info");
                //
                pane.setCenter(new TextArea());

                break;
        }
    }
}
>>>>>>> origin/Arshad

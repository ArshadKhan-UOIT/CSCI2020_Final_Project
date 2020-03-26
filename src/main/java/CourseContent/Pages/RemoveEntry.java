package main.java.CourseContent.Pages;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.CourseContent.CSV.CSVChanger;
import main.java.CourseContent.Windows.Window;

import java.util.List;
/*
 * Ryan Christopher
 */

/*
 * RemoveEntry Class:
 * This class creates a window where you select an item to remove and the it will remove that item from the proper csv file
 */
public class RemoveEntry extends Application implements Runnable {
    //member variables
    private Text[] prompts;
    private TextField[] info;
    private BorderPane mainPane = new BorderPane();
    private GridPane pane = new GridPane();
    private MenuButton choose = new MenuButton("What to Remove");
    private Button removeButton = new Button("Remove");

    //start() function sets up the window
    @Override
    public void start(Stage primaryStage) {
        mainPane.setPadding(new Insets(20));
        //sets the initial pane as empty
        setPane(new String[0]);

        //create the menu items for the menu button and sets the action listener for each
        MenuItem course = new MenuItem("Remove Course");
        course.setOnAction(e -> {
            //setup window
            setPane(new String[]{"Course Code"});
            //change menu button text
            choose.setText("Remove Course");
            mainPane.setBottom(new Text("Warning: This will remove everything associated \nto this Course"));
            //add listener to 'removeButton'
            removeButton.setOnMouseClicked(a -> {
                //call removeCourse()
                removeCourse(info[0].getText());
                //update courses held in Window class
                Window.setCourses();
            });
        });
        MenuItem assignment = new MenuItem("Remove Assignment");
        assignment.setOnAction(e -> {
            //setup window
            setPane(new String[]{"Course Code", "Name"});
            //change menu button text
            choose.setText("Remove Assignment");
            mainPane.setBottom(new Text("Warning: This will remove everything associated \nto this Assignment"));
            //add listener to 'removeButton'
            removeButton.setOnMouseClicked(a -> {
                //call removeAssignment()
                removeAssignment(info[0].getText(), info[1].getText());
                //update courses held in Window class
                Window.setCourses();
            });
        });
        MenuItem midterm = new MenuItem("Remove Midterm");
        midterm.setOnAction(e -> {
            //setup window
            setPane(new String[]{"Course Code", "Name"});
            //change menu button text
            choose.setText("Remove Midterm");
            mainPane.setBottom(new Text("Warning: This will remove everything associated \nto this Midterm"));
            //add listener to 'removeButton'
            removeButton.setOnMouseClicked(a -> {
                //call removeMidterm()
                removeMidterm(info[0].getText(), info[1].getText());
                //update courses held in Window class
                Window.setCourses();
            });
        });
        MenuItem exam = new MenuItem("Remove Exam");
        exam.setOnAction(e -> {
            //setup window
            setPane(new String[]{"Course Code"});
            //change menu button text
            choose.setText("Remove Exam");
            mainPane.setBottom(new Text("Warning: This will remove everything associated \nto this Exam"));
            //add listener to 'removeButton'
            removeButton.setOnMouseClicked(a -> {
                //call removeExam()
                removeExam(info[0].getText());
                //update courses held in Window class
                Window.setCourses();
            });
        });
        MenuItem toDo = new MenuItem("Remove To Do");
        toDo.setOnAction(e -> {
            //setup window
            setPane(new String[]{"Line Number"});
            //change menu button text
            choose.setText("Remove To Do");
            mainPane.setBottom(new Text("Warning: This will remove a To Do list item"));
            //add listener to 'removeButton'
            removeButton.setOnMouseClicked(a -> {
                //call removeCourse()
                removeToDo(info[0].getText());
            });
        });
        //add all menu items to the menu button
        choose.getItems().addAll(course, assignment, midterm, exam, toDo);

        //finish window set up and display the window
        mainPane.setCenter(pane);
        primaryStage.setTitle("Remove Entries");
        primaryStage.setScene(new Scene(mainPane, 350, 200));
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    //run() function allows the new window to be started in a new thread
    public void run() {
        start(new Stage());
    }

    //setPane() function takes a String array as input and will add the proper prompts and text fields to the pane
    // for the specific item being removed
    private void setPane(String[] s) {
        //create pane
        pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        //add column contraints
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(60);
        pane.getColumnConstraints().addAll(column); // first column gets 60% of width
        //add buttons to the new pane
        pane.add(choose, 0, 0);
        pane.add(removeButton, 1, 0);
        //set prompts based on the array passed to the function
        prompts = new Text[s.length];
        info = new TextField[s.length];
        for (int i = 0; i < s.length; i++) {
            prompts[i] = new Text(s[i]);
            pane.add(prompts[i], 0, i + 1);
            info[i] = new TextField();
            pane.add(info[i], 1, i + 1);
        }
        //change the windows main pane to the new pane
        mainPane.setCenter(pane);
    }

    //removeCourse() function takes in the course code of the course to be removed and removes it from courses.csv
    //as well as the assignments, midterms and exams associated with the course
    private void removeCourse(String code) {
        //call removeExam() to remove exam for this course
        removeExam(code);
        //remove midterms by rewriting the midterms.csv file without the midterms associated with the course
        List<String[]> data = CSVChanger.read("midterms.csv", 7);

        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i)[0].equalsIgnoreCase(code))) {
                data.remove(i);
            }
        }
        //call writeOver() to rewrite the file
        CSVChanger.writeOver("midterms.csv", data);
        //remove assignments by rewriting the assignments.csv file without the assignments associated with the course
        data = CSVChanger.read("assignments.csv", 5);

        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i)[0].equalsIgnoreCase(code))) {
                data.remove(i);
            }
        }
        //call writeOver() to rewrite the file
        CSVChanger.writeOver("assignments.csv", data);
        //remove course by rewriting the courses.csv file without the course wanted to be removed
        data = CSVChanger.read("courses.csv", 6);

        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i)[2].equalsIgnoreCase(code))) {
                data.remove(i);
            }
        }
        //call writeOver() to rewrite the file
        CSVChanger.writeOver("courses.csv", data);
        //update courses after removal
        Window.setCourses();
    }

    //removeAssignment() function takes in the course code and name of midterm to be removed and removes it from assignments.csv
    private void removeAssignment(String code, String title) {
        //remove assignments by rewriting the assignments.csv file without the assignments associated with the course
        List<String[]> data = CSVChanger.read("assignments.csv", 5);

        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i)[0].equalsIgnoreCase(code)) && (data.get(i)[1].equalsIgnoreCase(title))) {
                data.remove(i);
            }
        }
        //call writeOver() to rewrite the file
        CSVChanger.writeOver("assignments.csv", data);
        //update courses after removal
        Window.setCourses();
    }

    //removeMidterm() function takes in the course code and name of midterm to be removed and removes it from midterms.csv
    private void removeMidterm(String code, String title) {
        //remove midterms by rewriting the midterms.csv file without the midterms associated with the course
        List<String[]> data = CSVChanger.read("midterms.csv", 7);

        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i)[0].equalsIgnoreCase(code)) && (data.get(i)[1].equalsIgnoreCase(title))) {
                data.remove(i);
            }
        }
        //call writeOver() to rewrite the file
        CSVChanger.writeOver("midterms.csv", data);
        //update courses after removal
        Window.setCourses();
    }

    //removeExam() function takes in the course code of the exam to be removed and removes it from exams.csv
    private void removeExam(String code) {
        //remove exam
        List<String[]> data = CSVChanger.read("exams.csv", 7);

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i)[0].equalsIgnoreCase(code)) {
                data.remove(i);
            }
        }
        //call writeOver() to rewrite the file
        CSVChanger.writeOver("exams.csv", data);
        //update courses after removal
        Window.setCourses();
    }

    //removeToDo() function takes in the line number of the item to be removed and removes it from ToDoList.csv
    private void removeToDo(String line) {
        //remove to do
        int l = Integer.parseInt(line);

        List<String[]> data = CSVChanger.read("ToDoList.csv", 3);
        data.remove(l - 1);
        //call writeOver() to rewrite the file
        CSVChanger.writeOver("ToDoList.csv", data);
    }
}

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

public class RemoveEntry extends Application implements Runnable {
    private Text[] prompts;
    private TextField[] info;
    private BorderPane mainPane = new BorderPane();
    private GridPane pane = new GridPane();
    private MenuButton choose = new MenuButton("What to Remove");
    private Button removeButton = new Button("Remove");

    @Override
    public void start(Stage primaryStage) {
        mainPane.setPadding(new Insets(20));
        setPane(new String[0]);

        MenuItem course = new MenuItem("Remove Course");
        course.setOnAction(e -> {
            //setup window
            setPane(new String[]{"Course Code"});
            choose.setText("Remove Course");
            mainPane.setBottom(new Text("Warning: This will remove everything associated \nto this Course"));
            removeButton.setOnMouseClicked(a -> {
                //call removeCourse()
                removeCourse(info[0].getText());
                Window.setCourses();
            });
        });
        MenuItem assignment = new MenuItem("Remove Assignment");
        assignment.setOnAction(e -> {
            //setup window
            setPane(new String[]{"Course Code", "Name"});
            choose.setText("Remove Assignment");
            mainPane.setBottom(new Text("Warning: This will remove everything associated \nto this Assignment"));

            removeButton.setOnMouseClicked(a -> {
                //call removeAssignment()
                removeAssignment(info[0].getText(), info[1].getText());
                Window.setCourses();
            });
        });
        MenuItem midterm = new MenuItem("Remove Midterm");
        midterm.setOnAction(e -> {
            //setup window
            setPane(new String[]{"Course Code", "Name"});
            choose.setText("Remove Midterm");
            mainPane.setBottom(new Text("Warning: This will remove everything associated \nto this Midterm"));

            removeButton.setOnMouseClicked(a -> {
                //call removeMidterm()
                removeMidterm(info[0].getText(), info[1].getText());
                Window.setCourses();
            });

        });
        MenuItem exam = new MenuItem("Remove Exam");
        exam.setOnAction(e -> {
            //setup window
            setPane(new String[]{"Course Code"});
            choose.setText("Remove Exam");
            mainPane.setBottom(new Text("Warning: This will remove everything associated \nto this Exam"));

            removeButton.setOnMouseClicked(a -> {
                //call removeExam()
                removeExam(info[0].getText());
                Window.setCourses();
            });

        });
        MenuItem toDo = new MenuItem("Remove To Do");
        toDo.setOnAction(e -> {
            //setup window
            setPane(new String[]{"Line Number"});
            choose.setText("Remove To Do");
            mainPane.setBottom(new Text("Warning: This will remove a To Do list item"));

            removeButton.setOnMouseClicked(a -> {
                //call removeCourse()
                removeToDo(info[0].getText());
            });

        });

        choose.getItems().addAll(course, assignment, midterm, exam, toDo);

        mainPane.setCenter(pane);
        primaryStage.setTitle("Remove Entries");
        primaryStage.setScene(new Scene(mainPane, 350, 200));
        primaryStage.show();
    }

    public void run() {
        start(new Stage());
    }

    private void setPane(String[] s) {
        pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);

        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(60);
        pane.getColumnConstraints().addAll(column); // first column gets 60% of width

        pane.add(choose, 0, 0);
        pane.add(removeButton, 1, 0);
        //set prompts
        prompts = new Text[s.length];
        info = new TextField[s.length];
        for (int i = 0; i < s.length; i++) {
            prompts[i] = new Text(s[i]);
            pane.add(prompts[i], 0, i + 1);
            info[i] = new TextField();
            pane.add(info[i], 1, i + 1);
        }
        mainPane.setCenter(pane);
    }

    private void removeCourse(String code) {
        //remove exam
        removeExam(code);
        //remove midterms
        List<String[]> data = CSVChanger.read("midterms.csv", 7);

        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i)[0].equalsIgnoreCase(code))) {
                data.remove(i);
            }

        }
        CSVChanger.writeOver("midterms.csv", data);


        //remove assignments
        data = CSVChanger.read("assignments.csv", 5);

        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i)[0].equalsIgnoreCase(code))) {
                data.remove(i);
            }

        }
        CSVChanger.writeOver("assignments.csv", data);
        //remove course
        data = CSVChanger.read("courses.csv", 6);

        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i)[2].equalsIgnoreCase(code))) {
                data.remove(i);
            }

        }
        CSVChanger.writeOver("courses.csv", data);
        Window.courses = Window.getCourses();


    }

    private void removeAssignment(String code, String title) {
        //remove assignment
        List<String[]> data = CSVChanger.read("assignments.csv", 5);

        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i)[0].equalsIgnoreCase(code)) && (data.get(i)[1].equalsIgnoreCase(title))) {
                data.remove(i);
            }

        }
        CSVChanger.writeOver("assignments.csv", data);
        Window.courses = Window.getCourses();

    }

    private void removeMidterm(String code, String title) {
        //remove midterm
        List<String[]> data = CSVChanger.read("midterms.csv", 7);

        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i)[0].equalsIgnoreCase(code)) && (data.get(i)[1].equalsIgnoreCase(title))) {
                data.remove(i);
            }

        }
        CSVChanger.writeOver("midterms.csv", data);
        Window.courses = Window.getCourses();

    }

    private void removeExam(String code) {
        //remove exam
        List<String[]> data = CSVChanger.read("exams.csv", 7);

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i)[0].equalsIgnoreCase(code)) {
                data.remove(i);
            }

        }
        CSVChanger.writeOver("exams.csv", data);
        Window.courses = Window.getCourses();
    }

    private void removeToDo(String line) {
        //remove to do
        int l = Integer.parseInt(line);

        List<String[]> data = CSVChanger.read("ToDoList.csv", 3);
        data.remove(l - 1);
        CSVChanger.writeOver("ToDoList.csv", data);

    }
}

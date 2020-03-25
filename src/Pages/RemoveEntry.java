package Pages;

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
            removeButton.setOnMouseClicked(a -> {
                //call removeCourse()
            });
        });
        MenuItem assignment = new MenuItem("Remove Assignment");
        assignment.setOnAction(e -> {
            //setup window
            setPane(new String[]{"Course Code", "Name"});
        });
        MenuItem midterm = new MenuItem("Remove Midterm");
        midterm.setOnAction(e -> {
            //setup window
            setPane(new String[]{"Course Code", "Name"});

        });
        MenuItem exam = new MenuItem("Remove Exam");
        exam.setOnAction(e -> {
            //setup window
            setPane(new String[]{"Course Code"});

        });
        MenuItem toDo = new MenuItem("Remove To Do");
        toDo.setOnAction(e -> {
            //setup window
            setPane(new String[]{"Line Number"});

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
        column.setPercentWidth(50);
        pane.getColumnConstraints().addAll(column, column); // each get 50% of width

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

    private void removeCourse() {

    }

    private void removeAssignment() {

    }

    private void removeMidterm() {

    }

    private void removeExam() {

    }

    private void removeToDo() {

    }
}

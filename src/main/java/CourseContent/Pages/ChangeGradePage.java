package main.java.CourseContent.Pages;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.CourseContent.CSV.CSVChanger;
import main.java.CourseContent.Windows.Window;

import java.util.List;

//This class is used to change the grade of any assignment,midterm and exam, inside their respective CSV, the user
//does this through the UI.
public class ChangeGradePage extends Application implements Runnable {
    //These global variables are used to get types of grades and the CSV amount of columns
    String[] typesOfGrades = {"assignments","midterms","exams"};
    Integer[] sizeOfCSV = {5,7,7};

    //This runs when changeGradePage is created
    @Override
    public void start(Stage primaryStage) {

        //Makes GridPane and sets its settings for the UI the change Grade runs on.
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        //Saves the data from CSVs
        ObservableList<String> options = FXCollections.observableArrayList();

        //Reads in the data from the corresponding CSV, to put in the dropdown.
        for (int x=0; x<typesOfGrades.length;x++){
            List<String[]> gradesList = CSVChanger.read(typesOfGrades[x]+".csv",sizeOfCSV[x]);

            for (int y=0; y<gradesList.size(); y++){
                options.add(gradesList.get(y)[0] + " " + gradesList.get(y)[1] + ": " + gradesList.get(y)[sizeOfCSV[x]-1] + "/100");
            }
        }

        //Makes the dropDown
        ComboBox comboBox = new ComboBox(options);
        pane.add(comboBox,1,1);

        //Adds Text saying New Grade
        pane.add(new Text("New Grade"),1,2);

        //Make a textfield to enter the grade, you want to change to
        TextField textField = new TextField();
        pane.add(textField,2,2);

        //Makes a "Add" button, and makes it so that when pressed it will read in the correct CSV data. The changing the
        //matching data in the comboBox then overwriting the information into the CSV
        Button addButton = new Button("Add");
        addButton.setOnAction(e->{
            int sizeOfCSVForChange=-1;
            String typeForChange=null;
            String[] changeThis = new String[0];
            List<String[]> gradesListToChange = null;
            int yToChange = -1;

            //Loop over CSV types
            for (int x=0; x<typesOfGrades.length;x++){
                List<String[]> gradesList = CSVChanger.read(typesOfGrades[x]+".csv",sizeOfCSV[x]);

                //Loop over CSV
                for (int y=0; y<gradesList.size(); y++){
                    if((gradesList.get(y)[0] + " " + gradesList.get(y)[1] + ": " + gradesList.get(y)[sizeOfCSV[x]-1] + "/100").equals(comboBox.getValue())){

                        //If it does match comboBox it will save the needed information
                        gradesListToChange = CSVChanger.read(typesOfGrades[y] + ".csv", sizeOfCSV[x]);
                        changeThis = gradesList.get(y);
                        sizeOfCSVForChange = sizeOfCSV[x];
                        typeForChange = typesOfGrades[y];
                        yToChange = y;
                    }
                }
            }

            //Changes the grade column of the chosen data
            changeThis[sizeOfCSVForChange-1] = textField.getText();
            gradesListToChange.set(yToChange,changeThis);

            //Write in the new CSV
            CSVChanger.writeOver(typeForChange + ".csv", gradesListToChange);
            Window.setCourses();
            primaryStage.close();
        });
        pane.add(addButton, 1, 3);

        //Shows the Scene Created
        primaryStage.setTitle("Change Grade");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    @Override
    public void run() {
        start(new Stage());
    }
}
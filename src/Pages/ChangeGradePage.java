package Pages;

import CSV.CSVChanger;
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

import java.util.List;

public class ChangeGradePage extends Application implements Runnable {
    String[] typesOfGrades = {"assignments","midterms","exams"};
    Integer[] sizeOfCSV = {5,7,7};
    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        ObservableList<String> options = FXCollections.observableArrayList();

        for (int x=0; x<typesOfGrades.length;x++){
            List<String[]> gradesList = CSVChanger.read(typesOfGrades[x]+".csv",sizeOfCSV[x]);

            for (int y=0; y<gradesList.size(); y++){
                options.add(gradesList.get(y)[0] + " " + gradesList.get(y)[1] + ": " + gradesList.get(y)[sizeOfCSV[x]-1] + "/100");
            }
        }

        ComboBox comboBox = new ComboBox(options);

        pane.add(comboBox,1,1);

        pane.add(new Text("New Grade"),1,2);

        TextField textField = new TextField();
        pane.add(textField,2,2);

        Button addButton = new Button("Add");
        addButton.setOnAction(e->{
            int sizeOfCSVForChange=-1;
            String typeForChange=null;
            String[] changeThis = new String[0];
            List<String[]> gradesListToChange = null;
            int yToChange = -1;

            for (int x=0; x<typesOfGrades.length;x++){
                List<String[]> gradesList = CSVChanger.read(typesOfGrades[x]+".csv",sizeOfCSV[x]);

                for (int y=0; y<gradesList.size(); y++){
                    if((gradesList.get(y)[0] + " " + gradesList.get(y)[1] + ": " + gradesList.get(y)[sizeOfCSV[x]-1] + "/100").equals(comboBox.getValue())){
                        gradesListToChange = CSVChanger.read(typesOfGrades[x]+".csv",sizeOfCSV[x]);
                        changeThis = gradesList.get(y);
                        sizeOfCSVForChange = sizeOfCSV[x];
                        typeForChange = typesOfGrades[y];
                        yToChange = y;
                    }
                }
            }

            changeThis[sizeOfCSVForChange-1] = textField.getText();
            gradesListToChange.set(yToChange,changeThis);

            CSVChanger.writeOver(typeForChange+".csv",gradesListToChange);
            primaryStage.close();
        });



        pane.add(addButton, 1, 3);

        primaryStage.setTitle("Change Grade");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    @Override
    public void run() {
        start(new Stage());
    }
}
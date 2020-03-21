package Pages;

import CSV.CSVChanger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class GradesPage extends Page {
    HBox hbox;
    VBox vbox;
    Canvas canvas;
    GraphicsContext g;
    String[] typesOfGrades = {"assignments","midterms","exams"};
    Integer[] sizeOfCSV = {5,7,7};
    TableView<Row> grades;
    TableColumn typeCol = new TableColumn("Type");
    TableColumn totalCol = new TableColumn("Total");
    TableColumn gainedCol = new TableColumn("Gained");
    TableColumn neitherCol = new TableColumn("Neither");
    TableColumn lostCol = new TableColumn("Lost");
    Label courseMarks;
    Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE,Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    public GradesPage() {

        hbox = new HBox();
        vbox = new VBox();

        List<String[]> courses = CSVChanger.read("courses.csv",6);
        makeButtons(courses);
        mainPane.add(hbox,0,1);

        courseMarks = new Label(StringUtils.capitalize(courses.get(0)[0]) + " Current Grades:");
        courseMarks.setFont(new Font(30));
        mainPane.add(courseMarks,0,2);

        grades = new TableView<>();
        grades.setMaxHeight(125);
        grades.setMaxWidth(341);
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        gainedCol.setCellValueFactory(new PropertyValueFactory<>("gained"));
        neitherCol.setCellValueFactory(new PropertyValueFactory<>("neither"));
        lostCol.setCellValueFactory(new PropertyValueFactory<>("lost"));
        grades.getColumns().addAll(typeCol,gainedCol,lostCol,neitherCol,totalCol);
        canvas = new Canvas(500,500);
        g = canvas.getGraphicsContext2D();
        makeTable(courses.get(0)[2]);
        mainPane.add(canvas,1,2);
        mainPane.add(grades,0,3);
    }

    public void makeButtons(List<String[]> courses){
        for (int x=0; x<courses.size();x++){
            Button button = new Button();

            button.setText(courses.get(x)[0]);
            String cc = courses.get(x)[2];
            button.setOnAction(e->makeTable(cc));

            hbox.getChildren().add(button);
        }
    }

    public void makeTable(String courseName){
        grades.getItems().clear();
        ObservableList<Row> data = FXCollections.observableArrayList();
        double subjectTotal = 0;
        double subjectNeither = 0;
        double subjectGained = 0;
        double subjectLost = 0;

        for (int x=0; x<typesOfGrades.length;x++){
            List<String[]> gradesList = CSVChanger.read(typesOfGrades[x]+".csv",sizeOfCSV[x]);
            String type = StringUtils.capitalize(typesOfGrades[x]);
            double total = 0;
            double neither = 0;
            double gained = 0;
            double lost = 0;


            for (int y=0; y<gradesList.size(); y++){
                if (gradesList.get(y)[0].equalsIgnoreCase(courseName)){
                    if (gradesList.get(y)[sizeOfCSV[x]-1].equalsIgnoreCase("N/A")){
                        neither += Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        total += Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        subjectNeither += Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        subjectTotal += Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                    } else {
                        total += Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        gained += Double.parseDouble((gradesList.get(y)[sizeOfCSV[x]-1]))/100.*Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        lost += (100.-Double.parseDouble((gradesList.get(y)[sizeOfCSV[x]-1])))/100*Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        subjectTotal += Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        subjectGained += Double.parseDouble((gradesList.get(y)[sizeOfCSV[x]-1]))/100.*Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        subjectLost += (100.-Double.parseDouble((gradesList.get(y)[sizeOfCSV[x]-1])))/100*Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                    }
                }
            }

            data.add(new Row(type,total,neither,gained,lost));
        }
        makeGraph(subjectTotal,subjectNeither,subjectGained,subjectLost);
        data.add(new Row("Total",subjectTotal,subjectNeither,subjectGained,subjectLost));
        grades.setItems(data);
    }

    public static class Row{

        private final SimpleStringProperty type;
        private final SimpleStringProperty total;
        private final SimpleStringProperty neither;
        private final SimpleStringProperty gained;
        private final SimpleStringProperty lost;

        private Row(String type, double total, double neither, double gained, double lost){
            this.type = new SimpleStringProperty(type);
            this.total = new SimpleStringProperty(String.valueOf(total));
            this.neither = new SimpleStringProperty(String.valueOf(neither));
            this.gained = new SimpleStringProperty(String.valueOf(gained));
            this.lost = new SimpleStringProperty(String.valueOf(lost));
        }

        public SimpleStringProperty typeProperty() {
            return type;
        }

        public String getTotal() {
            return total.get();
        }

        public String getNeither() {
            return neither.get();
        }

        public String getGained() {
            return gained.get();
        }

        public String getLost() {
            return lost.get();
        }
    }

    public void makeGraph(double total, double neither, double gained, double lost){
        canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        double startAngle=0;
        double endAngle=0;
        double[] gradesArray = {neither,gained,lost};

        System.out.println(gradesArray[1]);

        for(int x=0; x < typesOfGrades.length; x++) {
            g.setFill(pieColours[x]);
            endAngle = ((gradesArray[x]/total)*360);
            g.fillArc(0,0,500,500,startAngle,endAngle, ArcType.ROUND);
            startAngle += endAngle;
        }

        if (Double.isNaN(endAngle)){
            g.setFont(new Font(30));
            g.setTextAlign(TextAlignment.CENTER);
            g.fillText("Nothing to Grade",250,250);
        }
    }
}

package main.java.CourseContent.Pages;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.java.CourseContent.CSV.CSVChanger;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class GradesPage extends Page {
    //string.format("%.2f",numbers)
    PieChart p1 = new PieChart(), p2 = new PieChart();
    HBox hbox;
    VBox vbox;
    Canvas canvas,canvas2;
    GraphicsContext g,g2;
    String[] typesOfGrades = {"assignments","midterms","exams"};
    Integer[] sizeOfCSV = {5,7,7};
    TableView<Row> grades;
    TableColumn typeCol = new TableColumn("Type");
    TableColumn totalCol = new TableColumn("Total");
    TableColumn gainedCol = new TableColumn("Gained");
    TableColumn neitherCol = new TableColumn("Neither");
    TableColumn lostCol = new TableColumn("Lost");
    TableView<Row> totalTable;
    TableColumn totTypeCol = new TableColumn("Type");
    TableColumn totTotalCol = new TableColumn("Total");
    TableColumn totGainedCol = new TableColumn("Gained");
    TableColumn totNeitherCol = new TableColumn("Neither");
    TableColumn totLostCol = new TableColumn("Lost");
    Text courseMarks;
//    Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE,Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    public GradesPage() {

        hbox = new HBox();
        vbox = new VBox();

        List<String[]> courses = CSVChanger.read("courses.csv",6);
        makeButtons(courses);
        mainPane.add(hbox,0,1);
//        for (int i=0;i< Window.courses.length;i++) {
//            courseMarks = new Label(StringUtils.capitalize(courses.get(0)[0]) + " Current Grades:");
        courseMarks = new Text(StringUtils.capitalize(courses.get(0)[0]) + " Current Grades:");
//        }
        courseMarks.setFont(new Font(30));
        mainPane.add(courseMarks,0,0);
        grades = new TableView<>();
        grades.setMaxHeight(125);
        grades.setMaxWidth(349);
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        gainedCol.setCellValueFactory(new PropertyValueFactory<>("gained"));
        neitherCol.setCellValueFactory(new PropertyValueFactory<>("neither"));
        lostCol.setCellValueFactory(new PropertyValueFactory<>("lost"));

        grades.getColumns().addAll(typeCol,gainedCol,lostCol,neitherCol,totalCol);
//        canvas = new Canvas(200,200);
//        g = canvas.getGraphicsContext2D();
        makeTable(courses.get(0)[2]);
//        mainPane.add(canvas,2,2);
        mainPane.add(grades,0,2);

        totalTable = new TableView<>();
        totalTable.setMaxHeight(125);
        totalTable.setMaxWidth(352);
        totTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        totTotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        totGainedCol.setCellValueFactory(new PropertyValueFactory<>("gained"));
        totNeitherCol.setCellValueFactory(new PropertyValueFactory<>("neither"));
        totLostCol.setCellValueFactory(new PropertyValueFactory<>("lost"));
        totalTable.getColumns().addAll(totTypeCol,totGainedCol,totLostCol,totNeitherCol,totTotalCol);
//        canvas2 = new Canvas(200,200);
//        g2 = canvas2.getGraphicsContext2D();
        makeTotalTable();
        mainPane.add(p1,2,2);
        mainPane.add(p2,2,3);
//        mainPane.add(canvas2,2,3);
        mainPane.add(totalTable,0,3);
    }

    public void makeButtons(List<String[]> courses){
        for (int x=0; x<courses.size();x++){
            Button button = new Button();

            button.setText(courses.get(x)[0]);
            String cc = courses.get(x)[2];
            final int index = x;
            button.setOnAction(e->{
                makeTable(cc);
                getLabel(index);
            });
            hbox.getChildren().add(button);
        }

    }

    private void getLabel(int index) {
        List<String[]> courses = CSVChanger.read("courses.csv",6);
        courseMarks.setText(StringUtils.capitalize(courses.get(index)[0]) + " Current Grades:");
        courseMarks.setFont(new Font(30));
//        mainPane.add(courseMarks,0,0);
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

    public void makeTotalTable(){
        ObservableList<Row> total = FXCollections.observableArrayList();
        double studentTotal2 = 0;
        double studentNeither2 = 0;
        double studentGained2 = 0;
        double studentLost2 = 0;

        for (int x=0; x<typesOfGrades.length;x++){
            List<String[]> gradesList = CSVChanger.read(typesOfGrades[x]+".csv",sizeOfCSV[x]);
            String type = StringUtils.capitalize(typesOfGrades[x]);
            double studentTotal = 0;
            double studentNeither = 0;
            double studentGained = 0;
            double studentLost = 0;

            for (int y=0; y<gradesList.size(); y++){
                    if (gradesList.get(y)[sizeOfCSV[x]-1].equalsIgnoreCase("N/A")){
                        studentNeither += Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        studentTotal += Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        studentNeither2 += Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        studentTotal2 += Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                    } else {
                        studentTotal += Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        studentGained += Double.parseDouble((gradesList.get(y)[sizeOfCSV[x]-1]))/100.*Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        studentLost += (100.-Double.parseDouble((gradesList.get(y)[sizeOfCSV[x]-1])))/100*Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        studentTotal2 += Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        studentGained2 += Double.parseDouble((gradesList.get(y)[sizeOfCSV[x]-1]))/100.*Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                        studentLost2 += (100.-Double.parseDouble((gradesList.get(y)[sizeOfCSV[x]-1])))/100*Double.parseDouble(gradesList.get(y)[sizeOfCSV[x]-2]);
                    }
            }
            total.add(new Row(type,studentTotal,studentNeither,studentGained,studentLost));
        }
        makeTotalGraph(studentTotal2,studentNeither2,studentGained2,studentLost2);
        total.add(new Row("Total",studentTotal2,studentNeither2,studentGained2,studentLost2));
        totalTable.setItems(total);
    }

    public void makeTotalGraph(double studentTotal,double  studentNeither, double studentGained,double studentLost){
//        canvas2.getGraphicsContext2D().clearRect(0,0,canvas2.getWidth(),canvas2.getHeight());
        double startAngle=0;
        double endAngle=0;
        double[] gradesArray = {studentNeither,studentGained,studentLost};
        String[] legend = {"Neither","Gained","Lost"};
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < 3; i++) {
            pieChartData.add(new PieChart.Data(legend[i],gradesArray[i]));
        }
        p2.setData(pieChartData);
        p2.setTitle("Overall");
//        System.out.println(gradesArray[1]);
//
//        for(int x=0; x < typesOfGrades.length; x++) {
//            g2.setFill(pieColours[x]);
//            endAngle = ((gradesArray[x]/studentTotal)*360);
//            g2.fillArc(0,0,200,200,startAngle,endAngle, ArcType.ROUND);
//            startAngle += endAngle;
//        }
//
//        if (Double.isNaN(endAngle)){
//            g2.setFont(new Font(30));
//            g2.setTextAlign(TextAlignment.CENTER);
//            g2.fillText("Nothing to Grade",250,250);
//        }
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
            String round1 = String.format("%.2f",gained);
            this.gained = new SimpleStringProperty(String.valueOf(round1));
            String round2 = String.format("%.2f",lost);
            this.lost = new SimpleStringProperty(String.valueOf(round2));
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
//        canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        double startAngle=0;
        double endAngle=0;
        double[] gradesArray = {neither,gained,lost};
        String[] legend = {"Neither","Gained","Lost"};
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < 3; i++) {
            pieChartData.add(new PieChart.Data(legend[i],gradesArray[i]));
        }
        p1.setData(pieChartData);
        p1.setTitle("Subject");
        p1.setClockwise(false);
//        System.out.println(gradesArray[1]);

//        for(int x=0; x < typesOfGrades.length; x++) {
//            g.setFill(pieColours[x]);
//            endAngle = ((gradesArray[x]/total)*360);
//            g.fillArc(0,0,200,200,startAngle,endAngle, ArcType.ROUND);
//            startAngle += endAngle;
//        }
//
//        if (Double.isNaN(endAngle)){
//            g.setFont(new Font(30));
//            g.setTextAlign(TextAlignment.CENTER);
//            g.fillText("Nothing to Grade",250,250);
//        }
    }
}

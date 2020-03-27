package main.java.CourseContent.Pages;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    PieChart p1 = new PieChart(), p2 = new PieChart();  //declaring the pie chart stuff
    HBox hbox;
    VBox vbox;
    String[] typesOfGrades = {"assignments","midterms","exams"};
    Integer[] sizeOfCSV = {5,7,7};
    TableView<Row> grades;      //declearing the table view
    TableColumn typeCol = new TableColumn("Type");     //these are the table columns for for the "grades" table view
    TableColumn totalCol = new TableColumn("Total");
    TableColumn gainedCol = new TableColumn("Gained");
    TableColumn neitherCol = new TableColumn("Neither");
    TableColumn lostCol = new TableColumn("Lost");
    TableView<Row> totalTable;  //declearing the table view
    TableColumn totTypeCol = new TableColumn("Type");   //these are the table columns for for the "totalTable" table view
    TableColumn totTotalCol = new TableColumn("Total");
    TableColumn totGainedCol = new TableColumn("Gained");
    TableColumn totNeitherCol = new TableColumn("Neither");
    TableColumn totLostCol = new TableColumn("Lost");
    Text courseMarks, courseOVMarks;

    public GradesPage() {

        hbox = new HBox();
        vbox = new VBox();

        List<String[]> courses = CSVChanger.read("courses.csv",6);  //reads the courses csv thats in CSV/CSVChanger.java
        makeButtons(courses);   //makes the buttons at the top of the screen
        mainPane.add(hbox,0,0);
        courseMarks = new Text(StringUtils.capitalize(courses.get(0)[0]) + " Current Grades:");
        courseOVMarks = new Text(StringUtils.capitalize("All Courses Overall Grades:"));
        courseMarks.setFont(new Font(30));
        courseOVMarks.setFont(new Font(30));
        mainPane.add(courseMarks,0,1);
        mainPane.add(courseOVMarks,0,3);
        grades = new TableView<>();
        grades.setMaxHeight(125);
        grades.setMaxWidth(349);
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));    //setting everything for the "grades" table
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        gainedCol.setCellValueFactory(new PropertyValueFactory<>("gained"));
        neitherCol.setCellValueFactory(new PropertyValueFactory<>("neither"));
        lostCol.setCellValueFactory(new PropertyValueFactory<>("lost"));

        grades.getColumns().addAll(typeCol,gainedCol,lostCol,neitherCol,totalCol);  //adding in all of the columns
        makeTable(courses.get(0)[2]);   //calls this function which initializes the table columns and pie chart based on the type of course
        mainPane.add(grades,0,2);   //adding it to the pane

        totalTable = new TableView<>();
        totalTable.setMaxHeight(125);
        totalTable.setMaxWidth(352);
        totTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));     //setting everything for the "total table" table
        totTotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        totGainedCol.setCellValueFactory(new PropertyValueFactory<>("gained"));
        totNeitherCol.setCellValueFactory(new PropertyValueFactory<>("neither"));
        totLostCol.setCellValueFactory(new PropertyValueFactory<>("lost"));
        totalTable.getColumns().addAll(totTypeCol,totGainedCol,totLostCol,totNeitherCol,totTotalCol);   //adding in all of the columns
        makeTotalTable();   //calls this function which initializes the table columns and pie chart based on the over total
        mainPane.add(p1,1,2);
        mainPane.add(p2,1,4);
        mainPane.add(totalTable,0,4);
    }

    public void makeButtons(List<String[]> courses){    //makes the buttons
        for (int x=0; x<courses.size();x++){    //initializes all the stuff for a button
            Button button = new Button();
            button.setText(courses.get(x)[0]);
            String cc = courses.get(x)[2];
            final int index = x;
            button.setOnAction(e->{ //when a button is clicked it ...
                makeTable(cc);  //makes the table
                getText(index);    //rests the text
            });
            hbox.getChildren().add(button); //adds them to an hbox so they are beside each other
        }
    }

    private void getText(int index) {  //changes the text at the top based on with button is pressed
        List<String[]> courses = CSVChanger.read("courses.csv",6);
        courseMarks.setText(StringUtils.capitalize(courses.get(index)[0]) + " Current Grades:");
        courseMarks.setFont(new Font(30));
        courseOVMarks.setText(StringUtils.capitalize("All Courses Overall Grades:"));
        courseOVMarks.setFont(new Font(30));
    }

    public void makeTable(String courseName){       //initializes the grades table view, and gets the pie chart
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
        makeGraph(subjectTotal,subjectNeither,subjectGained,subjectLost);   //calls for pie chart function for each course
        data.add(new Row("Total",subjectTotal,subjectNeither,subjectGained,subjectLost));       //this sets all of the data for the grades table view
        grades.setItems(data);  //this sets it
    }

    public void makeTotalTable(){   //initializes the totalTable view and pie chart (calls the total pie chart function)
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
    // this is the pie chart that grafically displays basically the same info like the total table column
    public void makeTotalGraph(double studentTotal,double  studentNeither, double studentGained,double studentLost){ ;
        double[] gradesArray = {studentNeither,studentGained,studentLost};
        String[] legend = {"Neither","Gained","Lost"};
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < 3; i++) {
            pieChartData.add(new PieChart.Data(legend[i],gradesArray[i]));
        }
        p2.setData(pieChartData);
        p2.setTitle("Overall");
    }

    public static class Row {    //this helps the table views get their columns by using the observal list

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

    public void makeGraph(double total, double neither, double gained, double lost){    //makes the pie chart that basically displays the same data as the "grades" table
        double[] gradesArray = {neither,gained,lost};
        String[] legend = {"Neither","Gained","Lost"};
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < 3; i++) {
            pieChartData.add(new PieChart.Data(legend[i],gradesArray[i]));
        }
        p1.setData(pieChartData);
        p1.setTitle("Subject");
        p1.setClockwise(false);
    }
}

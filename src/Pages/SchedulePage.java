package Pages;
import DataStructures.Course;
import Windows.Window;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SchedulePage extends Page {
    public SchedulePage()
    {
        Text[] MondayCourses = new Text[8];
        Text[] TuesdayCourses = new Text[8];
        Text[] WednesdayCourses = new Text[8];
        Text[] ThursdayCourses = new Text[8];
        Text[] FridayCourses = new Text[8];
        Text[] days= new Text[8];
        days[0]=new Text("\n\t\tDays\n\n\tTime");
        days[1]=new Text("Monday");
        days[2]=new Text("Tuesday");
        days[3]=new Text("Wednesday");
        days[4]=new Text("Thursday");
        days[5]=new Text("Friday");
        days[6]=new Text("Saturday");
        days[7]=new Text("Sunday");
        Text[] hours = new Text[8];
        hours[0]=new Text("8:10-9:30");
        hours[1]=new Text("9:30-11:00");
        hours[2]=new Text("11:10-12:30");
        hours[3]=new Text("12:40-14:00");
        hours[4]=new Text("14:10-15:30");
        hours[5]=new Text("15:40-17:00");
        hours[6]=new Text("17:10-18:30");
        hours[7]=new Text("18:40-20:00");
        VBox buttons = new VBox();
        Button[] col =new Button[8];
        Button[] row = new Button[8];

        for (int i=1; i<8;i++) {
            col[i] = new Button(days[i].getText());
            col[i].setPrefSize(125,75);
            buttons.getChildren().add(col[i]);
        }
        for (int i=0; i<8;i++) {
            row[i] = new Button(hours[i].getText());
            row[i].setPrefSize(125,75);
            buttons.getChildren().add(row[i]);
        }
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(20);
        mainPane.getColumnConstraints().addAll(column, column,column,column,column,column,column,column); // each get 50% of width
        RowConstraints rowC = new RowConstraints();
        rowC.setPercentHeight(20);
        mainPane.getRowConstraints().addAll(rowC, rowC, rowC, rowC, rowC, rowC, rowC, rowC); // each get 50% of width
        //mainPane.add(b[0],0,0);
        for (int i=1; i<8; i++)
        {
            mainPane.add(col[i],i,0);
        }
        for (int i=0; i<8; i++)
        {
            mainPane.add(row[i],0,i+1);
        }
        ArrayList<Course> list = new ArrayList<>();
        //Find courses with date equal to current day of the week
        for (Course c : Window.courses) {
            String courseDays = c.getDays();
            //String[] splitter;

            for (int i = 0; i < courseDays.length(); i++) {
                if (courseDays.charAt(i) == 'M') {
                    MondayCourses[i]=new Text(c.getCourseName());
                }
                else if (courseDays.charAt(i) == 'T') {
                    TuesdayCourses[i]=new Text(c.getCourseName());
                }
                else if (courseDays.charAt(i) == 'W') {
                    WednesdayCourses[i]=new Text(c.getCourseName());
                }
                else if (courseDays.charAt(i) == 'R') {
                    ThursdayCourses[i]=new Text(c.getCourseName());
                }
                else if (courseDays.charAt(i) == 'F') {
                    FridayCourses[i]=new Text(c.getCourseName());

                }
            }
        }

    }
    public static void main(String[] args) {

    }
}

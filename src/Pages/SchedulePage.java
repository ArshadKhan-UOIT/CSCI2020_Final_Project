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
        String[][] MondayCourses = new String[8][8];
        Text[][] TuesdayCourses = new Text[8][8];
        Text[][] WednesdayCourses = new Text[8][8];
        Text[][] ThursdayCourses = new Text[8][8];
        Text[][] FridayCourses = new Text[8][8];
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
        int m=0;
        int t=0;
        int w=0;
        int r=0;
        int f=0;
        //Find courses with date equal to current day of the week
        for (Course a : Window.courses) {
            String courseDays = a.getDays();
            String test;
            //System.out.println(a.getTime());
            //String[] splitter;

            for (int i = 0; i < courseDays.length(); i++) {
                if (courseDays.charAt(i) == 'M') {
                    //System.out.println(a.getCourseName());
                    //System.out.println(a.getTime());
                    MondayCourses[m][0]=(a.getCourseName());
                    MondayCourses[m][1]=(a.getTime());
                    //System.out.println("!");
                    test=MondayCourses[m][1];
                    //System.out.println(test);
                    m++;
                }
                else if (courseDays.charAt(i) == 'T') {
                    TuesdayCourses[t][0]=new Text(a.getCourseName());
                    TuesdayCourses[t][1]=new Text(a.getCourseName());
                    t++;
                }
                else if (courseDays.charAt(i) == 'W') {
                    WednesdayCourses[w][0]=new Text(a.getCourseName());
                    WednesdayCourses[w][1]=new Text(a.getCourseName());
                    w++;
                }
                else if (courseDays.charAt(i) == 'R') {
                    ThursdayCourses[r][0]=new Text(a.getCourseName());
                    ThursdayCourses[r][1]=new Text(a.getCourseName());
                    r++;
                }
                else if (courseDays.charAt(i) == 'F') {
                    FridayCourses[f][0]=new Text(a.getCourseName());
                    FridayCourses[f][1]=new Text(a.getCourseName());
                    f++;
                }
                else{
                    System.out.println("Nothing");
                }
            }
        }
        for (int i=0; i < 8; i++)
        {
            System.out.println("Current: " + MondayCourses[i][1] + " " + i);
            for (int j = 0; j <8; j++)
            {
                if (hours[j].getText().equals(MondayCourses[i][1]))
                {
                    System.out.println("Yep!");
                    //mainPane.add(row[0],1,i+1);
                }
                else
                {
                    System.out.println("Nope!");
                    System.out.println(hours[j].getText() + " compared with " + MondayCourses[i][1]);
                    //System.exit(0);
                }
            }
        }

    }
    public static void main(String[] args) {

    }
}

package main.java.CourseContent.Pages;

import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.CourseContent.DataStructures.Course;
import main.java.CourseContent.Windows.Window;
import javax.swing.*;

public class SchedulePage extends Page {
    //Creating buttons.
    VBox buttons = new VBox();
    Button[] col =new Button[8];
    Button[] row = new Button[8];
    Button[] mon = new Button[8];
    Button[] tues = new Button[8];
    Button[] wed = new Button[8];
    Button[] thurs = new Button[8];
    Button[] fri = new Button[8];
    Button[] sat = new Button[8];
    Button[] sun = new Button[8];
    //JFrame to be created when a button is pressed, plus the JLabel that will store the text that will appear on it.
    private JFrame secondFrame = new JFrame("Class Info");
    private JLabel classInfo = new JLabel("");
    public SchedulePage()
    {
        secondFrame.setDefaultCloseOperation(secondFrame.DISPOSE_ON_CLOSE);//When the second window is closed, it only closes the second window.
        secondFrame.setSize(200,200);
        secondFrame.setLocationRelativeTo(null);//Sets the second window to appear in the center.
        secondFrame.setAlwaysOnTop(true);
        classInfo.setHorizontalAlignment(JLabel.CENTER);
        mainPane.setGridLinesVisible(true);
        //Lots of variable declarations. First up are the 2D arrays that hold course names, times, professors and locations.
        //Each row is a different course on that day. Column 0 has the name of the course, and column 1 has the time.
        String[][] MondayCourses = new String[8][8];
        String[][] TuesdayCourses = new String[8][8];
        String[][] WednesdayCourses = new String[8][8];
        String[][] ThursdayCourses = new String[8][8];
        String[][] FridayCourses = new String[8][8];
        String[][] SaturdayCourses = new String[8][8];
        String[][] SundayCourses = new String[8][8];
        //Once again, each row=different course. Col 0 has the professor name, col 1 has the location.
        String[][] MondayProfLoc = new String[8][8];
        String[][] TuesdayProfLoc = new String[8][8];
        String[][] WednesdayProfLoc = new String[8][8];
        String[][] ThursdayProfLoc = new String[8][8];
        String[][] FridayProfLoc = new String[8][8];
        String[][] SaturdayProfLoc = new String[8][8];
        String[][] SundayProfLoc = new String[8][8];
        Text[] days= new Text[8]; //Text for the day labels on the top row.
        days[0]=new Text("\n\t\tDays\n\n\tTime");
        days[1]=new Text("Monday");
        days[2]=new Text("Tuesday");
        days[3]=new Text("Wednesday");
        days[4]=new Text("Thursday");
        days[5]=new Text("Friday");
        days[6]=new Text("Saturday");
        days[7]=new Text("Sunday");
        /*Text for the time labels for the first column. It should be noted that all times within the CSV must adhere
        to a certain format. They must be in military time, and they must either be x:10-y:30, or x:40-y:00. If a longer block
        of time must be added, then the course must be split into two or more, with the time values being split between them.
        For example, the class period of 12:40-15:30 must be given two course spots in the CSV, with one spot being 12:40-14:00,
        and another for 14:10-15:30. While inefficient, it is less inefficient than creating if/else statements for every possible
        time combination.*/
        Text[] hours = new Text[8]; 
        hours[0]=new Text("08:10-09:30");
        hours[1]=new Text("09:40-11:00");
        hours[2]=new Text("11:10-12:30");
        hours[3]=new Text("12:40-14:00");
        hours[4]=new Text("14:10-15:30");
        hours[5]=new Text("15:40-17:00");
        hours[6]=new Text("17:10-18:30");
        hours[7]=new Text("18:40-20:00");
        //Creating leftmost column and top row.
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
        //Setting constraints and dimensions to standardize button sizes.
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(20);
        mainPane.getColumnConstraints().addAll(column, column,column,column,column,column,column,column); // each get 50% of width
        RowConstraints rowC = new RowConstraints();
        rowC.setPercentHeight(20);
        mainPane.getRowConstraints().addAll(rowC, rowC, rowC, rowC, rowC, rowC, rowC, rowC); // each get 50% of width
        for (int i=1; i<8; i++)
        {
            mainPane.add(col[i],i,0);
        }
        for (int i=0; i<8; i++)
        {
            mainPane.add(row[i],0,i+1);
        }
        /*These variables are special. The following for loop iterates through the Courses CSV, and picks out courses to
        put in the appropriate day-based arrays. In order to make sure that each array is only as big as it needs to be,
        and does not skip over any indexes, each day needed its own counter, which is what the following int variables are.*/
        int m=0;
        int t=0;
        int w=0;
        int r=0;
        int f=0;
        int sa=0;
        int su=0;
        for (Course a : Window.courses) {
            String courseDays = a.getDays();
            for (int i = 0; i < courseDays.length(); i++) {
                if (courseDays.charAt(i) == 'M') {
                    MondayCourses[m][0]=(a.getCourseName());
                    MondayCourses[m][1]=(a.getTime());
                    MondayProfLoc[m][0]=(a.getTeacher());
                    MondayProfLoc[m][1]=(a.getLocation());
                    m++;
                }
                else if (courseDays.charAt(i) == 'T') {
                    TuesdayCourses[t][0]=(a.getCourseName());
                    TuesdayCourses[t][1]=(a.getTime());
                    TuesdayProfLoc[t][0]=(a.getTeacher());
                    TuesdayProfLoc[t][1]=(a.getLocation());
                    t++;
                }
                else if (courseDays.charAt(i) == 'W') {
                    WednesdayCourses[w][0]=(a.getCourseName());
                    WednesdayCourses[w][1]=(a.getTime());
                    WednesdayProfLoc[w][0]=(a.getTeacher());
                    WednesdayProfLoc[w][1]=(a.getLocation());
                    w++;
                }
                else if (courseDays.charAt(i) == 'R') {
                    ThursdayCourses[r][0]=(a.getCourseName());
                    ThursdayCourses[r][1]=(a.getTime());
                    ThursdayProfLoc[r][0]=(a.getTeacher());
                    ThursdayProfLoc[r][1]=(a.getLocation());
                    r++;
                }
                else if (courseDays.charAt(i) == 'F') {
                    FridayCourses[f][0]=(a.getCourseName());
                    FridayCourses[f][1]=(a.getTime());
                    FridayProfLoc[f][0]=(a.getTeacher());
                    FridayProfLoc[f][1]=(a.getLocation());
                    f++;
                }
                else if (courseDays.charAt(i) == 'A') {
                    SaturdayCourses[sa][0]=(a.getCourseName());
                    SaturdayCourses[sa][1]=(a.getTime());
                    SaturdayProfLoc[sa][0]=(a.getTeacher());
                    SaturdayProfLoc[sa][1]=(a.getLocation());
                    sa++;
                }
                else if (courseDays.charAt(i) == 'U') {
                    SundayCourses[su][0]=(a.getCourseName());
                    SundayCourses[su][1]=(a.getTime());
                    SundayProfLoc[su][0]=(a.getTeacher());
                    SundayProfLoc[su][1]=(a.getLocation());
                    su++;
                }
                else{
                }
            }
        }
        //The following for loops add items to the schedulePage day by day.
        for (int i=0; i < 8; i++)
        { //The outer loop determines the row within the xDayCourses array to be looked at.
            for (int j = 0; j <8; j++)
            {//The inner for loop iterates through each hour in the day, checking to see if the course occurs at its time.
                if (hours[j].getText().equals(MondayCourses[i][1]))
                {//If it does, a button is added at the appropriate spot.
                    mon[i] = new Button(MondayCourses[i][0]);
                    mon[i].setPrefSize(125,75);
                    buttons.getChildren().add(mon[i]);
                    mainPane.add(mon[i],1,j+1);
                }
                else
                {

                }
            }
        }
        for (int i=0; i < 8; i++)
        {
            for (int j = 0; j <8; j++)
            {
                if (hours[j].getText().equals(TuesdayCourses[i][1]))
                {
                    tues[i] = new Button(TuesdayCourses[i][0]);
                    tues[i].setPrefSize(125,75);
                    buttons.getChildren().add(tues[i]);
                    mainPane.add(tues[i],2,j+1);
                }
                else
                {
                }
            }
        }
        for (int i=0; i < 8; i++)
        {
            for (int j = 0; j <8; j++)
            {
                if (hours[j].getText().equals(WednesdayCourses[i][1]))
                {

                    wed[i] = new Button(WednesdayCourses[i][0]);
                    wed[i].setPrefSize(125,75);
                    buttons.getChildren().add(wed[i]);
                    mainPane.add(wed[i],3,j+1);
                }
                else
                {
                }
            }
        }
        for (int i=0; i < 8; i++)
        {
            for (int j = 0; j <8; j++)
            {
                if (hours[j].getText().equals(ThursdayCourses[i][1]))
                {
                    thurs[i] = new Button(ThursdayCourses[i][0]);
                    thurs[i].setPrefSize(125,75);
                    buttons.getChildren().add(thurs[i]);
                    mainPane.add(thurs[i],4,j+1);
                }
                else
                {
                }
            }
        }
        for (int i=0; i < 8; i++)
        {
            for (int j = 0; j <8; j++)
            {
                if (hours[j].getText().equals(FridayCourses[i][1]))
                {
                    fri[i] = new Button(FridayCourses[i][0]);
                    fri[i].setPrefSize(125,75);
                    buttons.getChildren().add(fri[i]);
                    mainPane.add(fri[i],5,j+1);
                }
                else
                {
                }
            }
        }
        for (int i=0; i < 8; i++)
        {
            for (int j = 0; j <8; j++)
            {
                if (hours[j].getText().equals(SaturdayCourses[i][1]))
                {
                    sat[i] = new Button(SaturdayCourses[i][0]);
                    sat[i].setPrefSize(125,75);
                    buttons.getChildren().add(sat[i]);
                    mainPane.add(sat[i],6,j+1);
                }
                else
                {
                }
            }
        }
        for (int i=0; i < 8; i++)
        {
            for (int j = 0; j <8; j++)
            {
                if (hours[j].getText().equals(SundayCourses[i][1]))
                {
                    sun[i] = new Button(SundayCourses[i][0]);
                    sun[i].setPrefSize(125,75);
                    buttons.getChildren().add(sun[i]);
                    mainPane.add(sun[i],7,j+1);
                }
                else
                {
                }
            }
        }
        /*These next for loops give functions to the buttons that were created. Specifically, each button is set up so that when it is
        clicked, more information about the course shows up. Specifically, the teacher and the location are displayed in a smaller, new
        window alongside the full name of the course.*/
        for (int i=0; i<8; i++)
        {
            if(mon[i]!=null)
            {
                int finalI = i;
                mon[i].setOnMouseClicked(e -> {
                    secondFrame.setVisible(false);
                    classInfo.setText("<html><p>" + MondayCourses[finalI][0] + " <br/>" + MondayProfLoc[finalI][0] + " <br/>" + MondayProfLoc[finalI][1] + "</p></html>");
                    secondFrame.add(classInfo);
                    secondFrame.setVisible(true);

                });
            }
        }
        for (int i=0; i<8; i++)
        {
            if(tues[i]!=null)
            {
                int finalI = i;
                tues[i].setOnMouseClicked(e -> {
                    secondFrame.setVisible(false);
                    classInfo.setText("<html><p>" + TuesdayCourses[finalI][0] + " <br/>" + TuesdayProfLoc[finalI][0] + " <br/>" + TuesdayProfLoc[finalI][1] + "</p></html>");
                    secondFrame.add(classInfo);
                    secondFrame.setVisible(true);

                });
            }
        }
        for (int i=0; i<8; i++)
        {
            if(wed[i]!=null)
            {
                int finalI = i;
                wed[i].setOnMouseClicked(e -> {
                    secondFrame.setVisible(false);
                    classInfo.setText("<html><p>" + WednesdayCourses[finalI][0] + " <br/>" + WednesdayProfLoc[finalI][0] + " <br/>" + WednesdayProfLoc[finalI][1] + "</p></html>");
                    secondFrame.add(classInfo);
                    secondFrame.setVisible(true);

                });
            }
        }
        for (int i=0; i<8; i++)
        {
            if(thurs[i]!=null)
            {
                int finalI = i;
                thurs[i].setOnMouseClicked(e -> {
                    secondFrame.setVisible(false);
                    classInfo.setText("<html><p>" + ThursdayCourses[finalI][0] + " <br/>" + ThursdayProfLoc[finalI][0] + " <br/>" + ThursdayProfLoc[finalI][1] + "</p></html>");
                    secondFrame.add(classInfo);
                    secondFrame.setVisible(true);

                });
            }
        }
        for (int i=0; i<8; i++)
        {
            if(fri[i]!=null)
            {
                int finalI = i;
                fri[i].setOnMouseClicked(e -> {
                    secondFrame.setVisible(false);
                    classInfo.setText("<html><p>" + FridayCourses[finalI][0] + " <br/>" + FridayProfLoc[finalI][0] + " <br/>" + FridayProfLoc[finalI][1] + "</p></html>");
                    secondFrame.add(classInfo);
                    secondFrame.setVisible(true);

                });
            }
        }
        for (int i=0; i<8; i++)
        {
            if(sat[i]!=null)
            {
                int finalI = i;
                tues[i].setOnMouseClicked(e -> {
                    secondFrame.setVisible(false);
                    classInfo.setText("<html><p>" + SaturdayCourses[finalI][0] + " <br/>" + SaturdayProfLoc[finalI][0] + " <br/>" + SaturdayProfLoc[finalI][1] + "</p></html>");
                    secondFrame.add(classInfo);
                    secondFrame.setVisible(true);

                });
            }
        }
        for (int i=0; i<8; i++)
        {
            if(sun[i]!=null)
            {
                int finalI = i;
                sun[i].setOnMouseClicked(e -> {
                    secondFrame.setVisible(false);
                    classInfo.setText("<html><p>" + SundayCourses[finalI][0] + " <br/>" + SundayProfLoc[finalI][0] + " <br/>" + SundayProfLoc[finalI][1] + "</p></html>");
                    secondFrame.add(classInfo);
                    secondFrame.setVisible(true);
                });
            }
        }


    }
    public static void main(String[] args) {

    }


}

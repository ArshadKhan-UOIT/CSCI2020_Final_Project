package Pages;
import DataStructures.Course;
import Windows.Window;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SchedulePage extends Page {
    public SchedulePage()
    {
        mainPane.setGridLinesVisible(true);
        String[][] MondayCourses = new String[8][8];
        String[][] TuesdayCourses = new String[8][8];
        String[][] WednesdayCourses = new String[8][8];
        String[][] ThursdayCourses = new String[8][8];
        String[][] FridayCourses = new String[8][8];
        String[][] SaturdayCourses = new String[8][8];
        String[][]SundayCourses = new String[8][8];
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
        hours[0]=new Text("08:10-09:30");
        hours[1]=new Text("09:40-11:00");
        hours[2]=new Text("11:10-12:30");
        hours[3]=new Text("12:40-14:00");
        hours[4]=new Text("14:10-15:30");
        hours[5]=new Text("15:40-17:00");
        hours[6]=new Text("17:10-18:30");
        hours[7]=new Text("18:40-20:00");
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
        for (int i=1; i<8; i++)
        {
            mainPane.add(col[i],i,0);
        }
        for (int i=0; i<8; i++)
        {
            mainPane.add(row[i],0,i+1);
        }
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
                    m++;
                }
                else if (courseDays.charAt(i) == 'T') {
                    TuesdayCourses[t][0]=(a.getCourseName());
                    TuesdayCourses[t][1]=(a.getTime());
                    t++;
                }
                else if (courseDays.charAt(i) == 'W') {
                    System.out.println("Wednesday");
                    WednesdayCourses[w][0]=(a.getCourseName());
                    WednesdayCourses[w][1]=(a.getTime());
                    w++;
                }
                else if (courseDays.charAt(i) == 'R') {
                    ThursdayCourses[r][0]=(a.getCourseName());
                    ThursdayCourses[r][1]=(a.getTime());
                    r++;
                }
                else if (courseDays.charAt(i) == 'F') {
                    FridayCourses[f][0]=(a.getCourseName());
                    FridayCourses[f][1]=(a.getTime());
                    f++;
                }
                else if (courseDays.charAt(i) == 'A') {
                    SaturdayCourses[sa][0]=(a.getCourseName());
                    SaturdayCourses[sa][1]=(a.getTime());
                    sa++;
                }
                else if (courseDays.charAt(i) == 'U') {
                    SundayCourses[su][0]=(a.getCourseName());
                    SundayCourses[su][1]=(a.getTime());
                    su++;
                }
                else{
                    System.out.println("Nothing");
                }
            }
        }
        for (int i=0; i < 8; i++)
        {
            for (int j = 0; j <8; j++)
            {
                System.out.println("Current: " + MondayCourses[i][1] + " " + i);
                if (hours[j].getText().equals(MondayCourses[i][1]))
                {
                    System.out.println("Yep!, " +  i);
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
            System.out.println("Current: " + TuesdayCourses[i][1] + " " + i);
            for (int j = 0; j <8; j++)
            {
                if (hours[j].getText().equals(TuesdayCourses[i][1]))
                {
                    System.out.println("Yep!, " +  i);
                    tues[i] = new Button(TuesdayCourses[i][0]);
                    tues[i].setPrefSize(125,75);
                    buttons.getChildren().add(tues[i]);
                    mainPane.add(tues[i],2,j+1);
                }
                else
                {
                    System.out.println("Nope!");
                }
            }
        }
        for (int i=0; i < 8; i++)
        {
            System.out.println("Past Tuesday, " +  i);
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

    }
    public static void main(String[] args) {

    }
}

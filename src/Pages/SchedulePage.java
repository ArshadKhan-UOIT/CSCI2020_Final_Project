package Pages;
import Windows.Window;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class SchedulePage extends Page {
    public SchedulePage()
    {
        Text[] days= new Text[8];
        days[0]=new Text("\n\t\tDays\n\n\tTime");
        days[1]=new Text("Monday");
        days[2]=new Text("Tuesday");
        days[3]=new Text("Wednesday");
        days[4]=new Text("Thursday");
        days[5]=new Text("Friday");
        days[6]=new Text("Saturday");
        days[7]=new Text("Sunday");
        VBox buttons = new VBox();
        Button[] b =new Button[8];

        for (int i=1; i<8;i++) {
            b[i] = new Button(days[i].getText());
            b[i].setPrefSize(125,75);
            buttons.getChildren().add(b[i]);
        }
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(20);
        mainPane.getColumnConstraints().addAll(column, column,column,column,column,column,column,column); // each get 50% of width
        //RowConstraints row = new RowConstraints();
        //row.setPercentHeight(20);
        //mainPane.getRowConstraints().addAll(row, row); // each get 50% of width
        //mainPane.add(b[0],0,0);
        mainPane.add(b[1],1,0);
        mainPane.add(b[2],2,0);
        mainPane.add(b[3],3,0);
        mainPane.add(b[4],4,0);
        mainPane.add(b[5],5,0);
        mainPane.add(b[6],6,0);
        mainPane.add(b[7],7,0);

    }
    public static void main(String[] args) {

    }
}

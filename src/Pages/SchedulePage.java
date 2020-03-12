package Pages;
import Windows.Window;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class SchedulePage extends Page {
    public SchedulePage()
    {
        Text[] days= new Text[6];
        days[0]=new Text("\n\n\t\t Days\n\n\tTime");
        days[1]=new Text("Monday");
        days[2]=new Text("Tuesday");
        days[3]=new Text("Wednesday");
        days[4]=new Text("Thursday");
        days[5]=new Text("Friday");
        VBox buttons = new VBox();
        Button[] b =new Button[6];

        for (int i=0; i<6;i++) {
            b[i] = new Button(days[i].getText());
            b[i].setPrefSize(100,100);
            buttons.getChildren().add(b[i]);
        }

        mainPane.add(b[0],0,0);
        mainPane.add(b[1],1,0);
        mainPane.add(b[2],2,0);
        mainPane.add(b[3],3,0);
        mainPane.add(b[4],4,0);
        mainPane.add(b[5],5,0);

    }
    public static void main(String[] args) {

    }
}

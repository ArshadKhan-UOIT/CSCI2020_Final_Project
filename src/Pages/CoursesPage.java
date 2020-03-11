package Pages;

import Windows.Window;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class CoursesPage extends Page {

    public CoursesPage() {
        System.out.println("Pages.CoursesPage created");

        VBox buttons = new VBox();
        //display courses as buttons

        Button[] b =new Button[Window.courses.length];

        for (int i=0; i< Window.courses.length;i++) {
            b[i] = new Button(Window.courses[i].getCourseName());
            buttons.getChildren().add(b[i]);
            System.out.println(Window.courses[i]);

        }


            mainPane.add(buttons,0,0);
    }

    public static void main(String[] args) {
    }

}

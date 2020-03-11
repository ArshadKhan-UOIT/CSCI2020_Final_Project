import CSV.CSVChanger;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class startup extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        //class to handle startup window before homepage window appears

        VBox titleBox = new VBox();
        Text banner = new Text("Loading...");
        Image bannerImage = new Image("file:DataFiles/CourseContent.png");
        ImageView imageView1 = new ImageView(bannerImage);
        titleBox.getChildren().add(imageView1);
        titleBox.getChildren().add(banner);
        titleBox.setAlignment(Pos.CENTER);
        banner.setFont(Font.font("URWGothicLSemi-Bold", FontWeight.BOLD, FontPosture.REGULAR, 35));
        banner.setFill( new Color( 0.427,0.51,0.792,1.0));

        primaryStage.setTitle("Course Content");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(titleBox,300, 300, Color.BLACK));
        primaryStage.show();

        CSVChanger.read("courses.csv");
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"Software Systems", "Mario", "CSCI2020, Monday and Tuesday,3:40-5pm"});
        CSVChanger.write("courses.csv",data);

        //text fade in then out
        FadeTransition ft = new FadeTransition(Duration.millis(3000), banner);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
    }
}

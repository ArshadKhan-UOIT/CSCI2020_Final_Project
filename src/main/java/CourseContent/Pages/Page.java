package main.java.CourseContent.Pages;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


/*
 * Page Class:
 * This class is a Parent class of the pages displayed on the main window
 */
public abstract class Page {
    //main pane is a pane that get changed by the children classes to hold the main pane for that page
    protected GridPane mainPane = new GridPane();

    //getMainPane() function returns the mainPage variable
    public Pane getMainPane() {
        return this.mainPane;
    }
}

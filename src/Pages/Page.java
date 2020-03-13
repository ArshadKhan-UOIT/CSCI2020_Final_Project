package Pages;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public abstract class Page {
    protected GridPane mainPane = new GridPane();

    public Pane getMainPane() {
        return this.mainPane;
    }
}

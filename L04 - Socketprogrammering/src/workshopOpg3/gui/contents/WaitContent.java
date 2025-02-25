package workshopOpg3.gui.contents;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WaitContent extends Stage {

    private final GridPane pane;

    public WaitContent() {
        pane = new GridPane();

        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);


    }

    public GridPane getPane() {
        return pane;
    }
}

package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;


public class StartingController implements Initializable, ControlledScreen {

    private ScreensController controller;

    @Override public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML public final void toConfigScreen() {
        controller.setScreen(Main.CONFIGURE_ID);
    }

    @FXML public final void toLoadGameScreen() {
        Main.loadScene(Main.LOAD_ID, Main.LOAD_FILE);
        controller.setScreen(Main.LOAD_ID);
    }

    public final void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
}

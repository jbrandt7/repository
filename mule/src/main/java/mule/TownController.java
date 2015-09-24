package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import mule.model.*;

public class TownController implements Initializable, ControlledScreen {

    ScreensController controller;

    @FXML Group townParent;

    @Override public void initialize(URL url, ResourceBundle rb) {
        Main.setTown(new Town(townParent));
    }

    public void goToMapScreen() {
        controller.setScreen(Main.mapID);
    }

    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

}

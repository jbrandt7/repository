package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.event.*;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import mule.model.*;
import mule.model.map.*;
import mule.model.town.*;
import mule.model.resources.*;
import mule.model.player.*;

public class StartingController implements Initializable, ControlledScreen {

    ScreensController controller; 

    @FXML Button toConfigButton, loadGameButton;

    @Override public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML public void toConfigScreen() {
        controller.setScreen(Main.configureID);
    }

    @FXML public void toLoadGameScreen() {
        Main.loadScene(Main.loadID, Main.loadFile);
        controller.setScreen(Main.loadID);
    }

    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
}

package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.animation.*;
import javafx.event.*;
import javafx.collections.FXCollections;
import java.util.List;

import mule.model.*;
import mule.model.map.*;
import mule.model.town.*;
import mule.model.player.*;

public class LoadGameController implements Initializable, ControlledScreen {

    private ScreensController controller;

    @FXML private ListView savesListView;

    private List<String> saves;

    @Override public final void initialize(URL url, ResourceBundle rb) {
        saves = Main.getDBController().getSaves();
        if (saves != null) {
            savesListView.setItems(FXCollections.observableArrayList(saves));
        } else {
            savesListView.setItems(FXCollections.observableArrayList("No saved games found"));
        }
    }

    public final void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    @FXML public final void loadGame() {
        String gameID = (String) savesListView.getSelectionModel().getSelectedItem();
        Main.getDBController().loadGame(gameID);
        Main.loadScene(Main.MAP_ID, Main.MAP_FILE);

        //((Label) Main.getInfoBar().getItems().get(0)) .setFont(Font.font("System", FontWeight.BOLD, Main.FONT_SIZE));

        controller.setScreen(Main.MAP_ID);
    }

    @FXML public final void goBack() {
        controller.setScreen(Main.STARTING_ID);
    }

    public ScreensController getController() {
        return controller;
    }

}

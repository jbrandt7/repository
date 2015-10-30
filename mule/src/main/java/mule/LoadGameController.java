package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.animation.*;
import javafx.event.*;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import java.util.List;

import mule.model.*;
import mule.model.map.*;
import mule.model.town.*;
import mule.model.player.*;

public class LoadGameController implements Initializable, ControlledScreen {

    ScreensController controller;

    @FXML private ListView savesListView;

    private List<String> saves;

    @Override public void initialize(URL url, ResourceBundle rb) {
        saves = Main.getDBController().getSaves();
        if (saves != null) {
            savesListView.setItems(FXCollections.observableArrayList(saves));
        } else {
            savesListView.setItems(FXCollections.observableArrayList("No saved games found"));
        }
    }

    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    @FXML public void loadGame() {
        String gameID = (String) savesListView.getSelectionModel().getSelectedItem();
        Main.getDBController().loadGame(gameID);
        Main.loadScene(Main.mapID, Main.mapFile);

        ((Label) Main.getInfoBar().getItems().get(0)) .setFont(Font.font("System", FontWeight.BOLD, 13));

        controller.setScreen(Main.mapID);
    }

    @FXML public void goBack() {
        controller.setScreen(Main.startingID);
    }

}

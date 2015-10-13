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

import mule.model.*;
import mule.model.map.*;
import mule.model.town.*;
import mule.model.player.*;

public class PlayersController implements Initializable, ControlledScreen {

    ScreensController controller;

    @FXML private VBox playersBox;

    @Override public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            ((ChoiceBox)((HBox) playersBox.getChildren().get(i)).getChildren().get(1))
                    .setItems(FXCollections.observableArrayList("Human", "Flapper",
                    "Bonzoid", "Ugaite", "Buzzite"));
            ((ChoiceBox)((HBox) playersBox.getChildren().get(i)).getChildren().get(1))
                    .getSelectionModel().select(0);
        }

        for (int i = Main.getPlayerCount(); i < 4; i++) {
            ((HBox) playersBox.getChildren().get(i)).setOpacity(0.0);
        }
    }

    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    @FXML private void goToMapScreen(ActionEvent event) {
        if (processPlayers()) {
            initializeTurn();
            Main.loadScene(Main.mapID, Main.mapFile);

            ((Label) Main.getInfoBar().getItems().get(0))
                    .setFont(Font.font("System", FontWeight.BOLD, 13));

            controller.setScreen(Main.mapID);
        }
    }

    private boolean processPlayers() {
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            String name = ((TextField)((HBox) playersBox.getChildren().get(i)).getChildren().get(0))
                    .getCharacters().toString();
            if (name.equals(""))
                return false;

            String race = (String) ((ChoiceBox)((HBox) playersBox.getChildren().get(i)).getChildren().get(1))
                    .getValue();
            Color color = ((ColorPicker)((HBox) playersBox.getChildren().get(i)).getChildren().get(2))
                    .getValue();

            Player p;
            if (race.equals("Human")) {
                p = new Human(name, color);
            } else if (race.equals("Bonzoid")) {
                p = new Bonzoid(name, color);
            } else if (race.equals("Ugaite")) {
                p = new Ugaite(name, color);
            } else if (race.equals("Buzzite")) {
                p = new Buzzite(name, color);
            } else {
                p = new Flapper(name, color);
            }

            Main.setPlayer(i, p);
        }

        return true;
    }

    private void initializeTurn() {
        Main.setTurn(new Turn(Main.getPlayerCount()));
    }

}

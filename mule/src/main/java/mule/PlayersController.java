package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;

import mule.model.*;
import mule.model.player.*;

public class PlayersController implements Initializable, ControlledScreen {

    private ScreensController controller;

    @FXML private VBox playersBox;

    @Override public final void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            ((ChoiceBox)((HBox) playersBox.getChildren().get(i))
                    .getChildren().get(1)).setItems(FXCollections
                    .observableArrayList("Human", "Flapper","Bonzoid",
                    "Ugaite", "Buzzite"));
            ((ChoiceBox)((HBox) playersBox.getChildren().get(i))
                    .getChildren().get(1)).getSelectionModel().select(0);
        }

        for (int i = Main.getPlayerCount(); i < 4; i++) {
            ((HBox) playersBox.getChildren().get(i)).setOpacity(0.0);
        }
    }

    public final void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    @FXML public final void goToMapScreen() {
        if (processPlayers()) {
            initializeTurn();
            Main.loadScene(Main.MAP_ID, Main.MAP_FILE);

            controller.setScreen(Main.MAP_ID);
        }
    }

    private boolean processPlayers() {
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            String name = ((TextField)((HBox) playersBox.getChildren()
                    .get(i)).getChildren().get(0)).getCharacters().toString();

            if (name.equals("")) {
                return false;
            }

            String race = (String) ((ChoiceBox)((HBox) playersBox
                    .getChildren().get(i)).getChildren().get(1)).getValue();
            Color color = ((ColorPicker)((HBox) playersBox.getChildren()
                    .get(i)).getChildren().get(2)).getValue();

            Player p;

            switch (race) {
                case ("Human"):
                    p = new Human(name, color);
                    break;
                case ("Bonzoid"):
                    p = new Bonzoid(name, color);
                    break;
                case ("Ugaite"):
                    p = new Ugaite(name, color);
                    break;
                case ("Buzzite"):
                    p = new Buzzite(name, color);
                    break;
                default:
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

package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import mule.model.Player;

public class PlayersController implements Initializable, ControlledScreen {

    ScreensController controller;

    @FXML private VBox playersBox;

    @Override public void initialize(URL url, ResourceBundle rb) {
        System.out.println(Main.getPlayerCount());
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            ((ChoiceBox)((HBox) playersBox.getChildren().get(i)).getChildren().get(1))
                    .setItems(FXCollections.observableArrayList("Human", "Flapper",
                    "Bonzoid", "Ugaite", "Buzzite"));
        }

        for (int i = Main.getPlayerCount(); i < 4; i++) {
            ((HBox) playersBox.getChildren().get(i)).setOpacity(0.0);
        }
    }

    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    @FXML private void goToMapScreen(ActionEvent event) {
        processPlayers();
        controller.setScreen(Main.mapID);
    }

    private void processPlayers() {
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            String name = ((TextField)((HBox) playersBox.getChildren().get(i)).getChildren().get(0))
                    .getCharacters().toString();
            String race = (String) ((ChoiceBox)((HBox) playersBox.getChildren().get(i)).getChildren().get(1))
                    .getValue();
            Color color = ((ColorPicker)((HBox) playersBox.getChildren().get(i)).getChildren().get(2))
                    .getValue();
            Player p = new Player(name, race, color);
            Main.setPlayer(i, p);
            System.out.println(p.getName() + ", " + p.getRace());
        }
    }

}

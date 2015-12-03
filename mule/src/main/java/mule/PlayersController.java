package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.animation.*;
import javafx.event.*;
import javafx.util.Duration;

import mule.model.*;
import mule.model.player.*;

public class PlayersController implements Initializable, ControlledScreen {

    private ScreensController controller;

    @FXML private VBox playersBox;
    @FXML private HBox players;

    @Override public final void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            ((ChoiceBox)((HBox) playersBox.getChildren().get(i))
                    .getChildren().get(1)).setItems(FXCollections
                    .observableArrayList("Human", "Flapper","Bonzoid",
                    "Ugaite", "Buzzite", "Pikachu", "Snorlax"));
            ((ChoiceBox)((HBox) playersBox.getChildren().get(i))
                    .getChildren().get(1)).getSelectionModel().select(0);

            ((ChoiceBox)((HBox) playersBox.getChildren().get(i))
                    .getChildren().get(2)).setItems(FXCollections
                    .observableArrayList("Blue", "Red", "Yellow", "Pink"));
            ((ChoiceBox)((HBox) playersBox.getChildren().get(i))
                    .getChildren().get(2)).getSelectionModel().select(0);
        }

        for (int i = Main.getPlayerCount(); i < 4; i++) {
            ((HBox) playersBox.getChildren().get(i)).setOpacity(0.0);
        }
    }

    public final void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    @FXML public final void continueGame() {
        if (processPlayers()) {
            setUpPlayers();
            initializeTurn();
            showPlayerAnimation();
        }
    }

    public final void goToMapScreen() {
        Main.loadScene(Main.MAP_ID, Main.MAP_FILE);
        controller.setScreen(Main.MAP_ID);
    }

    private void setUpPlayers() {
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            ImageView toAdd = new ImageView(PlayerImageNames.getFileName(Main.getPlayer(i)));
            players.getChildren().add(toAdd);
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
            String colorChoice = (String) ((ChoiceBox)((HBox) playersBox.getChildren()
                    .get(i)).getChildren().get(2)).getValue();

            Color color = convertColor(colorChoice);

            Player p = createPlayer(name, race, color);
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
                case ("Pikachu"):
                    p = new Pikachu(name, color);
                    break;
                case ("Snorlax"):
                    p = new Snorlax(name, color);
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

    private void showPlayerAnimation() {

        KeyFrame keyFrame = new KeyFrame(Duration.millis(6000),
                new KeyValue(players.translateXProperty(), 760));
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.setOnFinished(e -> goToMapScreen());
        timeline.play();

    }

    private Color convertColor(String colorChoice) {
        switch (colorChoice) {
            case ("Red"):
                return Color.RED;
            case ("Yellow"):
                return Color.YELLOW;
            case ("Pink"):
                return Color.PINK;
            default:
                return Color.BLUE;
        }
    }

    private Player createPlayer(String name, String race, Color color) {
        switch (race) {
            case ("Human"):
                return new Human(name, color);
            case ("Bonzoid"):
                return new Bonzoid(name, color);
            case ("Ugaite"):
                return new Ugaite(name, color);
            case ("Buzzite"):
                return new Buzzite(name, color);
            default:
                return new Flapper(name, color);
        }
    }

}

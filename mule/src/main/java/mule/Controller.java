package mule;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.collections.FXCollections;

import mule.model.*;

public class Controller implements Initializable {

    @FXML private Window window;
    @FXML private MapController mapController;

    @FXML
    private Button toConfigButton;

    @FXML
    private Slider playersSlider;

    @FXML
    private ChoiceBox mapChoiceBox, difficultyChoiceBox;

    @FXML
    public void toConfigScreen(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/mule/view/GameConfigurationScreen.fxml"));
        Main.changeScene(new Scene(parent), "Game Configuration");
    }

    @FXML
    private Button toPlayerSetUpButton;

    @FXML
    private HBox p1Box, p2Box, p3Box, p4Box;

    @FXML private VBox playersBox;

    @FXML
    public void toPlayerSetUp(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/mule/view/PlayerSelection.fxml"));
        Main.setPlayerCount((int) playersSlider.getValue());
        Main.changeScene(new Scene(parent), "Player Configuration");
    }

    @FXML
    private Button toMapButton;

    @FXML
    private Group mapParent;

    @FXML
    private TextArea commandField;

    @FXML
    public void toMap(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/mule/view/MapScreen.fxml"));
        Main.changeScene(new Scene(parent), "Main Map");
        processPlayers(Main.getPlayerCount());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (mapChoiceBox != null) {
            mapChoiceBox.setItems(FXCollections.observableArrayList(
                        "Mars", "Venus", "Mercury"));
        }
        if (difficultyChoiceBox != null) {
            difficultyChoiceBox.setItems(FXCollections.observableArrayList(
                        "Easy", "Medium", "Hard"));
        }
        if (playersBox != null) {
            for (int i = 0; i < 4; i++) {
                ((ChoiceBox)((HBox) playersBox.getChildren().get(i)).getChildren().get(1))
                        .setItems(FXCollections.observableArrayList("Human", "Protus", "Zerg"));
            }
        }

        if (mapParent != null) {
            setupMap();

            EventHandler<KeyEvent> plotSelectionHandler = new EventHandler<KeyEvent>() {
                public void handle(KeyEvent event) {
                    System.out.println("Key Pressed");
                }
            };

            commandField.setOnKeyPressed(plotSelectionHandler);
            commandField.appendText("Welcome to M.U.L.E!\nChoose your plot of land: ");
        }
    }

    private void setupMap() {
        Map map = new Map(mapParent);
        Main.setMap(map);
    }

    public void processPlayers(int playerCount) {
        for (int i = 0; i < playerCount; i++) {
            String name = ((TextField)((HBox) playersBox.getChildren().get(i)).getChildren().get(0))
                    .getCharacters().toString();
            String race = (String) ((ChoiceBox)((HBox) playersBox.getChildren().get(i)).getChildren().get(1))
                    .getValue();
            Player p = new Player(name, race, "Blue");
            Main.setPlayer(i, p);
            System.out.println(p.getName() + ", " + p.getRace());
        }
    }

}

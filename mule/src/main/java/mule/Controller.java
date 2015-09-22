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

import mule.model.Map;

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
        if (p1Box != null) {
            Player p = new Player();
            p.setName(p1Box.getChildren().get(0).getValue());
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

}

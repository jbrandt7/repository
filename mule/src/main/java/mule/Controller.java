package mule;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.collections.FXCollections;

public class Controller implements Initializable {

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
    private ChoiceBox p1RaceChoiceBox, p2RaceChoiceBox,
            p3RaceChoiceBox, p4RaceChoiceBox;

    @FXML
    public void toPlayerSetUp(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/mule/view/PlayerSelection.fxml"));
        Main.setPlayerCount((int) playersSlider.getValue());
        Main.changeScene(new Scene(parent), "Player Configuration");
    }

    @FXML
    private Button toPlaceholderButton;

    @FXML
    public void toPlaceholder(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/mule/view/PlaceholderScreen.fxml"));
        Main.changeScene(new Scene(parent), "Placeholder");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (mapChoiceBox != null) {
            mapChoiceBox.setItems(FXCollections.observableArrayList(
                        "Mars", "Venus", "Mars"));
        }
        if (difficultyChoiceBox != null) {
            difficultyChoiceBox.setItems(FXCollections.observableArrayList(
                        "Easy", "Medium", "Hard"));
        }
        if (p1RaceChoiceBox!= null) {
            p1RaceChoiceBox.setItems(FXCollections.observableArrayList(
                    "Human", "Protoss", "Zerg"));
        }
        if (p2RaceChoiceBox!= null) {
            p2RaceChoiceBox.setItems(FXCollections.observableArrayList(
                    "Human", "Protoss", "Zerg"));
        }
        if (p3RaceChoiceBox!= null) {
            p3RaceChoiceBox.setItems(FXCollections.observableArrayList(
                    "Human", "Protoss", "Zerg"));
        }
        if (p4RaceChoiceBox!= null) {
            p4RaceChoiceBox.setItems(FXCollections.observableArrayList(
                    "Human", "Protoss", "Zerg"));
        }
    }

}

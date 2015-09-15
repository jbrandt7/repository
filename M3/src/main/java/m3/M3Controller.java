package m3;

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

public class M3Controller implements Initializable {

    @FXML
    private Button toConfigButton;

    @FXML
    private Slider playersSlider;

    @FXML
    private ChoiceBox mapChoiceBox, difficultyChoiceBox;

    @FXML
    public void toConfigScreen(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/m3/view/GameConfigurationScreen.fxml"));
        M3.changeScene(new Scene(parent), "Game Configuration");
    }

    @FXML
    private Button toPlayerSetUpButton;

    @FXML
    private ChoiceBox p1RaceChoiceBox;

    @FXML
    private ChoiceBox p2RaceChoiceBox;

    @FXML
    private ChoiceBox p3RaceChoiceBox;

    @FXML
    private ChoiceBox p4RaceChoiceBox;

    @FXML
    public void toPlayerSetUp(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/m3/view/PlayerSelection.fxml"));
        M3.setPlayerCount((int) playersSlider.getValue());
        M3.changeScene(new Scene(parent), "Player Configuration");
    }

    @FXML
    private Button toPlaceholderButton;

    @FXML
    public void toPlaceholder(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/m3/view/PlaceholderScreen.fxml"));
        M3.changeScene(new Scene(parent), "Placeholder");
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

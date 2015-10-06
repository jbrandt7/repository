package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;

public class ConfigurationController implements Initializable, ControlledScreen {

    ScreensController controller;

    @FXML Slider playersSlider;

    @FXML ChoiceBox mapChoiceBox, difficultyChoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapChoiceBox.setItems(FXCollections.observableArrayList(
                    "Mars", "Venus", "Mercury"));
        mapChoiceBox.getSelectionModel().select(0);
        difficultyChoiceBox.setItems(FXCollections.observableArrayList(
                    "Easy", "Medium", "Hard"));
        difficultyChoiceBox.getSelectionModel().select(0);
    }

    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    @FXML
    private void goToPlayerScreen(ActionEvent event) {
        Main.setPlayerCount((int) playersSlider.getValue());
        Main.loadScene(Main.playerConID, Main.playerConFile);
        controller.setScreen(Main.playerConID);
    }

}

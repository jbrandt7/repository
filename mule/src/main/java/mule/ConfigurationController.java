package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;

public class ConfigurationController implements Initializable, ControlledScreen {

    private ScreensController controller;

    @FXML private Slider playersSlider;

    @FXML private ChoiceBox mapChoiceBox, difficultyChoiceBox;

    @FXML private TextField saveTextField;

    @Override
    public final void initialize(URL url, ResourceBundle rb) {
        mapChoiceBox.setItems(FXCollections.observableArrayList(
                    "Mars", "Venus", "Mercury"));
        mapChoiceBox.getSelectionModel().select(0);
        difficultyChoiceBox.setItems(FXCollections.observableArrayList(
                    "Easy", "Medium", "Hard"));
        difficultyChoiceBox.getSelectionModel().select(0);
    }

    public final void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    public final ScreensController getController() {
        return controller;
    }

    @FXML
    public final void goToPlayerScreen() {
        //if (Main.getDBController().checkName(saveTextField.getCharacters().toString())) {
            Main.setSaveName(saveTextField.getCharacters().toString());
            Main.setPlayerCount((int) playersSlider.getValue());
            Main.loadScene(Main.PLAYER_CONFIG_ID, Main.PLAYER_CONFIG_FILE);
            controller.setScreen(Main.PLAYER_CONFIG_ID);
        //}
    }

}

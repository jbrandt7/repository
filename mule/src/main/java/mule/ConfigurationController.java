package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.BooleanProperty;

public class ConfigurationController implements Initializable, ControlledScreen {

    private ScreensController controller;

    @FXML private Slider playersSlider;

    @FXML private ChoiceBox<String> mapChoiceBox, difficultyChoiceBox;

    @FXML private TextField saveTextField;

    @FXML private CheckBox saveCheckBox;

    @Override
    public final void initialize(URL url, ResourceBundle rb) {
        mapChoiceBox.setItems(FXCollections.observableArrayList(
                    "Default", "River", "Desert","Random"));
        mapChoiceBox.getSelectionModel().select(0);

        difficultyChoiceBox.setItems(FXCollections.observableArrayList(
                    "Easy", "Medium", "Hard"));
        difficultyChoiceBox.getSelectionModel().select(0);

        saveTextField.setEditable(false);
        saveTextField.getStyleClass().add("disabled");

        saveCheckBox.selectedProperty().addListener(new ChangeListener() {
            @Override public void changed(ObservableValue o, Object oldBool,
                    Object newBool) {
                if (saveTextField.isEditable()) {
                    saveTextField.setEditable(false);
                    saveTextField.getStyleClass().add("disabled");
                } else {
                    saveTextField.setEditable(true);
                    saveTextField.getStyleClass().remove("disabled");
                }
            }
        });
    }

    public final void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    public final ScreensController getController() {
        return controller;
    }

    @FXML
    public final void goToPlayerScreen() {
        if (saveCheckBox.isSelected() && Main.getDBController().checkName(
                    saveTextField.getCharacters().toString())) {
            saveTextField.getStyleClass().add("error");
            return;
        }

        Main.setSaveName(saveTextField.getCharacters().toString());
        Main.setMapType(mapChoiceBox.getValue());
        Main.setPlayerCount((int) playersSlider.getValue());
        Main.loadScene(Main.PLAYER_CONFIG_ID, Main.PLAYER_CONFIG_FILE);
        controller.setScreen(Main.PLAYER_CONFIG_ID);
    }

}

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
import javafx.scene.control.Button;

public class M3Controller implements Initializable {

    @FXML
    private Button toConfigButton;

    @FXML
    public void toConfigScreen(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/m3/view/GameConfigurationScreen.fxml"));
        M3.changeScene(new Scene(parent), "Game Configuration");
    }

    @FXML
    private Button toPlayerSetUpButton;

    @FXML
    public void toPlayerSetUp(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/m3/view/PlayerSelection.fxml"));
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
    public void initialize(URL arg0, ResourceBundle arg1) {}

}

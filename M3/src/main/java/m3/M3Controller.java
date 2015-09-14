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
        Stage primaryStage = M3.getStage();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Configuration");
        primaryStage.show();
    }

    @FXML
    private Button toPlayerSetUpButton;

    @FXML
    public void toPlayerSetUp(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/m3/view/PlayerSelection.fxml"));
        Stage stage = M3.getStage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Player Configuration");
        stage.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {}

}

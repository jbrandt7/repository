package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import java.util.List;

public class LoadGameController implements Initializable, ControlledScreen {

    private ScreensController controller;

    @FXML private ListView savesListView;

    @Override public final void initialize(URL url, ResourceBundle rb) {
        List<String> saves = Main.getDBController().getSaves();
        if (saves != null) {
            savesListView.setItems(FXCollections.observableArrayList(saves));
        } else {
            savesListView.setItems(FXCollections.observableArrayList("No saved games found"));
        }
    }

    /**
     * Sets the main screen controller
     * @param screenParent the screen controller
     */
    public final void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    @FXML public final void loadGame() {
        String gameID = (String) savesListView.getSelectionModel().getSelectedItem();
        Main.getDBController().loadGame(gameID);
        Main.loadScene(Main.MAP_ID, Main.MAP_FILE);

        //((Label) Main.getInfoBar().getItems().get(0)) .setFont(Font.font("System", FontWeight.BOLD, Main.FONT_SIZE));

        controller.setScreen(Main.MAP_ID);
    }

    /**
     * Goes back to the original starting screen
     */
    @FXML public final void goBack() {
        controller.setScreen(Main.STARTING_ID);
    }

    /**
     * Gets the screen controller for this controller
     * @return the load game screen controller
     */
    public final ScreensController getController() {
        return controller;
    }

}

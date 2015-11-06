package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;

import mule.model.resources.*;

public class StoreController implements Initializable, ControlledScreen {

    private ScreensController controller;

    @FXML private ChoiceBox<Resource> resourceChoiceBox;

    @FXML private ChoiceBox<Integer> quantityChoiceBox;

    @FXML private Label mapText, timerLabel;
    private static Label mapLabel, timerText;

    @FXML private ToolBar infoBar;
    private static ToolBar toolBar;

    @Override public final void initialize(URL location, ResourceBundle resources) {
        setupInfoBar();
        resourceChoiceBox.setItems(FXCollections.observableArrayList(new Energy(),
                    new Food(), new Smithore()));
        resourceChoiceBox.getSelectionModel().select(0);

        quantityChoiceBox.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
        quantityChoiceBox.getSelectionModel().select(0);

    }

    public final void buyResource() {
        for (int i = 0; i < quantityChoiceBox.getValue(); i++) {
            if (Main.getTown().getStore().buyResource(Main.getCurrentPlayer(),
                    resourceChoiceBox.getValue())) {
                mapText.setText(resourceChoiceBox.getValue() + " bought");
            } else {
                mapText.setText("Not enough money");
            }
        }
        goToTownScreen();
    }

    public final void sellResource() {
        for (int i = 0; i < quantityChoiceBox.getValue(); i++) {
            if (Main.getTown().getStore().sellResource(Main.getCurrentPlayer(),
                    resourceChoiceBox.getValue())) {
                mapText.setText(resourceChoiceBox.getValue() + " sold");
            } else {
                mapText.setText("Not enough resources");
            }
        }
        goToTownScreen();
    }

    public final void buyFoodMule() {
        if (Main.getTown().getStore().buyMule(Main.getCurrentPlayer(), new Food())) {
            goToMapScreen();
        }
    }

    public final void buyEnergyMule() {
        if (Main.getTown().getStore().buyMule(Main.getCurrentPlayer(), new Energy())) {
            goToMapScreen();
        }
    }

    public final void buyOreMule() {
        if (Main.getTown().getStore().buyMule(Main.getCurrentPlayer(), new Energy())) {
            goToMapScreen();
        }
    }

    /**
     * Sets the main screen controller
     * @param screenParent the screen controller
     */
    public final void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    private void goToTownScreen() {
        controller.setScreen(Main.TOWN_ID);
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            MapController.getMenuBar().getMenus().get(i).setText(
                    Main.getPlayer(i).getName());
        }
    }

    public final void goToMapScreen() {
        controller.setScreen(Main.MAP_ID);
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            MapController.getMenuBar().getMenus().get(i).setText(
                    Main.getPlayer(i).getName());
        }
    }


    private void setupInfoBar() {
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            ((Label) infoBar.getItems().get(i)).setText(Main.getPlayer(i) + ": "
                    + Main.getPlayer(i).getMoney());
        }
        for (int i = Main.getPlayerCount(); i < 4; i++) {
            ((Label) infoBar.getItems().get(i)).setOpacity(0.0);
        }
        mapLabel = mapText;
        timerText = timerLabel;
        toolBar = infoBar;
    }

    public static Label getHelperLabel() { return mapLabel; }

    public static Label getTimerLabel() { return timerText; }

    public static ToolBar getInfoBar() { return toolBar; }

}

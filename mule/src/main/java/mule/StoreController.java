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

    @FXML private ToolBar toolBar;
    private static ToolBar toolBarInstance;

    @FXML private Label timerLabel;
    private static Label timerLabelInstance;

    @FXML private TextArea displayText;
    private static TextArea displayTextInstance;

    @Override public final void initialize(URL location, ResourceBundle resources) {
        resourceChoiceBox.setItems(FXCollections.observableArrayList(new Energy(),
                    new Food(), new Smithore()));
        resourceChoiceBox.getSelectionModel().select(0);

        quantityChoiceBox.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
        quantityChoiceBox.getSelectionModel().select(0);

        setupInfoBar();
        setupDisplayText();
    }

    public final void buyResource() {
        for (int i = 0; i < quantityChoiceBox.getValue(); i++) {
            if (Main.getTown().getStore().buyResource(Main.getCurrentPlayer(),
                    resourceChoiceBox.getValue())) {
                updateDisplayText(resourceChoiceBox.getValue() + " bought");
            } else {
                updateDisplayText("Not enough money");
            }
        }
        goToTownScreen();
    }

    public final void sellResource() {
        for (int i = 0; i < quantityChoiceBox.getValue(); i++) {
            if (Main.getTown().getStore().sellResource(Main.getCurrentPlayer(),
                    resourceChoiceBox.getValue())) {
                updateDisplayText(resourceChoiceBox.getValue() + " sold");
            } else {
                updateDisplayText("Not enough resources");
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

    public void goToTownScreen() {
        controller.setScreen(Main.TOWN_ID);
        Main.setToolBar(TownController.getToolBar());
        Main.setTimerLabel(TownController.getTimerLabel());
        TownController.getDisplayText().setText(displayText.getText());
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            TownController.updatePlayerMenu(i);
        }
    }

    public final void goToMapScreen() {
        controller.setScreen(Main.MAP_ID);
        /*Main.setMenuBar(MapController.getMenuBar());*/
        Main.setToolBar(MapController.getToolBar());
        Main.setTimerLabel(MapController.getTimerLabel());
        MapController.getDisplayText().setText(displayText.getText());
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            MapController.updatePlayerMenu(i);
        }
    }

    public static void updatePlayerMenu(int i) {
        ((Label) (toolBarInstance.getItems().get(i * 2))).setText(
                Main.getPlayer(i).getName() + "\n" +
                "M: " + Main.getPlayer(i).getMoney() + "\n" +
                "E: " + Main.getPlayer(i).getResource(new Energy()) + "\n" +
                "F: " + Main.getPlayer(i).getResource(new Food()) + "\n" +
                "S: " + Main.getPlayer(i).getResource(new Smithore()));
    }

    public static void updateDisplayText(String text) {
        displayTextInstance.setText(text + "\n" + displayTextInstance.getText());
    }

    private void setupInfoBar() {
        toolBarInstance = toolBar;
        Main.setToolBar(toolBarInstance);
        timerLabelInstance = timerLabel;
        Main.setTimerLabel(timerLabelInstance);

        for (int i = 0; i < Main.getPlayerCount(); i++) {
            updatePlayerMenu(i);        }
        for (int i = Main.getPlayerCount(); i < 4; i++) {
            toolBarInstance.getItems().get(i * 2).setVisible(false);
            toolBarInstance.getItems().get(i * 2 + 1).setVisible(false);
        }
    }

    private void setupDisplayText() {
        displayText.setEditable(false);
        displayText.setText("Welcome to M.U.L.E! Select a plot " +
                "of land to continue or select the town to pass.\n");
        displayTextInstance = displayText;
    }

    public static ToolBar getToolBar() { return toolBarInstance; }

    public static Label getTimerLabel() { return timerLabelInstance; }

    public static TextArea getDisplayText() { return displayTextInstance; }

}

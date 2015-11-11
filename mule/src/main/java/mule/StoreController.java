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

    @FXML private MenuBar menuBar;
    private static MenuBar menuBarInstance;

    @FXML private TextArea displayText;
    private static TextArea displayTextInstance;

    @Override public final void initialize(URL location, ResourceBundle resources) {
        //setupInfoBar();
        resourceChoiceBox.setItems(FXCollections.observableArrayList(new Energy(),
                    new Food(), new Smithore()));
        resourceChoiceBox.getSelectionModel().select(0);

        quantityChoiceBox.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
        quantityChoiceBox.getSelectionModel().select(0);

        setupInfoBar();

        displayTextInstance = displayText;

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
        Main.setMenuBar(TownController.getMenuBar());
        TownController.getDisplayText().setText(displayText.getText());
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            TownController.updatePlayerMenu(i);
        }
    }

    public final void goToMapScreen() {
        controller.setScreen(Main.MAP_ID);
        Main.setMenuBar(MapController.getMenuBar());
        MapController.getDisplayText().setText(displayText.getText());
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            MapController.updatePlayerMenu(i);
        }
    }

    public static void updatePlayerMenu(int i) {
        menuBarInstance.getMenus().get(i).setText(Main.getPlayer(i).getName());
        menuBarInstance.getMenus().get(i).getItems().get(0).setText(
                "Money: " + Main.getPlayer(i).getMoney());
        menuBarInstance.getMenus().get(i).getItems().get(1).setText(
                "Energy: " + Main.getPlayer(i).getResource(new Energy()));
        menuBarInstance.getMenus().get(i).getItems().get(2).setText(
                "Food: " + Main.getPlayer(i).getResource(new Food()));
        menuBarInstance.getMenus().get(i).getItems().get(3).setText(
                "Smithore: " + Main.getPlayer(i).getResource(new Smithore()));
        menuBarInstance.getMenus().get(i).getItems().get(4).setText(
                "Score: " + Main.getPlayer(i).getScore());
    }

    public static void updateDisplayText(String text) {
        displayTextInstance.setText(text + "\n" + displayTextInstance.getText());
    }

    private void setupInfoBar() {
        menuBarInstance = menuBar;
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            updatePlayerMenu(i);
        }
        for (int i = Main.getPlayerCount(); i < 4; i++) {
            menuBar.getMenus().get(i).setVisible(false);
        }
    }

    public static MenuBar getMenuBar() { return menuBarInstance; }

    public static TextArea getDisplayText() { return displayTextInstance; }

}

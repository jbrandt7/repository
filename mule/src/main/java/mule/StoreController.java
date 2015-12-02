package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.collections.FXCollections;

import mule.model.resources.*;

public class StoreController implements Initializable, ControlledScreen {

    private ScreensController controller;

    @FXML private ChoiceBox<Integer> oreQuantity, foodQuantity, energyQuantity;

    @FXML private ToolBar toolBar;
    private static ToolBar toolBarInstance;

    @FXML private Label timerLabel;
    private static Label timerLabelInstance;

    @FXML private TextArea displayText;
    private static TextArea displayTextInstance;

    @Override public final void initialize(URL location, ResourceBundle resources) {

        oreQuantity.setItems(FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10));
        foodQuantity.setItems(FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10));
        energyQuantity.setItems(FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10));

        oreQuantity.getSelectionModel().select(0);
        foodQuantity.getSelectionModel().select(0);
        energyQuantity.getSelectionModel().select(0);

        setupInfoBar();
        setupDisplayText();
    }

    public final void buyResource() {
        ResourceBag cart = new ResourceBag();

        cart.add(new Smithore(), oreQuantity.getValue());
        cart.add(new Food(), foodQuantity.getValue());
        cart.add(new Energy(), energyQuantity.getValue());

        if (Main.getTown().getStore().buyResource(Main.getCurrentPlayer(),
                cart)) {
            updateDisplayText("Resources bought");
        } else {
            updateDisplayText("Not enough money");
        }

        goToTownScreen();
    }

    public final void sellResource() {
        ResourceBag cart = new ResourceBag();

        cart.add(new Smithore(), oreQuantity.getValue());
        cart.add(new Food(), foodQuantity.getValue());
        cart.add(new Energy(), energyQuantity.getValue());

        if (Main.getTown().getStore().sellResource(Main.getCurrentPlayer(),
                cart)) {
            updateDisplayText("Resources sold");
        } else {
            updateDisplayText("Not enough in inventory");
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

    @FXML public void goToTownScreen() {
        controller.setScreen(Main.TOWN_ID);
        Main.setToolBar(TownController.getToolBar());
        Main.setTimerLabel(TownController.getTimerLabel());
        TownController.getDisplayText().setText(displayText.getText());
        unboldPlayerFont(Main.getTurn().getCurrentPlayer());
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            TownController.updatePlayerMenu(i);
        }
    }

    public final void goToMapScreen() {
        controller.setScreen(Main.MAP_ID);
        Main.setToolBar(MapController.getToolBar());
        Main.setTimerLabel(MapController.getTimerLabel());
        MapController.getDisplayText().setText(displayText.getText());
        unboldPlayerFont(Main.getTurn().getCurrentPlayer());
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

    public static void unboldPlayerFont(int i) {
        ((Label) toolBarInstance.getItems().get(i * 2))
                .setFont(Font.font("System", FontWeight.NORMAL, 12));
    }

    public static void boldPlayerFont(int i) {
        ((Label) toolBarInstance.getItems().get(i * 2))
                .setFont(Font.font("System", FontWeight.BOLD, 12));
    }

    public static ToolBar getToolBar() { return toolBarInstance; }

    public static Label getTimerLabel() { return timerLabelInstance; }

    public static TextArea getDisplayText() { return displayTextInstance; }

}

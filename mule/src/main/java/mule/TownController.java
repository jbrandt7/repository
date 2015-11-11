package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.Canvas;

import mule.model.town.*;
import mule.model.resources.*;

public class TownController implements Initializable, ControlledScreen {

    private ScreensController controller;


    @FXML private Canvas townParent;

    @FXML private MenuBar menuBar;
    private static MenuBar menuBarInstance;

    @FXML private TextArea displayText;
    private static TextArea displayTextInstance;

    @Override public final void initialize(URL url, ResourceBundle rb) {
        Main.setTown(new Town(townParent));
        setupInfoBar();
        setupDisplayText();

        townParent.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent event) {
                    int x = (int) (event.getSceneX());
                    int y = (int) (event.getSceneY());
                    processClick(x, y);
                }
            });
    }

    private void processClick(int x, int y) {
            if (x >= Town.STORE_WIDTH && y >= Town.STORE_HEIGHT * 2) {
                int amount = Main.getTown().getPub().cashOut(Main.getCurrentPlayer());
                updateDisplayText(Main.getCurrentPlayer().getName() + " cashed out " +
                        "for " + amount);
                incrementTurn();
            } else if (x > Town.STORE_WIDTH && y < Town.STORE_HEIGHT * 2) {
                goToStoreScreen();
            }
    }

    private void incrementTurn() {
        if (Main.getTurn().hasNextPlayer()) {
            Main.getTurn().nextPlayer();
        } else if (Main.getTurn().hasNextTurn()) {
            Main.getTurn().nextTurn();
            goToMapScreen();
        }
    }

    private void goToMapScreen() {
        controller.setScreen(Main.MAP_ID);
        Main.setMenuBar(MapController.getMenuBar());
        MapController.getDisplayText().setText(displayText.getText());
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            MapController.updatePlayerMenu(i);
        }
    }

    private void goToStoreScreen() {
        Main.loadScene(Main.STORE_ID, Main.STORE_FILE);
        controller.setScreen(Main.STORE_ID);
        Main.setMenuBar(StoreController.getMenuBar());
        StoreController.getDisplayText().setText(displayText.getText());
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            MapController.updatePlayerMenu(i);
        }
    }

    /**
     * Sets the main screen controller
     * @param screenParent the screen controller
     */
    public final void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
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

    /**
     * Updates the players information in the menubar
     * @param i The rank of the player to update
     */
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

    private void setupDisplayText() {
        displayText.setEditable(false);
        displayTextInstance = displayText;
    }

    private void updateDisplayText(String text) {
        displayText.setText(text + "\n" + displayText.getText());
    }


    /**
     * Gets the menubar located in this screen
     * @return this screen's menubar
     */
    public static MenuBar getMenuBar() { return menuBarInstance; }

    public static TextArea getDisplayText() { return displayTextInstance; }

}

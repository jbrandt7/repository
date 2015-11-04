package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.Canvas;

import mule.model.*;
import mule.model.map.*;
import mule.model.town.*;
import mule.model.resources.*;
import mule.model.player.*;

public class TownController implements Initializable, ControlledScreen {

    private ScreensController controller;


    @FXML private Canvas townParent;

    @FXML private MenuBar menuBar;
    private static MenuBar menuBarInstance;

    @Override public final void initialize(URL url, ResourceBundle rb) {
        Main.setTown(new Town(townParent));
        setupInfoBar();

        townParent.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent event) {
                    int x = (int) (event.getSceneX());
                    int y = (int) (event.getSceneY());
                    processClick(x, y);
                }
            });
    }

    public final void processClick(int x, int y) {
            if (x >= Town.STORE_WIDTH && y >= Town.STORE_HEIGHT * 2) {
                Main.getTown().getPub().cashOut(Main.getCurrentPlayer());
                incrementTurn();
            } else if (x < Town.STORE_WIDTH && y < Town.STORE_HEIGHT * 2) {
                goToStoreScreen();
            }
    }

    public final void incrementTurn() {
        if (Main.getTurn().hasNextPlayer()) {
            Main.getTurn().nextPlayer();
        } else if (Main.getTurn().hasNextTurn()) {
            Main.getTurn().nextTurn();
            goToMapScreen();
        }
    }

    public final void goToMapScreen() {
        controller.setScreen(Main.MAP_ID);

        for (int i = 0; i < Main.getPlayerCount(); i++) {
            MapController.updatePlayerMenu(i);
        }
    }

    public final void goToStoreScreen() {
        Main.loadScene(Main.STORE_ID, Main.STORE_FILE);
        controller.setScreen(Main.STORE_ID);

        for (int i = 0; i < Main.getPlayerCount(); i++) {
            ((Label) StoreController.getInfoBar().getItems().get(i)).setText(Main
                    .getPlayer(i).toString());
        }

        ((Label) Main.getInfoBar().getItems().get(Main.getTurn().getCurrentPlayer()))
                .setFont(Font.font("System", FontWeight.BOLD, 13));
    }


    public final void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    private final void setupInfoBar() {
        menuBarInstance = menuBar;
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            updatePlayerMenu(i);
        }
        for (int i = Main.getPlayerCount(); i < 4; i++) {
            menuBar.getMenus().get(i).setVisible(false);
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


    public static MenuBar getMenuBar() { return menuBarInstance; }

}

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

import mule.model.*;
import mule.model.map.*;
import mule.model.town.*;
import mule.model.resources.*;
import mule.model.player.*;

public class TownController implements Initializable, ControlledScreen {

    ScreensController controller;

    @FXML StackPane root;

    @FXML Group townParent;

    @FXML ToolBar infoBar;
    static ToolBar _infoBar;

    @FXML Label timerLabel, mapText;
    static Label _timerLabel, _mapText;

    @Override public void initialize(URL url, ResourceBundle rb) {
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

    public void processClick(int x, int y) {
            if (x >= 337.5 && y >= 250) {
                Main.getTown().getPub().cashOut(Main.getCurrentPlayer());
                ((Label) infoBar.getItems().get(Main.getTurn()
                        .getCurrentPlayer())).setText(Main.getCurrentPlayer().toString());
                incrementTurn();
            } else if (x < 337.5 && y < 250) {
                goToStoreScreen();
            }
    }

    public void incrementTurn() {
        if (Main.getTurn().hasNextPlayer()) {
            ((Label) Main.getInfoBar().getItems().get(Main.getTurn().getCurrentPlayer()))
                    .setFont(Font.font("System", FontWeight.NORMAL, 13));

            Main.getTurn().nextPlayer();

            ((Label) Main.getInfoBar().getItems().get(Main.getTurn().getCurrentPlayer()))
                    .setFont(Font.font("System", FontWeight.BOLD, 13));
        } else if (Main.getTurn().hasNextTurn()) {
            ((Label) Main.getInfoBar().getItems().get(Main.getTurn().getCurrentPlayer()))
                    .setFont(Font.font("System", FontWeight.NORMAL, 13));

            Main.getTurn().nextTurn();
            goToMapScreen();

            ((Label) Main.getInfoBar().getItems().get(Main.getTurn().getCurrentPlayer()))
                    .setFont(Font.font("System", FontWeight.BOLD, 13));
        }
    }

    public void goToMapScreen() {
        controller.setScreen(Main.mapID);

        Main.setHelperLabel(MapController.getHelperLabel());
        Main.setTimerLabel(MapController.getTimerLabel());
        Main.setInfoBar(MapController.getInfoBar());

        for (int i = 0; i < Main.getPlayerCount(); i++) {
            ((Label) MapController.getInfoBar().getItems().get(i)).setText(Main
                    .getPlayer(i).toString());
        }
    }

    public void goToStoreScreen() {
        Main.loadScene(Main.storeID, Main.storeFile);
        controller.setScreen(Main.storeID);

        Main.setHelperLabel(StoreController.getHelperLabel());
        Main.setTimerLabel(StoreController.getTimerLabel());
        Main.setInfoBar(StoreController.getInfoBar());

        for (int i = 0; i < Main.getPlayerCount(); i++) {
            ((Label) StoreController.getInfoBar().getItems().get(i)).setText(Main
                    .getPlayer(i).toString());
        }

        ((Label) Main.getInfoBar().getItems().get(Main.getTurn().getCurrentPlayer()))
                .setFont(Font.font("System", FontWeight.BOLD, 13));
    }


    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    private void setupInfoBar() {
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            ((Label) infoBar.getItems().get(i)).setText(Main.getPlayer(i) + ": "
                    + Main.getPlayer(i).getMoney());
        }
        for (int i = Main.getPlayerCount(); i < 4; i++) {
            ((Label) infoBar.getItems().get(i)).setOpacity(0.0);
        }
        _mapText = mapText;
        _timerLabel = timerLabel;
        _infoBar = infoBar;
    }

    public static Label getHelperLabel() { return _mapText; }

    public static Label getTimerLabel() { return _timerLabel; }

    public static ToolBar getInfoBar() { return _infoBar; }

}

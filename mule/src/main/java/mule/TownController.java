package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import mule.model.*;
import mule.model.map.*;
import mule.model.town.*;

public class TownController implements Initializable, ControlledScreen {

    ScreensController controller;

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

                    if (x >= 337.5 && y >= 250) {
                        if (Main.getTurn().hasNextPlayer()) {
                            Main.getCurrentPlayer().addMoney(Main.getCurrentPlayer().getTimer().getTime());
                            Main.getCurrentPlayer().updateScore();
                            ((Label) infoBar.getItems().get(Main.getTurn()
                                    .getCurrentPlayer())).setText(Main.getCurrentPlayer()
                                    + ": " + Main.getCurrentPlayer().getMoney());
                            Main.getTurn().nextPlayer();
                        } else if (Main.getTurn().hasNextStage()) {
                            //go to next stage eventually
                        } else if (Main.getTurn().hasNextTurn()) {
                            Main.getCurrentPlayer().addMoney(Main.getCurrentPlayer().getTimer().getTime());
                            Main.getCurrentPlayer().updateScore();
                            ((Label) infoBar.getItems().get(Main.getTurn()
                                    .getCurrentPlayer())).setText(Main.getCurrentPlayer()
                                    + ": " + Main.getCurrentPlayer().getMoney());
                            Main.getTurn().nextTurn();
                            for (int i = 0; i < Main.getPlayerCount(); i++) {
                                Main.getPlayer(i).getTimer().reset();
                            }
                            Main.sortPlayers();
                            goToMapScreen();
                        }
                    } else if (x < 337.5 && y < 250) {
                        //Main.getCurrentPlayer().addMule(new Mule(new Resource(1, 1)));
                        goToStoreScreen();
                    }

                }
            });
    }

    public void goToMapScreen() {
        controller.setScreen(Main.mapID);
        Main.setHelperLabel(MapController.getHelperLabel());
        Main.setTimerLabel(MapController.getTimerLabel());
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            ((Label) MapController.getInfoBar().getItems().get(i)).setText(Main
                    .getPlayer(i) + ": " + Main.getPlayer(i).getMoney());
        }
    }

    public void goToStoreScreen() {
        Main.loadScene(Main.storeID, Main.storeFile);
        controller.setScreen(Main.storeID);
        Main.setHelperLabel(StoreController.getHelperLabel());
        Main.setTimerLabel(StoreController.getTimerLabel());
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            ((Label) StoreController.getInfoBar().getItems().get(i)).setText(Main
                    .getPlayer(i) + ": " + Main.getPlayer(i).getMoney());
        }
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
    }

    public static Label getHelperLabel() { return _mapText; }

    public static Label getTimerLabel() { return _timerLabel; }

    public static ToolBar getInfoBar() { return _infoBar; }

}

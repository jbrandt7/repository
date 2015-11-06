package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.event.*;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import mule.model.*;
import mule.model.map.*;
import mule.model.town.*;
import mule.model.resources.*;
import mule.model.player.*;

public class MapController implements Initializable, ControlledScreen {

    ScreensController controller;

    @FXML private Canvas mapParent;

    @FXML private MenuBar menuBar;
    private static MenuBar menuBarInstance;

    @Override public final void initialize(URL url, ResourceBundle rb) {
        if (Main.getMap() == null) {
            Main.setMap(new Map(mapParent));
        } else {
            Main.getMap().redraw(mapParent);
        }
        setupInfoBar();
        startTimer();

        mapParent.addEventHandler(MouseEvent.MOUSE_CLICKED,
                createLandSelectionHandler());

    }

    public final void goToTownScreen() {
        Main.loadScene(Main.TOWN_ID, Main.TOWN_FILE);
        Main.setMenuBar(TownController.getMenuBar());
        controller.setScreen(Main.TOWN_ID);

        for (int i = 0; i < Main.getPlayerCount(); i++) {
            MapController.updatePlayerMenu(i);
        }
    }

    public final void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    @FXML public final void saveGame() {
        Main.getDBController().saveGame();
    }

    private void incrementTurn() {
        if (Main.getTurn().hasNextPlayer()) {
            Main.getTurn().nextPlayer();
        } else {
            Main.getTurn().nextStage();
            goToTownScreen();
        }
    }

    private void setupInfoBar() {
        menuBarInstance = menuBar;
        Main.setMenuBar(menuBarInstance);
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            updatePlayerMenu(i);        }
        for (int i = Main.getPlayerCount(); i < 4; i++) {
            menuBar.getMenus().get(i).setVisible(false);
        }

    }

    private void startTimer() {
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                if (Main.getCurrentPlayer().getTimer().outOfTime()) {
                    Main.getCurrentPlayer().getTimer().reset();
                    //Main.getHelperLabel().setText(Main.getCurrentPlayer().getName()
                    //        + " ran out of time, " + "skipping to next player");
                    incrementTurn();
                } else {
                    //Main.getTimerLabel().setText("Time: "
                    //        + Main.getCurrentPlayer().getTimer().getTime());
                    Main.getMenuBar().getMenus().get(4).setText("Time: "
                            + Main.getCurrentPlayer().getTimer().getTime());
                    Main.getCurrentPlayer().getTimer().tick();
                }
            }
        };
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), onFinished);
        Main.getTimeline().getKeyFrames().addAll(keyFrame);
        Main.getTimeline().play();
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

    private EventHandler<MouseEvent> createLandSelectionHandler() {
        return new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                int x = (int) ((event.getSceneX()) / 75);
                int y = (int) ((event.getSceneY() - 30) / 75);

                if (isTown(x, y)) {
                    processTownClick();
                } else {
                    Plot selected = Main.getMap().getPlot(x, y);
                    if (selected.hasOwner()) {
                        processLandOwnedClick(selected);
                    } else {
                        processLandNotOwnedClick(selected);
                    }
                }
            }
        };
    }

    private boolean isTown(int x, int y) {
        return x == Map.MAP_WIDTH / 2 && y == Map.MAP_HEIGHT / 2;
    }

    private void processTownClick() {
        if (Main.getTurn().getCurrentStage() == Turn.LAND) {
            //mapText.setText(Main.getCurrentPlayer().getName()
            //        + "passes, " + "no land bought");
            incrementTurn();
        } else if (Main.getTurn().getCurrentStage() == Turn.TOWN) {
            Main.getCurrentPlayer().removeMule();
            //mapText.setText("Mule lost, silly");
            goToTownScreen();
        }
    }

    private void processLandOwnedClick(Plot selected) {
        if (Main.getTurn().getCurrentStage() == Turn.TOWN) {
            if (selected.getOwner().equals(Main.getCurrentPlayer())
                    && selected.notOutfitted()) {
                Mule temp = Main.getCurrentPlayer().removeMule();
                selected.outfit(temp);
                goToTownScreen();
            } else {
                Main.getCurrentPlayer().removeMule();
                //mapText.setText("Mule lost, silly");
                goToTownScreen();
            }
        } else {
            //mapText.setText("Can't buy, already bought!");
            goToTownScreen();
        }
    }

    private void processLandNotOwnedClick(Plot selected) {
        if (Main.getTurn().getCurrentStage() == Turn.TOWN) {
            Main.getCurrentPlayer().removeMule();
            //mapText.setText("Mule lost, silly");
            goToTownScreen();
        } else if (Main.getTurn().getCurrentTurn() > 1) {
            if (selected.buy(Main.getCurrentPlayer())) {
                //mapText.setText(Main.getCurrentPlayer().getName()
                 //       + " bought land");
                incrementTurn();
            } else {
                //mapText.setText("Could not buy land");
            }
        } else {
            Main.getCurrentPlayer().addPlot(selected);
            //mapText.setText(Main.getCurrentPlayer().getName()
            //      + " granted land");
            incrementTurn();
        }
    }


    public static MenuBar getMenuBar() { return menuBarInstance; }
}

package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
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

    @FXML StackPane root;

    @FXML Canvas mapParent;

    @FXML Label mapText, timerLabel;
    static Label _mapText, _timerLabel;

    @FXML ToolBar infoBar;
    static ToolBar _infoBar;

    @Override public void initialize(URL url, ResourceBundle rb) {
        Main.setMap(new Map(mapParent));
        setupInfoBar();
        startTimer();

        mapParent.addEventHandler(MouseEvent.MOUSE_CLICKED,
            createLandSelectionHandler());

    }

    public void goToTownScreen() {
        Main.loadScene(Main.townID, Main.townFile);
        Main.setHelperLabel(TownController.getHelperLabel());
        Main.setTimerLabel(TownController.getTimerLabel());
        Main.setInfoBar(TownController.getInfoBar());
        controller.setScreen(Main.townID);
    }

    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    private void incrementTurn() {
        if (Main.getTurn().hasNextPlayer()) {
            ((Label) Main.getInfoBar().getItems().get(Main.getTurn()
                    .getCurrentPlayer())).setFont(Font
                    .font("System", FontWeight.NORMAL, 13));

            Main.getTurn().nextPlayer();

            ((Label) Main.getInfoBar().getItems().get(Main.getTurn()
                    .getCurrentPlayer())).setFont(Font
                    .font("System", FontWeight.BOLD, 13));
        } else {
            ((Label) Main.getInfoBar().getItems().get(Main.getTurn()
                    .getCurrentPlayer())).setFont(Font
                    .font("System", FontWeight.NORMAL, 13));

            Main.getTurn().nextStage();
            goToTownScreen();

            ((Label) Main.getInfoBar().getItems().get(Main.getTurn()
                    .getCurrentPlayer())).setFont(Font
                    .font("System", FontWeight.BOLD, 13));
        }
    }

    private void setupInfoBar() {
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            ((Label) infoBar.getItems().get(i))
                    .setText(Main.getPlayer(i).toString());
        }
        for (int i = Main.getPlayerCount(); i < 4; i++) {
            ((Label) infoBar.getItems().get(i)).setOpacity(0.0);
        }
        Main.setInfoBar(infoBar);
        Main.setHelperLabel(mapText);
        _mapText = mapText;
        Main.setTimerLabel(timerLabel);
        _timerLabel = timerLabel;
        _infoBar = infoBar;
    }

    private void startTimer() {
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                if (Main.getCurrentPlayer().getTimer().outOfTime()) {
                    Main.getCurrentPlayer().getTimer().reset();
                    Main.getHelperLabel().setText(Main.getCurrentPlayer().getName()
                            + " ran out of time, " + "skipping to next player");
                    incrementTurn();
                } else {
                    Main.getTimerLabel().setText("Time: "
                            + Main.getCurrentPlayer().getTimer().getTime());
                    Main.getCurrentPlayer().getTimer().tick();
                }
            }
        };
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), onFinished);
        Main.getTimeline().getKeyFrames().addAll(keyFrame);
        Main.getTimeline().play();
    }

    private EventHandler<MouseEvent> createLandSelectionHandler() {
        return new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                int x = (int) (event.getSceneX() / 75);
                int y = (int) (event.getSceneY() / 75);

                if (x == Map.MAP_WIDTH / 2 && y == Map.MAP_HEIGHT / 2) {

                    if (Main.getTurn().getCurrentStage() == Turn.LAND) {

                        mapText.setText(Main.getCurrentPlayer().getName()
                                + "passes, " + "no land bought");
                        incrementTurn();

                    } else if (Main.getTurn().getCurrentStage() == Turn.TOWN){
                        Main.getCurrentPlayer().removeMule();
                        mapText.setText("Mule lost, silly");
                        goToTownScreen();
                    }

                } else {

                    Plot selected = Main.getMap().getPlot(x, y);

                    if (selected.hasOwner()) {

                        if (Main.getTurn().getCurrentStage() == Turn.TOWN) {
                            if (selected.getOwner().equals(Main.getCurrentPlayer())
                                    && selected.notOutfitted()) {
                                Mule temp = Main.getCurrentPlayer().removeMule();
                                selected.outfit(temp);
                                goToTownScreen();
                            } else {
                                Main.getCurrentPlayer().removeMule();
                                mapText.setText("Mule lost, silly");
                                goToTownScreen();
                            }
                        } else {
                            mapText.setText("Can't buy, already bought!");
                            goToTownScreen();
                        }

                    } else {

                        if (Main.getTurn().getCurrentStage() == Turn.TOWN) {
                            Main.getCurrentPlayer().removeMule();
                            mapText.setText("Mule lost, silly");
                            goToTownScreen();

                        } else if (Main.getTurn().getCurrentTurn() > 1) {

                            if (selected.buy(Main.getCurrentPlayer())) {

                                mapText.setText(Main.getCurrentPlayer().getName()
                                        + " bought land");
                                ((Label) infoBar.getItems().get(Main
                                        .getTurn().getCurrentPlayer()))
                                        .setText(Main.getCurrentPlayer()
                                        .toString());
                                incrementTurn();

                            } else {

                                mapText.setText("Could not buy land");

                            }
                        } else {

                            Main.getCurrentPlayer().addPlot(selected);
                            mapText.setText(Main.getCurrentPlayer().getName()
                                    + " granted land");
                            incrementTurn();

                        }
                    }
                }
            }
        };
    }

    public static Label getHelperLabel() { return _mapText; }

    public static Label getTimerLabel() { return _timerLabel; }

    public static ToolBar getInfoBar() { return _infoBar; }
}

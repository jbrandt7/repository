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

public class MapController implements Initializable, ControlledScreen {

    ScreensController controller;

    @FXML Group mapParent;

    @FXML Label mapText;

    @FXML ToolBar infoBar;

    @Override public void initialize(URL url, ResourceBundle rb) {
        Main.setMap(new Map(mapParent));
        setupInfoBar();

        mapParent.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent event) {
                        int x = (int) (event.getSceneX() / 75);
                        int y = (int) (event.getSceneY() / 75);

                        if (x == Map.MAP_WIDTH / 2 && y == Map.MAP_HEIGHT /2) {
                            mapText.setText(Main.getCurrentPlayer() + "passes, "
                                    + "no land bought");
                        } else {
                            Plot selected = Main.getMap().getPlot(x, y);
                            if (selected.hasOwner()) {
                                mapText.setText("Can't buy, already bought!");
                            } else {
                                if (Main.getTurn().getCurrentTurn() > 1) {
                                    if (selected.buy(Main.getCurrentPlayer())) {
                                        mapText.setText(Main.getTurn().getCurrentPlayer()
                                                + " bought land");
                                        ((Label) infoBar.getItems().get(Main.getTurn()
                                                .getCurrentPlayer())).setText(Main.getCurrentPlayer()
                                                + ": " + Main.getCurrentPlayer().getMoney());
                                        incrementTurn();
                                    } else {
                                        mapText.setText("Could not buy land");
                                    }
                                } else {
                                    Main.getCurrentPlayer().addPlot(selected);
                                    mapText.setText(Main.getCurrentPlayer()
                                            + " granted land");
                                    incrementTurn();
                                }

                            }
                        }
                    }
                });
    }

    public void goToStoreScreen() {
        controller.setScreen(Main.storeID);
    }

    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    private void incrementTurn() {
        if (Main.getTurn().hasNextPlayer()) {
            Main.getTurn().nextPlayer();
        } else {
            Main.getTurn().nextStage();
            mapText.setText("New Turn");
            goToStoreScreen();
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
    }
}

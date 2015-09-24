package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import mule.model.*;

public class MapController implements Initializable, ControlledScreen {

    ScreensController controller;

    @FXML Group mapParent;

    @Override public void initialize(URL url, ResourceBundle rb) {
        Main.setMap(new Map(mapParent));

        mapParent.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        int x = (int) (event.getSceneX() / 75);
                        int y = (int) (event.getSceneY() / 75);
                        if (x == Map.MAP_WIDTH / 2 && y == Map.MAP_HEIGHT /2) {
                            System.out.println("Can't buy the town!");
                            return;
                        } else {
                            Plot selected = Main.getMap().getPlot(x, y);
                            if (selected.hasOwner()) {
                                System.out.println("Can't buy, already bought!");
                            } else {
                                Main.getCurrentPlayer().addPlot(selected);
                                selected.getRep().setStroke(selected.getRep().getFill());
                                selected.getRep().setFill(Main.getCurrentPlayer().getColor());

                                if (Main.getTurn().hasNextPlayer()) {
                                    Main.getTurn().nextPlayer();
                                } else if (Main.getTurn().hasNextStage()){
                                    Main.getTurn().nextStage();
                                    goToStoreScreen();
                                } else {
                                    Main.getTurn().nextTurn();
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

}

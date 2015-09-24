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
                        if (x == 5 && y == 5) {
                            System.out.println("Selected town");
                            return;
                        }
                        Plot selected = Main.getMap().getPlot(x, y);
                        if (selected.hasOwner()) {
                            System.out.println("Can't buy, already bought!");
                        } else {
                            Main.getCurrentPlayer().addPlot(selected);
                            selected.getRep().setFill(Main.getCurrentPlayer().getColor());
                            Main.nextPlayer();
                        }
                    }
                });
    }

    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

}

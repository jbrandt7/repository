package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.animation.*;
import javafx.event.*;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;

import mule.model.*;
import mule.model.map.*;
import mule.model.town.*;

public class StoreController implements Initializable, ControlledScreen {

    ScreensController controller;

    @FXML ChoiceBox resourceChoiceBox, quantityChoiceBox;

    @FXML Button buyButton, sellButton, foodMule, energyMule, oreMule;

    @FXML Label mapText, timerLabel;
    static Label _mapText, _timerLabel;

    @FXML ToolBar infoBar;
    static ToolBar _infoBar;

    @Override public void initialize(URL location, ResourceBundle resources) {
        setupInfoBar();
        resourceChoiceBox.setItems(FXCollections.observableArrayList(new Resource(1,1),
                    new Resource(2,1), new Resource(3,1)));
        resourceChoiceBox.getSelectionModel().select(0);

        quantityChoiceBox.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
        quantityChoiceBox.getSelectionModel().select(0);

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

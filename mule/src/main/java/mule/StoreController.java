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
import mule.model.resources.*;
import mule.model.player.*;

public class StoreController implements Initializable, ControlledScreen {

    ScreensController controller;

    @FXML ChoiceBox<Resource> resourceChoiceBox;

    @FXML ChoiceBox<Integer> quantityChoiceBox;

    @FXML Button buyButton, sellButton, foodMule, energyMule, oreMule;

    @FXML Label mapText, timerLabel;
    static Label _mapText, _timerLabel;

    @FXML ToolBar infoBar;
    static ToolBar _infoBar;

    @Override public void initialize(URL location, ResourceBundle resources) {
        setupInfoBar();
        resourceChoiceBox.setItems(FXCollections.observableArrayList(new Energy(),
                    new Food(), new Smithore()));
        resourceChoiceBox.getSelectionModel().select(0);

        quantityChoiceBox.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
        quantityChoiceBox.getSelectionModel().select(0);

    }

    public void buyResource() {
        for (int i = 0; i < quantityChoiceBox.getValue(); i++) {
            if (Main.getTown().getStore().buyResource(Main.getCurrentPlayer(),
                    resourceChoiceBox.getValue()))
                mapText.setText(resourceChoiceBox.getValue() + " bought");
            else
                mapText.setText("Not enough money");
        }
        goToTownScreen();
    }

    public void sellResource() {
        for (int i = 0; i < quantityChoiceBox.getValue(); i++) {
            if (Main.getTown().getStore().sellResource(Main.getCurrentPlayer(),
                    resourceChoiceBox.getValue()))
                mapText.setText(resourceChoiceBox.getValue() + " sold");
            else
                mapText.setText("Not enough resources");
        }
        goToTownScreen();
    }

    public void buyFoodMule() {
        if (Main.getTown().getStore().buyMule(Main.getCurrentPlayer(), new Food()))
            goToMapScreen();
    }

    public void buyEnergyMule() {
        if (Main.getTown().getStore().buyMule(Main.getCurrentPlayer(), new Energy()))
            goToMapScreen();
    }

    public void buyOreMule() {
        if (Main.getTown().getStore().buyMule(Main.getCurrentPlayer(), new Energy()))
            goToMapScreen();
    }

    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    public void goToTownScreen() {
        controller.setScreen(Main.townID);
        Main.setHelperLabel(TownController.getHelperLabel());
        Main.setTimerLabel(TownController.getTimerLabel());
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            ((Label) TownController.getInfoBar().getItems().get(i)).setText(Main
                    .getPlayer(i).toString());
        }
    }

    public void goToMapScreen() {
        controller.setScreen(Main.mapID);
        Main.setHelperLabel(MapController.getHelperLabel());
        Main.setTimerLabel(MapController.getTimerLabel());
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            ((Label) MapController.getInfoBar().getItems().get(i)).setText(Main
                    .getPlayer(i).toString());
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
        _mapText = mapText;
        _timerLabel = timerLabel;
        _infoBar = infoBar;
    }

    public static Label getHelperLabel() { return _mapText; }

    public static Label getTimerLabel() { return _timerLabel; }

    public static ToolBar getInfoBar() { return _infoBar; }

}

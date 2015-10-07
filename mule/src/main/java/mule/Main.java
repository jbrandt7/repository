package mule;

import java.util.ArrayList;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.*;
import javafx.animation.*;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.util.Duration;

import mule.model.*;
import mule.model.map.*;
import mule.model.town.*;

public class Main extends Application {

    private static Stage stage;

    private static Player[] players = new Player[4];

    private static int playerCount, currentPlayer, time;

    private static Turn turn;

    private static Map map;

    private static Town town;

    private static ScreensController mainContainer;

    private static Timeline timeline;

    private static ToolBar infoBar;

    private static Label helperLabel;

    private static Label timerLabel;

    public static String configureID = "configure";
    public static String configureFile = "view/GameConfigurationScreen.fxml";
    public static String playerConID = "playerCon";
    public static String playerConFile = "view/PlayerConfigurationScreen.fxml";
    public static String mapID = "map";
    public static String mapFile = "view/MapScreen.fxml";
    public static String storeID = "store";
    public static String storeFile = "view/TownScreen.fxml";

    @Override
    public void start(Stage stage) {
        mainContainer = new ScreensController();
        mainContainer.loadScreen(Main.configureID, Main.configureFile);
        mainContainer.setScreen(Main.configureID);

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        stage.setScene(new Scene(root));
        stage.show();

        timeline.play();
    }

    public static void sortPlayers() {
        int j;
        boolean flag = true;
        Player temp;
        while (flag) {
            flag = false;
            for (j = 0; j < playerCount - 1; j++) {
                if (players[j].hashCode() < players[j + 1].hashCode()) {
                    temp = players[j];
                    players[j] = players[j + 1];
                    players[j + 1] = temp;
                    flag = true;
                }
            }
        }
    }

    public static Stage getStage() { return stage; }

    public static void setMap(Map m) { map = m; }

    public static void setTown(Town t) { town = t; }

    public static Timeline getTimeline() { return timeline; }

    public static Map getMap() { return map; }

    public static Town getTown() { return town; }

    public static int getPlayerCount() { return playerCount; }

    public static Player getPlayer(int i) { return players[i]; }

    public static void setPlayer(int i, Player p) { players[i] = p; }

    public static void setPlayerCount(int number) { playerCount = number; }

    public static Player getCurrentPlayer() { return players[turn.getCurrentPlayer()]; }

    public static void setTurn(Turn t) { turn = t; }

    public static Turn getTurn() { return turn; }

    public static ToolBar getInfoBar() { return infoBar; }

    public static void setInfoBar(ToolBar t) { infoBar = t; }

    public static Label getHelperLabel() { return helperLabel; }

    public static void setHelperLabel(Label l) { helperLabel = l; }

    public static Label getTimerLabel() { return timerLabel; }

    public static void setTimerLabel(Label l) { timerLabel = l; }

    public static void loadScene(String name, String resource) {
        mainContainer.loadScreen(name, resource);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

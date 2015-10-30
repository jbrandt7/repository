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
import mule.model.resources.*;
import mule.model.player.*;

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

    private static MenuBar menuBar;

    private static Label helperLabel;

    private static Label timerLabel;

    private static DatabaseController dbController;

    private static String saveGame;

    public static String startingID = "start";
    public static String startingFile = "view/StartingScreen.fxml";
    public static String loadID = "load";
    public static String loadFile = "view/LoadGameScreen.fxml";
    public static String configureID = "configure";
    public static String configureFile = "view/GameConfigurationScreen.fxml";
    public static String playerConID = "playerCon";
    public static String playerConFile = "view/PlayerConfigurationScreen.fxml";
    public static String mapID = "map";
    public static String mapFile = "view/MapScreen.fxml";
    public static String storeID = "store";
    public static String storeFile = "view/StoreScreen.fxml";
    public static String townID = "town";
    public static String townFile = "view/TownScreen.fxml";

    @Override
    public void start(Stage stage) {
        mainContainer = new ScreensController();
        mainContainer.loadScreen(Main.startingID, Main.startingFile);
        mainContainer.loadScreen(Main.configureID, Main.configureFile);
        mainContainer.setScreen(Main.startingID);

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        dbController = new DatabaseController();

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

    public static String getSaveName() { return saveGame; }

    public static void setSaveName(String s) { saveGame = s; }

    public static void setPlayer(int i, Player p) { players[i] = p; }

    public static void setPlayerCount(int number) { playerCount = number; }

    public static Player getCurrentPlayer() {
        return players[turn.getCurrentPlayer()];
    }

    public static DatabaseController getDBController() { return dbController; }

    public static void setTurn(Turn t) { turn = t; }

    public static Turn getTurn() { return turn; }

    public static MenuBar getMenuBar() { return menuBar; }

    public static void setMenuBar(MenuBar m) { menuBar = m; }

    public static ToolBar getInfoBar() { return infoBar; }

    public static void setInfoBar(ToolBar t) { infoBar = t; }

    public static Label getHelperLabel() { return helperLabel; }

    public static void setHelperLabel(Label l) { helperLabel = l; }

    public static Label getTimerLabel() { return timerLabel; }

    public static void setTimerLabel(Label l) { timerLabel = l; }

    public static boolean loadScene(String name, String resource) {
        return mainContainer.loadScreen(name, resource);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

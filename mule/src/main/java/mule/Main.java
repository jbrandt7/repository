package mule;

import javafx.application.Application;
import javafx.scene.*;
import javafx.animation.*;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.scene.control.*;

import mule.model.*;
import mule.model.map.*;
import mule.model.town.*;
import mule.model.resources.*;
import mule.model.player.*;

public class Main extends Application {

    private static Stage stage;

    private static Player[] players = new Player[Main.MAX_PLAYERS];

    private static int playerCount;

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

    public static final String STARTING_ID = "start";
    public static final String STARTING_FILE = "view/StartingScreen.fxml";
    public static final String LOAD_ID = "load";
    public static final String LOAD_FILE = "view/LoadGameScreen.fxml";
    public static final String CONFIGURE_ID = "configure";
    public static final String CONFIGURE_FILE = "view/GameConfigurationScreen.fxml";
    public static final String PLAYER_CONFIG_ID = "playerCon";
    public static final String PLAYER_CONFIG_FILE = "view/PlayerConfigurationScreen.fxml";
    public static final String MAP_ID = "map";
    public static final String MAP_FILE = "view/MapScreen.fxml";
    public static final String STORE_ID = "store";
    public static final String STORE_FILE = "view/StoreScreen.fxml";
    public static final String TOWN_ID = "town";
    public static final String TOWN_FILE = "view/TownScreen.fxml";

    public static final int MAX_PLAYERS = 4;
    public static final int FONT_SIZE = 13;
    public static final String FONT = "System";

    @Override
    public final void start(Stage primaryStage) {
        mainContainer = new ScreensController();
        mainContainer.loadScreen(Main.STARTING_ID, Main.STARTING_FILE);
        mainContainer.loadScreen(Main.CONFIGURE_ID, Main.CONFIGURE_FILE);
        mainContainer.setScreen(Main.STARTING_ID);

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        dbController = new DatabaseController();

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

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

    public static Stage getStage() {
        return stage;
    }

    public static void setMap(Map m) {
        map = m;
    }

    public static void setTown(Town t) {
        town = t;
    }

    public static Timeline getTimeline() {
        return timeline;
    }

    public static Map getMap() {
        return map;
    }

    public static Town getTown() {
        return town;
    }

    public static int getPlayerCount() {
        return playerCount;
    }

    public static Player getPlayer(int i) {
        return players[i];
    }

    public static String getSaveName() {
        return saveGame;
    }

    public static void setSaveName(String s) {
        saveGame = s;
    }

    public static void setPlayer(int i, Player p) {
        players[i] = p;
    }

    public static void setPlayerCount(int number) {
        playerCount = number;
    }

    public static Player getCurrentPlayer() {
        return players[turn.getCurrentPlayer()];
    }

    public static DatabaseController getDBController() {
        return dbController;
    }

    public static void setTurn(Turn t) {
        turn = t;
    }

    public static Turn getTurn() {
        return turn;
    }

    public static MenuBar getMenuBar() {
        return menuBar;
    }

    public static void setMenuBar(MenuBar m) {
        menuBar = m;
    }

    public static ToolBar getInfoBar() {
        return infoBar;
    }

    public static void setInfoBar(ToolBar t) {
        infoBar = t;
    }

    public static Label getHelperLabel() {
        return helperLabel;
    }

    public static void setHelperLabel(Label l) {
        helperLabel = l;
    }

    public static Label getTimerLabel() {
        return timerLabel;
    }

    public static void setTimerLabel(Label l) {
        timerLabel = l;
    }

    public static boolean loadScene(String name, String resource) {
        return mainContainer.loadScreen(name, resource);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

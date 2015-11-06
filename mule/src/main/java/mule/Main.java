package mule;

import javafx.application.Application;
import javafx.scene.*;
import javafx.animation.*;
import javafx.stage.Stage;
import javafx.scene.control.*;

import mule.model.*;
import mule.model.map.*;
import mule.model.town.*;
import mule.model.player.*;

/**
 * The Main class actually launching the game and handling all the persistent
 * game variables
 */
public class Main extends Application {

    private static Stage stage;

    private static final Player[] players = new Player[Main.MAX_PLAYERS];

    private static int playerCount;

    private static Turn turn;

    private static Map map;

    private static Town town;

    private static ScreensController mainContainer;

    private static Timeline timeline;

    private static MenuBar menuBar;

    private static DatabaseController dbController;

    private static String saveGame;

    public static final String STARTING_ID = "start";
    private static final String STARTING_FILE = "view/StartingScreen.fxml";
    public static final String LOAD_ID = "load";
    public static final String LOAD_FILE = "view/LoadGameScreen.fxml";
    public static final String CONFIGURE_ID = "configure";
    private static final String CONFIGURE_FILE = "view/GameConfigurationScreen.fxml";
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

    /**
     * Sorts the players based on their score
     */
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

    /**
     * Gets the current stage
     * @return the main stage of the entire application
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * Initializes the main map to be used throughout the game
     * @param m The map to be used
     */
    public static void setMap(Map m) {
        map = m;
    }

    /**
     * Initializes the main town to be used throughout the game
     * @param t The town to be used
     */
    public static void setTown(Town t) {
        town = t;
    }

    /**
     * Gets the main timeline
     * @return the games timeline
     */
    public static Timeline getTimeline() {
        return timeline;
    }

    /**
     * Gets the main map
     * @return the games map
     */
    public static Map getMap() {
        return map;
    }

    /**
     * Gets the main town
     * @return the games town
     */
    public static Town getTown() {
        return town;
    }

    /**
     * Gets the total number of players playing the game
     * @return the player count
     */
    public static int getPlayerCount() {
        return playerCount;
    }

    /**
     * Returns the player based on their ranking with 1 being the best
     * @param i
     * @return the player at rank i
     */
    public static Player getPlayer(int i) {
        return players[i];
    }

    /**
     * Gets the name being used to save the game in the database
     * @return the name of the current save
     */
    public static String getSaveName() {
        return saveGame;
    }

    /**
     * Sets the name being used to save the game in the database
     * @param s the name to be the current save
     */
    public static void setSaveName(String s) {
        saveGame = s;
    }

    /**
     * Sets the player to be at rank i
     * @param i the rank
     * @param p the player
     */
    public static void setPlayer(int i, Player p) {
        players[i] = p;
    }

    /**
     * Sets the total_number of players in the game
     * @param number the number of playesrs
     */
    public static void setPlayerCount(int number) {
        playerCount = number;
    }

    /**
     * Gets the player who turn it currently is
     * @return the current player
     */
    public static Player getCurrentPlayer() {
        return players[turn.getCurrentPlayer()];
    }

    /**
     * Gets the database controller used to load, save the game
     * @return the controller for the database
     */
    public static DatabaseController getDBController() {
        return dbController;
    }

    /**
     * Sets the main turn handler for the game
     * @param t the turn to be used
     */
    public static void setTurn(Turn t) {
        turn = t;
    }

    /**
     * Gets the main turn handling rounds, turns, and stages
     * @return the main turn
     */
    public static Turn getTurn() {
        return turn;
    }

    /**
     * Gets the menubar currently being used in the application which changes throughout the game
     * @return the games menubar
     */
    public static MenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * Sets the games menubar
     * @param m the menubar to be used
     */
    public static void setMenuBar(MenuBar m) {
        menuBar = m;
    }

    /**
     * Loads a screen to show to the user
     * @param name The id of the screen in SceneController
     * @param resource The fxml file of the screen
     * @return Returns whether or not the scene was successfully loaded
     */
    public static boolean loadScene(String name, String resource) {
        return mainContainer.loadScreen(name, resource);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

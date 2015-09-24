package mule;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;

import mule.model.*;

public class Main extends Application {

    private static Stage stage;

    private static Player[] players = new Player[4];

    private static int playerCount, currentPlayer;

    private static Turn turn;

    private static Map map;

    private static Town town;

    private static ScreensController mainContainer;

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
        mainContainer.loadScreen(Main.mapID, Main.mapFile);
        mainContainer.loadScreen(Main.storeID, Main.storeFile);
        mainContainer.setScreen(Main.configureID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    public static Map getMap() {
        return map;
    }

    public static int getPlayerCount() {
        return playerCount;
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

    public static void setTurn(Turn t) {
        turn = t;
    }

    public static Turn getTurn() {
        return turn;
    }

    public static void loadScene(String name, String resource) {
        mainContainer.loadScreen(name, resource);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

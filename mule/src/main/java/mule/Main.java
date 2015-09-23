package mule;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;

import mule.model.*;

public class Main extends Application {

    private static Stage stage;
    private static Player[] players;
    private static int playerCount;
    private static Map map;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("M4");

        this.players = new Player[4];

        initRootLayout();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/mule/view/StartingScreen.fxml"));
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setMap(Map m) {
        map = m;
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

    public static void changeScene(Scene scene, String title) {
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

}

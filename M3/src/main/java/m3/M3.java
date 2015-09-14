package m3;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;

public class M3 extends Application {

    private static Stage stage;
    private StackPane startingLayout, configLayout;
    private Scene startingScene, configScene;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("M3");

        initRootLayout();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(M3.class.getResource("view/StartingScreen.fxml"));
            startingLayout = (StackPane) loader.load();

            loader = new FXMLLoader();
            loader.setLocation(M3.class.getResource("view/GameConfigurationScreen.fxml"));
            configLayout = (StackPane) loader.load();

            startingScene = new Scene(startingLayout);
            configScene = new Scene(configLayout);

            stage.setScene(startingScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Stage getStage() {
        return stage;
    }

    public void loadConfigScreen() {
        stage.setScene(configScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package m3;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;

public class M3 extends Application {

    private Stage stage;
    private StackPane rootLayout;

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

            rootLayout = (StackPane) loader.load();

            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

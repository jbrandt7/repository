package mule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.animation.*;
import javafx.scene.shape.*;
import javafx.event.*;
import javafx.util.Duration;

public class InstructionsController implements Initializable, ControlledScreen {

    private ScreensController controller;

    @FXML private VBox instructionsBox;

    @FXML private ImageView player;

    private int instructionsCount;

    @Override public final void initialize(URL url, ResourceBundle rb) {
        instructionsCount = instructionsBox.getChildren().size();
        startInstructionAnimation();
    }

    public final void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    private void startInstructionAnimation() {
        Path path = new Path();
        path.getElements().add(new MoveTo(-20,40));
        path.getElements().add(new HLineTo(380));
        path.getElements().add(new VLineTo(200));

        PathTransition pathTransition = new PathTransition(Duration.millis(12000), path, player);

        SequentialTransition seqTransition = new SequentialTransition();

        PauseTransition pause = new PauseTransition(Duration.millis(1000));

        for (int i = 0; i < instructionsCount; i++) {
            FadeTransition fade = new FadeTransition(Duration.millis(1000), getInstruction(i));
            fade.setFromValue(0.0);
            fade.setToValue(1.0);
            seqTransition.getChildren().addAll(fade, new PauseTransition(Duration.millis(3000)));
        }

        seqTransition.setOnFinished(e -> goToMapScreen());
        seqTransition.play();
        pathTransition.play();


    }

    private Label getInstruction(int i) {
        return ((Label) instructionsBox.getChildren().get(i));
    }

    private void goToMapScreen() {
        Main.loadScene(Main.MAP_ID, Main.MAP_FILE);
        controller.setScreen(Main.MAP_ID);
    }

}

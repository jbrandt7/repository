package mule;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ScreensController extends StackPane {

    private static final int TRANSITION_DURATION = 300;

    private Map<String, Node> screens = new HashMap<>();

    public ScreensController() {
        super();
    }

    private void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    public final Node getScreen(String name) {
        return screens.get(name);
    }

    public final boolean loadScreen(String name, String resource) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource(resource));
            Parent loadScreen = (Parent) loader.load();
            ControlledScreen myScreenController = ((ControlledScreen) loader
                    .getController());
            myScreenController.setScreenParent(this);
            addScreen(name, loadScreen);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean setScreen(final String name) {
        if (screens.get(name) != null) {
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(TRANSITION_DURATION),
                                new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent t) {
                                getChildren().remove(0);
                                getChildren().add(0, screens.get(name));
                                Timeline fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO,
                                        new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration (TRANSITION_DURATION),
                                        new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0)));
                        fade.play();
            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(name));
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(TRANSITION_DURATION * 6),
                                new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            return false;
        }
    }

    public final boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            return false;
        }
        return true;
    }
}

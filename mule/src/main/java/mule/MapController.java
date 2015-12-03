package mule;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.*;
import javafx.event.*;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import mule.model.*;
import mule.model.map.*;
import mule.model.resources.*;

/**
 * Controlls the map screen, handling plot purchases, mule placement, etc
 */
public class MapController implements Initializable, ControlledScreen {

    private ScreensController controller;

    @FXML private Canvas mapParent;

    @FXML private ToolBar toolBar;
    private static ToolBar toolBarInstance;

    @FXML private Label timerLabel;
    private static Label timerLabelInstance;

    @FXML private TextArea displayText;
    private static TextArea displayTextInstance;

    @FXML private Rectangle selectionRect;

    @FXML private ImageView muleImage;
    private static ImageView muleImageInstance;

    @Override public final void initialize(URL url, ResourceBundle rb) {
        if (Main.getMap() == null) {
            String type = Main.getMapType();
            switch (type) {
                case "Default":
                    Main.setMap(new DefaultMap(mapParent));
                    break;
                case "River":
                    Main.setMap(new RiverMap(mapParent));
                    break;
                case "Desert":
                    Main.setMap(new DesertMap(mapParent));
                    break;
                case "Random":
                    Main.setMap(new RandomMap(mapParent));
                    break;
            }
        } else {
            Main.getMap().redraw(mapParent);
        }
        setupInfoBar();
        setupDisplayText();
        startTimer();

        selectionRect.setWidth(75);
        selectionRect.setHeight(75);
        selectionRect.setFill(Main.getCurrentPlayer().getColor());

        selectionRect.addEventHandler(MouseEvent.MOUSE_PRESSED,
                e -> createLandSelectionHandler(e));

        muleImage.addEventHandler(MouseEvent.MOUSE_PRESSED,
                e -> createLandSelectionHandler(e));

        mapParent.addEventHandler(MouseEvent.MOUSE_MOVED,
                e -> createLandOutlineHandler(e));

        muleImageInstance = muleImage;
    }

    /**
     * Shows the town to the user
     */
    private void goToTownScreen() {
        Main.loadScene(Main.TOWN_ID, Main.TOWN_FILE);
        controller.setScreen(Main.TOWN_ID);
        Main.setToolBar(TownController.getToolBar());
        Main.setTimerLabel(TownController.getTimerLabel());

        for (int i = 0; i < Main.getPlayerCount(); i++) {
            TownController.updatePlayerMenu(i);
        }

        TownController.boldPlayerFont(Main.getTurn().getCurrentPlayer());
    }

    /**
     * Sets the main screen controller
     * @param screenParent the screen controller
     */
    public final void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    /**
     * Attempts to save the game using the Database Controller
     */
    @FXML public final void saveGame() {
        Main.getDBController().saveGame();
    }

    private void incrementTurn() {
        if (Main.getTurn().hasNextPlayer()) {
            unboldPlayerFont(Main.getTurn().getCurrentPlayer());
            Main.getTurn().nextPlayer();
            boldPlayerFont(Main.getTurn().getCurrentPlayer());
            selectionRect.setFill(Main.getCurrentPlayer().getColor());
        } else {
            unboldPlayerFont(Main.getTurn().getCurrentPlayer());
            Main.getTurn().nextStage();
            goToTownScreen();
        }
    }

    public static void unboldPlayerFont(int i) {
        ((Label) toolBarInstance.getItems().get(i * 2))
                .setFont(Font.font("System", FontWeight.NORMAL, 12));
    }

    public static void boldPlayerFont(int i) {
        ((Label) toolBarInstance.getItems().get(i * 2))
                .setFont(Font.font("System", FontWeight.BOLD, 12));
    }

    private void setupInfoBar() {
        toolBarInstance = toolBar;
        Main.setToolBar(toolBarInstance);
        timerLabelInstance = timerLabel;
        Main.setTimerLabel(timerLabelInstance);

        for (int i = 0; i < Main.getPlayerCount(); i++) {
            updatePlayerMenu(i);        }
        for (int i = Main.getPlayerCount(); i < 4; i++) {
            toolBarInstance.getItems().get(i * 2).setVisible(false);
            toolBarInstance.getItems().get(i * 2 + 1).setVisible(false);
        }

        boldPlayerFont(Main.getTurn().getCurrentPlayer());

    }

    private void setupDisplayText() {
        displayText.setEditable(false);
        displayText.setText("Welcome to M.U.L.E! Select a plot " +
                "of land to continue or select the town to pass.\n");
        displayTextInstance = displayText;
    }

    private void startTimer() {
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                if (Main.getCurrentPlayer().getTimer().outOfTime()) {
                    Main.getCurrentPlayer().getTimer().reset();
                    updateDisplayText(Main.getCurrentPlayer().getName() +
                            " ran out of time, skipping to next player");
                    incrementTurn();
                } else {
                    Main.getTimerLabel().setText(
                            String.valueOf(Main.getCurrentPlayer().getTimer().getTime()));
                    Main.getCurrentPlayer().getTimer().tick();
                }
            }
        };
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), onFinished);
        Main.getTimeline().getKeyFrames().addAll(keyFrame);
        Main.getTimeline().play();
    }

    /**
     * Updates the players information in the menubar
     * @param i The rank of the player to update
     */
    public static void updatePlayerMenu(int i) {
        ((Label) (toolBarInstance.getItems().get(i * 2))).setText(
                Main.getPlayer(i).getName() + "\n" +
                "M: " + Main.getPlayer(i).getMoney() + "\n" +
                "E: " + Main.getPlayer(i).getResource(new Energy()) + "\n" +
                "F: " + Main.getPlayer(i).getResource(new Food()) + "\n" +
                "S: " + Main.getPlayer(i).getResource(new Smithore()));
    }

    private void createLandSelectionHandler(MouseEvent event) {
        int x = (int) ((event.getSceneX()) / 75);
        int y = (int) ((event.getSceneY()) / 75);

        selectionRect.setTranslateX(-75);
        selectionRect.setTranslateY(-75);

        muleImage.setTranslateX(-75);
        muleImage.setTranslateY(-75);

        if (isTown(x, y)) {
            processTownClick();
        } else {
            Plot selected = Main.getMap().getPlot(x, y);
            if (selected.hasOwner()) {
                processLandOwnedClick(selected);
            } else {
                processLandNotOwnedClick(selected);
            }
        }
    }

    private void createLandOutlineHandler(MouseEvent event) {
        int x = (int) ((event.getX()) / 75);
        int y = (int) ((event.getY()) / 75);

        if (Main.getTurn().getCurrentStage() == Turn.LAND) {
            selectionRect.setTranslateX(x * 75);
            selectionRect.setTranslateY(y * 75);
        } else {
            muleImage.setImage(new Image("mule/view/mule_energy.png"));
            muleImage.setTranslateX(x * 75);
            muleImage.setTranslateY(y * 75);
        }
    }

    private boolean isTown(int x, int y) {
        return x == Map.MAP_WIDTH / 2 && y == Map.MAP_HEIGHT / 2;
    }

    private void processTownClick() {
        if (Main.getTurn().getCurrentStage() == Turn.LAND) {
            updateDisplayText(Main.getCurrentPlayer().getName() +
                    " passes, no land purchased.");
            incrementTurn();
        } else if (Main.getTurn().getCurrentStage() == Turn.TOWN) {
            Main.getCurrentPlayer().removeMule();
            updateDisplayText("Mule's can't go on the town, " +
                    Main.getCurrentPlayer().getName() +
                    ". Your mule was lost.");
            goToTownScreen();
        }
    }

    private void processLandOwnedClick(Plot selected) {
        if (Main.getTurn().getCurrentStage() == Turn.TOWN) {
            if (selected.getOwner().equals(Main.getCurrentPlayer())
                    && !selected.outfitted()) {
                Mule temp = Main.getCurrentPlayer().removeMule();
                selected.outfit(temp);
                goToTownScreen();
            } else {
                Main.getCurrentPlayer().removeMule();
                updateDisplayText("You do not own that piece of land. " +
                        "Your mule was lost.");
                goToTownScreen();
            }
        } else {
            updateDisplayText("That land was already purchased!");
            goToTownScreen();
        }
    }

    private void processLandNotOwnedClick(Plot selected) {
        if (Main.getTurn().getCurrentStage() == Turn.TOWN) {
            Main.getCurrentPlayer().removeMule();
            updateDisplayText("You can't place a mule on land " +
                    "you don't own. Your mule was lost.");
            goToTownScreen();
        } else if (Main.getTurn().getCurrentTurn() > 1) {
            if (selected.buy(Main.getCurrentPlayer())) {
                updateDisplayText(Main.getCurrentPlayer().getName() +
                        " bought a plot of land.");
                incrementTurn();
            } else {
                updateDisplayText("You do not have enough funds to " +
                        "buy this plot of land.");
            }
        } else {
            Main.getCurrentPlayer().addPlot(selected);
            updateDisplayText(Main.getCurrentPlayer().getName() +
                    " granted a plot of land");
            incrementTurn();
        }
    }

    /**
     * Updates the text displayed to the user
     * @param text the string to add to the display
     */
    public static void updateDisplayText(String text) {
        displayTextInstance.setText(text + "\n" + displayTextInstance.getText());
    }

    public static void updateMuleImagePos(int x, int y) {
        muleImageInstance.setTranslateX(x);
        muleImageInstance.setTranslateY(y);
    }

    public static TextArea getDisplayText() { return displayTextInstance; }

    public static Label getTimerLabel() { return timerLabelInstance; }

    public static ToolBar getToolBar() { return toolBarInstance; }

}

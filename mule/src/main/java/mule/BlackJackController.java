package mule;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;
import mule.model.blackjack.*;

/**
 * Created by RyanC on 11/23/15.
 */
public class BlackJackController implements Initializable, ControlledScreen {

    ScreensController controller;

    @FXML private Label winningLabel;

    @FXML private StackPane parent;

    @FXML private ImageView d1, d2, p1, p2;

    private List<ImageView> dealerViews, playerViews;

    private Hand dealer, player;
    private Deck deck;

    @Override public void initialize(URL url, ResourceBundle rb) {
        deck = new Deck();
        dealerViews = new ArrayList<>();
        playerViews = new ArrayList<>();

        dealerViews.add(d1);
        dealerViews.add(d2);

        dealer = deck.createHand();

        for (Card c : dealer.getCards()) {
            ImageView current = dealerViews.remove(0);
            current.setImage(new Image(CardToFile.getFileName(c)));
            System.out.println(c);
        }

        player = deck.createHand();

        playerViews.add(p1);
        playerViews.add(p2);

        for (Card c : player.getCards()) {
            ImageView current = playerViews.remove(0);
            current.setImage(new Image(CardToFile.getFileName(c)));
        }
        winningLabel.setOpacity(0.0);

        /*
        player.valueProperty().addListener((obs, old, newValue) -> {
            if (newValue.intValue() >= 21) {
                endGame();
            }
        });

        dealer.valueProperty().addListener((obs, old, newValue) -> {
            if (newValue.intValue() >= 21) {
                endGame();
            }
        });
        newGame();
        */
    }

    @FXML
    public void hit() {
        player.takeCard(deck.drawCard());
    }

    @FXML
    public void stay() {
        while (dealer.getValue() < 17)
            dealer.takeCard(deck.drawCard());

        endGame();
    }

    private void endGame() {
        int dealerValue = dealer.getValue();
        int playerValue = player.getValue();
        String winner = "Exceptional case: d: " + dealerValue + " p: " + playerValue;

        // the order of checking is important
        int amount = Main.getTown().getPub().cashOut(Main.getCurrentPlayer());
        if (dealerValue == 21 || playerValue > 21 || dealerValue == playerValue
                || (dealerValue < 21 && dealerValue > playerValue)) {
            winner = "DEALER";
            updateWinningLabel("You lost, but got " + amount);
        }
        else if (playerValue == 21 || dealerValue > 21 || playerValue > dealerValue) {
            winner = "PLAYER";
            amount *= 2;
            updateWinningLabel("You won, and got " + amount);
        }
        //If player is last one in game then it should go to Map screen.  If there's a player after it should go to Town Screen
        incrementTurn();

    }

    private void newGame() {
        deck.refill();
        dealer = deck.createHand();
        player = deck.createHand();
    }

    @FXML public void incrementTurn() {
        if (Main.getTurn().hasNextPlayer()) {
            Main.getTurn().nextPlayer();
            goToTownScreen();
        } else if (Main.getTurn().hasNextTurn()) {
            Main.getTurn().nextTurn();
            goToMapScreen();
        }
    }

    private void updateWinningLabel(String message) {
        winningLabel.setText(message);
        winningLabel.setOpacity(1.0);
    }


    public final void goToTownScreen() {
        controller.setScreen(Main.TOWN_ID);
    }

    private final void goToMapScreen() {
        controller.setScreen(Main.MAP_ID);
        Main.setToolBar(MapController.getToolBar());
        Main.setTimerLabel(MapController.getTimerLabel());
        MapController.getDisplayText().setText(TownController.getDisplayText().getText());
        MapController.boldPlayerFont(Main.getTurn().getCurrentPlayer());
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            MapController.updatePlayerMenu(i);
        }
    }

    public final void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
}

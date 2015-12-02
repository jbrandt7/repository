package mule;

import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;
import mule.model.blackjack.*;

/**
 * Created by RyanC on 11/23/15.
 */
public class BlackJackController implements ControlledScreen {

    ScreensController controller;

    private Hand dealer, player;
    private Deck deck;

    @Override public void initialize(URL url, ResourceBundle rb) {
        deck = new Deck();
        dealer = new Hand(dealerCards.getChildren());
        player = new Hand(playerCards.getChildren());

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
    }

    @FXML


    @FXML
    public void hit() {
        player.takeCard(deck.drawCard());
    }

    @FXML
    public void stay() {
        while (dealer.valueProperty().get() < 17)
            dealer.takeCard(deck.drawCard());

        endGame();
    }

    private void endGame() {
        int dealerValue = dealer.valueProperty().get();
        int playerValue = player.valueProperty().get();
        String winner = "Exceptional case: d: " + dealerValue + " p: " + playerValue;

        // the order of checking is important
        if (dealerValue == 21 || playerValue > 21 || dealerValue == playerValue
                || (dealerValue < 21 && dealerValue > playerValue)) {
            winner = "DEALER";
        }
        else if (playerValue == 21 || dealerValue > 21 || playerValue > dealerValue) {
            winner = "PLAYER";
        }
        //If player is last one in game then it should go to Map screen.  If there's a player after it should go to Town Screen
        goToTownScreen();

    }

    private void newGame() {
        deck.refill();

        dealer.reset();
        player.reset();

        dealer.takeCard(deck.drawCard());
        dealer.takeCard(deck.drawCard());
        player.takeCard(deck.drawCard());
        player.takeCard(deck.drawCard());
    }


    private final void goToTownScreen() {

    }

    private final void goToMapScreen() {
        controller.setScreen(Main.CONFIGURE_ID);
    }

    public final void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
}

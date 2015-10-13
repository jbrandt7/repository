package mule.model;

import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.animation.KeyValue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import mule.*;

public class Turn {

    public static final int LAND = 0;
    public static final int TOWN = 1;

    private static int TOTAL_PLAYERS;
    private final static int TOTAL_TURNS = 12;
    private final static int TOTAL_STAGES = 2;
    private final static Integer TURN_TIME = 60;

    private int currentPlayer, currentStage, currentTurn;

    public Turn(int players) {
        TOTAL_PLAYERS = players;
        currentPlayer = 0;
        currentStage = 0;
        currentTurn = 0;
    }

    public boolean hasNextPlayer() {
        return currentPlayer < TOTAL_PLAYERS - 1;
    }

    public void nextPlayer() {
        if (hasNextPlayer())
            currentPlayer++;
    }

    public boolean hasNextStage() {
        return currentStage < TOTAL_STAGES - 1;
    }

    public void nextStage() {
        if (hasNextStage())
            currentStage++;
        else
            currentStage = 0;

        currentPlayer = 0;
    }

    public boolean hasNextTurn() {
        return currentTurn < TOTAL_TURNS - 1;
    }

    public void nextTurn() {
        if (hasNextTurn())
            currentTurn++;

        currentPlayer = 0;
        currentStage = 0;

        for (int i = 0; i < Main.getPlayerCount(); i++) {
            Main.getPlayer(i).getTimer().reset();
            Main.getPlayer(i).produce();
        }

        Main.sortPlayers();

    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getCurrentStage() {
        return currentStage;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

}

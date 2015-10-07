package mule.model;

public class Turn {

    public static final int LAND = 0;
    public static final int TOWN = 1;

    private static int TOTAL_PLAYERS;
    private final static int TOTAL_TURNS = 12;
    private final static int TOTAL_STAGES = 2;

    private int currentPlayer, currentStage, currentTurn;

    public Turn(int players) {
        TOTAL_PLAYERS = players;
        currentPlayer = 0;
        currentStage = 0;
        currentTurn = 0;
    }

    public void next() {
        nextPlayer();
    }

    public boolean hasNextPlayer() {
        return currentPlayer < TOTAL_PLAYERS - 1;
    }

    public void nextPlayer() {
        if (hasNextPlayer()) {
            currentPlayer++;
        }
    }

    public boolean hasNextStage() {
        return currentStage < TOTAL_STAGES - 1;
    }

    public void nextStage() {
        if (hasNextStage()) {
            currentStage++;
        } else {
            currentStage = 0;
        }
        currentPlayer = 0;
    }

    public boolean hasNextTurn() {
        return currentTurn < TOTAL_TURNS - 1;
    }

    public void nextTurn() {
        if (hasNextTurn()) {
            currentTurn++;
        }

        currentPlayer = 0;
        currentStage = 0;
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

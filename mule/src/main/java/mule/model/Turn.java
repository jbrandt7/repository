package mule.model;

public class Turn {
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
            currentPlayer = 0;
        }
        currentPlayer++;
    }

    public boolean hasNextStage() {
        return currentStage < TOTAL_STAGES - 1;
    }

    public void nextStage() {
        if (hasNextStage()) {
            currentStage = 0;
        }
        currentStage++;
    }

    public boolean hasNextTurn() {
        return currentTurn < TOTAL_TURNS - 1;
    }

    public void nextTurn() {
        if (hasNextTurn()) {
            return;
        }
        currentTurn++;
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

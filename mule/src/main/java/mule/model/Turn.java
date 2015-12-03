package mule.model;

import mule.*;
import mule.model.random_events.RandomEventGenerator;
import mule.model.random_events.EventTypes;
import mule.model.random_events.EffectType;

public class Turn implements java.io.Serializable {

    private static final long serialVersionUID = 42L;

    public static final int LAND = 0;
    public static final int TOWN = 1;

    private static final int TOTAL_TURNS = 12;
    private static final int TOTAL_STAGES = 2;

    private static final int STARTING_M = 25;
    private static final int PHASE1_M = 50;
    private static final int PHASE2_M = 75;
    private static final int FINAL_M = 100;

    private int totalPlayers, currentPlayer, currentStage, currentTurn, m;

    public Turn(int players) {
        totalPlayers = players;
        currentPlayer = 0;
        currentStage = 0;
        currentTurn = 0;
        m = STARTING_M;
    }

    public final boolean hasNextPlayer() {
        return currentPlayer < totalPlayers - 1;
    }

    public final void nextPlayer() {
        if (hasNextPlayer()) {
			currentPlayer++;
		}
    }

    public final boolean hasNextStage() {
        return currentStage < TOTAL_STAGES - 1;
    }

    public final void nextStage() {
        if (hasNextStage()) {
			currentStage++;
		} else {
			currentStage = 0;
		}

        currentPlayer = 0;
    }

    public final boolean hasNextTurn() {
        return currentTurn < TOTAL_TURNS - 1;
    }

    public final void updateM() {
        if (currentTurn == 12) {
            m = FINAL_M;
        } else if (currentTurn > 7) {
            m = PHASE2_M;
        } else if (currentTurn > 3) {
            m = PHASE1_M;
        } else {
            m = STARTING_M;
        }
    }
    public final boolean hasRandomEvent() {
        int randomNum = (int)((Math.random()*100) + 1);
        return true;
    }
    public final void nextTurn() {
        if (hasNextTurn()) {
			currentTurn++;
		}

        updateM();
        currentPlayer = 0;
        currentStage = 0;

        Main.sortPlayers();
        Main.getDBController().saveGame();
        //gloal random event
        if (hasRandomEvent()) {
            RandomEventGenerator eventGenerator = new RandomEventGenerator(EventTypes.BAD, Main.getPlayer(0), EffectType.GLOBAL);
            for (int i = 0; i < Main.getPlayerCount(); i++) {
                eventGenerator.updatePlayer(Main.getPlayer(i));
                eventGenerator.generateAction();
            }
            if (eventGenerator != null)
                TownController.updateDisplayText(eventGenerator.getEvent().getMessage());
        }
        for (int i = 0; i < Main.getPlayerCount(); i++) {
            Main.getPlayer(i).getTimer().reset();
            Main.getPlayer(i).produce();
            if (hasRandomEvent()) {
                RandomEventGenerator eventGenerator;
                if (i != Main.getPlayerCount() - 1) {
                    eventGenerator = new RandomEventGenerator(EventTypes.BAD, Main.getPlayer(i), EffectType.LOCAL);
                    eventGenerator.generateAction();

                } else {
                    eventGenerator = new RandomEventGenerator(EventTypes.GOOD, Main.getPlayer(i), EffectType.LOCAL);
                    eventGenerator.generateAction();
                }
                TownController.updateDisplayText(eventGenerator.getEvent().getMessage());
            }
        }
    }

    public final int getCurrentPlayer() {
        return currentPlayer;
    }

    public final int getCurrentStage() {
        return currentStage;
    }

    public final int getCurrentTurn() {
        return currentTurn;
    }

    public final int getM() {
        return m;
    }


}

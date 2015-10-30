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
import mule.model.resources.*;

public class Turn implements java.io.Serializable {

    private static final long serialVersionUID = 42L;

    public static final int LAND = 0;
    public static final int TOWN = 1;

    private final static int TOTAL_TURNS = 12;
    private final static int TOTAL_STAGES = 2;
    private final static Integer TURN_TIME = 60;

    private int total_players, currentPlayer, currentStage, currentTurn, m;

    public Turn(int players) {
        total_players = players;
        currentPlayer = 0;
        currentStage = 0;
        currentTurn = 0;
	    m = 25;
    }

    public boolean hasNextPlayer() {
        return currentPlayer < total_players - 1;
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

    public void updateM() {
    	if (currentTurn == 12) {
		    m = 100;
	    } else if (currentTurn > 7) {
		    m = 75;
	    } else if (currentTurn > 3) {
		    m = 50;
	    } else {
		    m = 25;
	    }
    }
    public boolean hasRandomEvent() {
	    int randomNum = (int)((Math.random()*100) + 1);
	    return randomNum < 28;
    }
    public void nextTurn() {
        if (hasNextTurn())
            currentTurn++;

	    updateM();
        currentPlayer = 0;
        currentStage = 0;

        Main.sortPlayers();
        Main.getDBController().saveGame();

        for (int i = 0; i < Main.getPlayerCount(); i++) {
            Main.getPlayer(i).getTimer().reset();
            Main.getPlayer(i).produce();
	        if (hasRandomEvent()) {
		        if (i != Main.getPlayerCount() - 1) {
		            int randomEventSelector = (int)((Math.random() * 7) + 1);
		            switch (randomEventSelector) {
		    	        case 1:
				            System.out.println(Main.getPlayer(i).getName() + "YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING "
					                + "3 FOOD AND 2 ENERGY UNITS");
				            Main.getPlayer(i).getBag().add(new Food(), 3);
				            Main.getPlayer(i).getBag().add(new Energy(), 2);
				            break;
			            case 2:
				            System.out.println(Main.getPlayer(i).getName() + "A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING "
					                + "TWO BARS OF ORE.");
				
				            Main.getPlayer(i).getBag().add(new Smithore(), 2);
				            break;
			            case 3:
				            System.out.println(Main.getPlayer(i).getName() + "THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $" + (8 * m) + ".");
				            Main.getPlayer(i).addMoney(8 * m);
				            break;
			            case 4:
				            System.out.println(Main.getPlayer(i).getName() + "YOU FOUND A DEAD MOOSE RAT AN SOLD THE HIDE FOR $" + (2 * m) + ".");
				            Main.getPlayer(i).addMoney(2 * m);
				            break;
			            case 5:
				            System.out.println(Main.getPlayer(i).getName() + "FLYING CAT-BUGS ATE THE ROOF OF YOUR HOUSE. REPAIRS COST $" + (4 * m) + ".");
				            Main.getPlayer(i).removeMoney(4 * m);
				            break;
			            case 6:
				            System.out.println(Main.getPlayer(i).getName() + "MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.");
				            int foodCount = Main.getPlayer(i).getBag().get(new Food()) / 2;
				            Main.getPlayer(i).getBag().remove(new Food(), foodCount);
				            break;
			            case 7:
				            System.out.println(Main.getPlayer(i).getName() + "YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU $"
						            + (6 * m) + " TO CLEAN IT UP.");
				            Main.getPlayer(i).removeMoney(6 * m);
				            break;
		            }
		        } else {
		            int randomEventSelector = (int)((Math.random() * 4) + 1);
		            switch (randomEventSelector) {
		    	        case 1:
				        System.out.println(Main.getPlayer(i).getName() + "YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING "
				                + "3 FOOD AND 2 ENERGY UNITS");
				        Resource nrg = new Energy();
				        Main.getPlayer(i).getBag().add(new Food(), 3);
				        Main.getPlayer(i).getBag().add(nrg, 2);
				        break;
			        case 2:
				        System.out.println(Main.getPlayer(i).getName() + "A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING "
				                + "TWO BARS OF ORE.");
				
				        Main.getPlayer(i).getBag().add(new Smithore(), 2);
				        break;
			        case 3:
				        System.out.println(Main.getPlayer(i).getName() + "THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $" + (8 * m) + ".");
				        Main.getPlayer(i).addMoney(8 * m);
				        break;
			        case 4:
				        System.out.println(Main.getPlayer(i).getName() + "YOU FOUND A DEAD MOOSE RAT AN SOLD THE HIDE FOR $" + (2 * m) + ".");
				        Main.getPlayer(i).addMoney(2 * m);
				        break;
		            }
            	}		
	        }
        }	
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

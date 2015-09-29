package mule.model;

import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.animation.KeyValue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Turn {
    private static int TOTAL_PLAYERS;
    private final static int TOTAL_TURNS = 12;
    private final static int TOTAL_STAGES = 2;
    private final static Integer TURN_TIME = 60;

    private int currentPlayer, currentStage, currentTurn;
    private Timeline timeline;
    private Label timerLabel;
    private IntegerProperty time = new SimpleIntegerProperty(TURN_TIME);

    public Turn(int players) {
        TOTAL_PLAYERS = players;
        currentPlayer = 0;
        currentStage = 0;
        currentTurn = 0;
	initTimer();
    }
    public void initTimer() {
	timerLabel = new Label();
	timerLabel.setText(TURN_TIME.toString());
	timerLabel.setTextFill(Color.BLACK);	
	Button button = new Button();
	button.setText("Press to start your turn");
	button.setOnAction(new EventHandler<ActionEvent>() {

	    public void handle(ActionEvent event) {
		if (timeline != null) {
			timeline.stop();
		}
		
		timerLabel.setText(time.toString());
		timeline = new Timeline();
		timeline.getKeyFrames().add(
			new KeyFrame(Duration.seconds(TURN_TIME + 1),
			new KeyValue(time, 0)));
		timeline.playFromStart();
	    }
	});

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

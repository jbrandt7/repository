package mule.model.town;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import mule.model.*;
import mule.model.player.*;

public class Pub extends Store {

    public Pub() {
        super();
    }

    public final void cashOut(Player player) {
        player.addMoney(player.getTimer().getTime());
        player.updateScore();
    }

}

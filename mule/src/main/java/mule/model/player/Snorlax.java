package mule.model.player;

import javafx.scene.paint.Color;
import mule.model.resources.Food;

public class Snorlax extends Player {

    private static final int SNORLAX_MONEY = 400;

    public Snorlax(String name, Color color) {
        super(name, color);
        removeMoney(SNORLAX_MONEY);
        super.bag.add(new Food(), 1);
    }

}

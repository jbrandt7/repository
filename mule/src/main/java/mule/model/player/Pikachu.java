package mule.model.player;

import javafx.scene.paint.Color;
import mule.model.resources.Energy;

public class Pikachu extends Player {

    private static final int PIKACHU_MONEY = 400;

    public Pikachu(String name, Color color) {
        super(name, color);
        removeMoney(PIKACHU_MONEY);
        super.bag.add(new Energy(), 1);
    }

}

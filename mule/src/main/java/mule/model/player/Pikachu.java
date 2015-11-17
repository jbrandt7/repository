package mule.model.player;

import javafx.scene.paint.Color;

public class Pikachu extends Player {

    private static final int PIKACHU_MONEY = 400;

    public Human(String name, Color color) {
        super(name, color);
        removeMoney(HUMAN_MONEY);
        super.bag.addResource(new Energy(), 1);
    }

}

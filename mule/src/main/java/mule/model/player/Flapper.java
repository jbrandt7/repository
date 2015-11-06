package mule.model.player;

import javafx.scene.paint.Color;

public class Flapper extends Player {

    private static final int FLAPPER_MONEY = 600;

    public Flapper(String name, Color color) {
        super(name, color);
        addMoney(FLAPPER_MONEY);
    }

}

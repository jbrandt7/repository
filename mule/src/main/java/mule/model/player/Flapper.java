package mule.model.player;

import javafx.scene.paint.Color;

import mule.model.map.*;
import mule.model.*;
import mule.model.resources.*;

public class Flapper extends Player {

    private static final int FLAPPER_MONEY = 600;

    public Flapper(String name, Color color) {
        super(name, color);
        addMoney(FLAPPER_MONEY);
    }

}

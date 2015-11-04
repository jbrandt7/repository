package mule.model.player;

import javafx.scene.paint.Color;

import mule.model.map.*;
import mule.model.*;
import mule.model.resources.*;

public class Human extends Player {

    private static final int HUMAN_MONEY = 400;

    public Human(String name, Color color) {
        super(name, color);
        removeMoney(HUMAN_MONEY);
    }

}

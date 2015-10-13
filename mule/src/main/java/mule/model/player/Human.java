package mule.model.player;

import java.util.ArrayList;
import javafx.scene.paint.Color;

import mule.model.map.*;
import mule.model.*;
import mule.model.resources.*;

public class Human extends Player {

    public Human(String name, Color color) {
        super(name, color);
        removeMoney(400);
    }

}

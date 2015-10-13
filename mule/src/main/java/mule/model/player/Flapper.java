package mule.model.player;

import java.util.ArrayList;
import javafx.scene.paint.Color;

import mule.model.map.*;
import mule.model.*;
import mule.model.resources.*;

public class Flapper extends Player {

    public Flapper(String name, Color color) {
        super(name, color);
        addMoney(600);
    }

}

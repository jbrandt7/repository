package mule.model.map;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/**
 * Created by harrylane on 9/18/15.
 */
public class TownPlot extends Plot {

    private static final int energyBonus = 2;

    public TownPlot(Rectangle rep) {
        super(rep);
        rep.setFill(Color.RED);
    }

    public int getBonus() {
        return energyBonus;
    }
}

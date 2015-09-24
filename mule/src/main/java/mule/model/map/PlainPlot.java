package mule.model.map;

import javafx.scene.shape.Rectangle;

/**
 * Created by harrylane on 9/18/15.
 */
public class PlainPlot extends Plot {

    private static final int energyBonus = 2;

    public PlainPlot(Rectangle rep) {
        super(rep);
    }

    public int getBonus() {
        return energyBonus;
    }
}

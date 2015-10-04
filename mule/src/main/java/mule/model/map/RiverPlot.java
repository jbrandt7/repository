package mule.model.map;

import javafx.scene.shape.Rectangle;

/**
 * Created by harrylane on 9/18/15.
 */
public class RiverPlot extends Plot {

    private static final int foodBonus = 2;

    public RiverPlot(Rectangle rep) {
        super(rep);
    }

    public int getBonus() {
        return foodBonus;
    }
}

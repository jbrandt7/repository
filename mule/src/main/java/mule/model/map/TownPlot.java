package mule.model.map;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 * Created by harrylane on 9/18/15.
 */
public class TownPlot extends Plot {

    private static final int energyBonus = 2;

    public TownPlot(Canvas rep, int x, int y) {
        super(rep, x, y);
    }

    public int getBonus() {
        return energyBonus;
    }

    public boolean produce() {
        return false;
    }
}

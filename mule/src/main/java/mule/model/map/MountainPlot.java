package mule.model.map;

import javafx.scene.shape.Rectangle;

/**
 * Created by harrylane on 9/18/15.
 */
public class MountainPlot extends Plot {

    private static final int smithOre = 2;

    public MountainPlot(Rectangle rep) {
        super(rep);
    }

    public int getBonus() {
        return smithOre;
    }
}

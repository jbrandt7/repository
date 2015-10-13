package mule.model.map;

import javafx.scene.shape.Rectangle;
import mule.model.resources.*;

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

    public boolean produce() {
        if (outfitted() && hasOwner()) {
            if (owner.getBag().get(new Energy()) > 0) {
                owner.addResource(new Food(), 4);
                owner.addResource(new Energy(), 2);
            }
        }
        return false;
    }
}

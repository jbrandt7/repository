package mule.model.map;

import javafx.scene.shape.Rectangle;
import mule.model.resources.*;

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

    public boolean produce() {
        if (outfitted() && hasOwner()) {
            if (owner.getBag().get(new Energy()) > 0) {
                owner.addResource(new Food(), 2);
                owner.addResource(new Energy(), 3);
                owner.addResource(new Smithore(), 1);
            }
        }
        return false;
    }
}

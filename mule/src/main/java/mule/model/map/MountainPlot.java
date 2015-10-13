package mule.model.map;

import javafx.scene.shape.Rectangle;
import mule.model.resources.*;

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

    public boolean produce() {
        if (outfitted() && hasOwner()) {
            if (owner.getBag().get(new Energy()) > 0) {
                owner.addResource(new Food(), 1);
                owner.addResource(new Energy(), 1);
                owner.addResource(new Smithore(), 3);
            }
        }
        return false;
    }
}

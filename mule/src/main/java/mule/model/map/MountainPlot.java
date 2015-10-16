package mule.model.map;

import javafx.scene.canvas.Canvas;
import mule.model.resources.*;

/**
 * Created by harrylane on 9/18/15.
 */
public class MountainPlot extends Plot {

    private static final int smithOre = 2;

    public MountainPlot(Canvas rep, int x, int y) {
        super(rep, x, y);
    }

    public int getBonus() {
        return smithOre;
    }

    public boolean produce() {
        if (outfitted() && hasOwner()) {
            if (owner.getBag().get(new Energy()) > 0) {
                if (mule.getType().equals(new Food())) {
                    owner.addResource(new Food(), 1);
                } else if (mule.getType().equals(new Energy())) {
                    owner.addResource(new Energy(), 1);
                } else {
                    owner.addResource(new Smithore(), 3);
                }
                return true;
            }
        }
        return false;
    }
}

package mule.model.map;

import javafx.scene.canvas.Canvas;
import mule.model.resources.*;

/**
 * Created by harrylane on 9/18/15.
 */
public class RiverPlot extends Plot {

    private static final int foodBonus = 2;

    public RiverPlot(Canvas rep, int x, int y) {
        super(rep, x, y);
    }

    public int getBonus() {
        return foodBonus;
    }

    public boolean produce() {
        if (outfitted() && hasOwner()) {
            if (owner.getBag().get(new Energy()) > 0) {
                if (mule.getType().equals(new Food())) {
                    owner.addResource(new Food(), 4);
                } else if (mule.getType().equals(new Energy())) {
                    owner.addResource(new Energy(), 2);
                }
                return true;
            }
        }
        return false;
    }
}

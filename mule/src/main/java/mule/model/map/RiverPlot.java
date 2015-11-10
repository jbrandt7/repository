package mule.model.map;

import javafx.scene.canvas.Canvas;
import mule.model.resources.*;
import javafx.scene.image.Image;

/**
 * A standard river plot
 */
public class RiverPlot extends Plot {

    private static final long serialVersionUID = 42L;

    private static final int FOODBONUS = 4;
    private static final int ENERGYBONUS = 2;

    public RiverPlot(Canvas rep, int x, int y) {
        super(rep, x, y);
    }

    @Override
    public final void drawBackground() {
        Image image = new Image("mule/view/river.jpg", false);
        super.getRep().getGraphicsContext2D().drawImage(image, super.getLocation()[0],
                super.getLocation()[1]);
    }

    @Override
    public final boolean produce() {
        if (outfitted() && hasOwner()
                && super.getOwner().getBag().get(new Energy()) > 0) {
            if (super.getMule().getType().equals(new Food())) {
                super.getOwner().addResource(new Food(), FOODBONUS);
            } else if (super.getMule().getType().equals(new Energy())) {
                super.getOwner().addResource(new Energy(), ENERGYBONUS);
            }
            super.getOwner().removeResource(new Energy(), 1);
            return true;
        }
        return false;
    }
}

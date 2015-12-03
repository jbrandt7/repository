package mule.model.map;

import javafx.scene.canvas.Canvas;
import mule.model.resources.*;
import javafx.scene.image.Image;

/**
 * A standard mountain plot
 */
public class MountainPlot extends Plot {

    private static final long serialVersionUID = 42L;

    /**
     * Sets up the plot with a standard bonus
     * @param rep The canvas to draw the plot onto
     * @param x The x coordinate to draw the plot
     * @param y The y coordinate to draw the plot
     */
    public MountainPlot(Canvas rep, int x, int y) {
        super(rep, x, y);
    }

    @Override
    public final void drawBackground() {
        Image image = new Image("mule/view/mountain.jpg", false);
        super.getRep().getGraphicsContext2D().drawImage(image, super.getLocation()[0],
                super.getLocation()[1]);
    }

    @Override
    public final boolean produce() {
        if (outfitted() && hasOwner()
                && super.getOwner().getBag().get(new Energy()) > 0) {
            if (super.getMule().getType() == null) {
                super.getOwner().addResource(new Food(), 1);
                super.getOwner().addResource(new Energy(), 1);
                super.getOwner().addResource(new Smithore(), 1);
            } else if (super.getMule().getType().equals(new Food())) {
                super.getOwner().addResource(new Food(), 1);
            } else if (super.getMule().getType().equals(new Energy())) {
                super.getOwner().addResource(new Energy(), 1);
            } else {
                super.getOwner().addResource(new Smithore(), 3);
            }
            super.getOwner().removeResource(new Energy(), 1);
            return true;
        }
        return false;
    }
}

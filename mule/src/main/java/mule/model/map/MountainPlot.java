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
        rep.getGraphicsContext2D().drawImage(image, location[0], location[1]);
    }

    @Override
    public final boolean produce() {
        if (outfitted() && hasOwner()
                && owner.getBag().get(new Energy()) > 0) {
            if (mule.getType().equals(new Food())) {
                owner.addResource(new Food(), 1);
            } else if (mule.getType().equals(new Energy())) {
                owner.addResource(new Energy(), 1);
            } else {
                owner.addResource(new Smithore(), 3);
            }
            owner.removeResource(new Energy(), 1);
            return true;
        }
        return false;
    }
}

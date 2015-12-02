package mule.model.map;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import mule.model.resources.Energy;
import mule.model.resources.Food;
import mule.model.resources.Smithore;

/**
 * Created by RyanC on 12/1/15.
 */
public class DesertPlot extends Plot {

    private static final long serialVersionUID = 42L;

    public DesertPlot(Canvas rep, int x, int y) {
        super(rep, x, y);
    }

    @Override
    public final void drawBackground() {
        Image image = new Image("mule/view/desert.jpg", false);
        super.getRep().getGraphicsContext2D().drawImage(image, super.getLocation()[0],
                super.getLocation()[1]);
    }

    @Override
    public final boolean produce() {
        if (outfitted() && hasOwner()
                && super.getOwner().getBag().get(new Energy()) > 0) {
            if (super.getMule().getType().equals(new Food())) {
                super.getOwner().addResource(new Food(), 0);
            } else if (super.getMule().getType().equals(new Energy())) {
                super.getOwner().addResource(new Energy(), 0);
            } else {
                super.getOwner().addResource(new Smithore(), 0);
            }
            super.getOwner().removeResource(new Energy(), 0);
            return true;
        }
        return false;
    }
}

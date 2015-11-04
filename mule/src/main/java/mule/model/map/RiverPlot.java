package mule.model.map;

import javafx.scene.canvas.Canvas;
import mule.model.resources.*;
import javafx.scene.image.Image;

public class RiverPlot extends Plot {

    private static final long serialVersionUID = 42L;

    private static final int FOODBONUS = 4;
    private static final int ENERGYBONUS = 2;

    public RiverPlot(Canvas rep, int x, int y) {
        super(rep, x, y);
    }

    public final int getBonus() {
        return FOODBONUS;
    }

    public final void drawBackground() {
        Image image = new Image("mule/view/river.jpg", false);
        rep.getGraphicsContext2D().drawImage(image, location[0], location[1]);
    }

    public final boolean produce() {
        if (outfitted() && hasOwner()
                && owner.getBag().get(new Energy()) > 0) {
            if (mule.getType().equals(new Food())) {
                owner.addResource(new Food(), FOODBONUS);
            } else if (mule.getType().equals(new Energy())) {
                owner.addResource(new Energy(), ENERGYBONUS);
            }
            owner.removeResource(new Energy(), 1);
            return true;
        }
        return false;
    }
}

package mule.model.map;

import javafx.scene.canvas.Canvas;
import mule.model.resources.*;
import javafx.scene.image.Image;

/**
 * Created by harrylane on 9/18/15.
 */
public class RiverPlot extends Plot {

    private static final long serialVersionUID = 42L;

    private static final int foodBonus = 2;

    public RiverPlot(Canvas rep, int x, int y) {
        super(rep, x, y);
    }

    public int getBonus() {
        return foodBonus;
    }

    public void drawBackground() {
        Image image = new Image("mule/view/river.jpg", false);
        rep.getGraphicsContext2D().drawImage(image, location[0], location[1]);
    }

    public boolean produce() {
        if (outfitted() && hasOwner()) {
            if (owner.getBag().get(new Energy()) > 0) {
                if (mule.getType().equals(new Food())) {
                    owner.addResource(new Food(), 4);
                } else if (mule.getType().equals(new Energy())) {
                    owner.addResource(new Energy(), 2);
                }
                owner.removeResource(new Energy(), 1);
                return true;
            }
        }
        return false;
    }
}

package mule.model.map;

import javafx.scene.canvas.Canvas;
import mule.model.resources.*;
import javafx.scene.image.Image;

/**
 * Created by harrylane on 9/18/15.
 */
public class PlainPlot extends Plot {

    private static final long serialVersionUID = 42L;

    private static final int energyBonus = 2;

    public PlainPlot(Canvas rep, int x, int y) {
        super(rep, x, y);
    }

    public int getBonus() {
        return energyBonus;
    }

    public void drawBackground() {
        Image image = new Image("mule/view/plain.jpg", false);
        rep.getGraphicsContext2D().drawImage(image, location[0], location[1]);
    }

    public boolean produce() {
        if (outfitted() && hasOwner()) {
            if (owner.getBag().get(new Energy()) > 0) {
                if (mule.getType().equals(new Food())) {
                    owner.addResource(new Food(), 2);
                } else if (mule.getType().equals(new Energy())) {
                    owner.addResource(new Energy(), 3);
                } else {
                    owner.addResource(new Smithore(), 1);
                }
                owner.removeResource(new Energy(), 1);
                return true;
            }
        }
        return false;
    }
}

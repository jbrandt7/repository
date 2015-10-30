package mule.model.map;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

/**
 * Created by harrylane on 9/18/15.
 */
public class TownPlot extends Plot {

    private static final long serialVersionUID = 42L;

    private static final int energyBonus = 2;

    public TownPlot(Canvas rep, int x, int y) {
        super(rep, x, y);
    }

    public int getBonus() {
        return energyBonus;
    }

    public void drawBackground() {
        Image image = new Image("mule/view/town.jpg", false);
        rep.getGraphicsContext2D().drawImage(image, location[0], location[1]);
    }

    public boolean produce() {
        return false;
    }
}

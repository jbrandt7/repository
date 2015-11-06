package mule.model.map;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class TownPlot extends Plot {

    private static final long serialVersionUID = 42L;

    private static final int ENERGYBONUS = 2;

    public TownPlot(Canvas rep, int x, int y) {
        super(rep, x, y);
    }

    public final int getBonus() {
        return ENERGYBONUS;
    }

    public final void drawBackground() {
        Image image = new Image("mule/view/town.jpg", false);
        rep.getGraphicsContext2D().drawImage(image, location[0], location[1]);
    }

    public final boolean produce() {
        return false;
    }
}

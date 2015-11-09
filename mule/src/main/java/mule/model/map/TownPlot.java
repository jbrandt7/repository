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
        super.getRep().getGraphicsContext2D().drawImage(image, super.getLocation()[0],
                super.getLocation()[1]);
    }

    public final boolean produce() {
        return false;
    }
}

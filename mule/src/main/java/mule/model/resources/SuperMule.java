package mule.model.resources;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Created by RyanC on 12/1/15.
 */
public class SuperMule extends Mule {
    private static final int COST = 200;

    private Resource type;

    /**
     * Constructs a general purpose mule.
     * Will need a type specified later
     */
    public SuperMule() {
        super();
    }

    @Override
    public void draw(Canvas rep, int x, int y) {
        String name = getClass().getName().substring(21, 26).toLowerCase();
        Image mule = new Image("mule/view/mule_" + name + ".png");
        rep.getGraphicsContext2D().drawImage(mule,  x, y);
    }
}

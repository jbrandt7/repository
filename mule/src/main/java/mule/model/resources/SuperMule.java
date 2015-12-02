package mule.model.resources;

import javafx.scene.canvas.Canvas;
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

    /**
     * Constructs a mule of a particular type
     * @param t The type of mule to construct
     */
    public SuperMule(Resource t) {
        super();
        this.type = t;
    }
}

package mule.model;

import javafx.scene.shape.Rectangle;

/**
 * Created by harrylane on 9/16/15.
 */
public class Plot {
    private Player owner;
    private Mule mule;
    private Rectangle rep;
    private static int VALUE = 100;

    public Plot(Rectangle rep) {
        this.rep = rep;
    }

    public void assignOwner(Player p) {
        owner = p;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    public void outfit(Mule m) {
        mule = m;
    }

    public int bonus() {
        return 0;
    }

    public Rectangle getRep() {
        return rep;
    }

    public int getValue() {
        return VALUE;
    }

}

package mule.model;

import javafx.scene.shape.Rectangle;

/**
 * Created by harrylane on 9/16/15.
 */
public abstract class Plot {
    Player owner;
    Mule mule;
    Rectangle rep;

    public Plot() {
        rep = new Rectangle(1,1);
    }

    public void assignOwner(Player player) {
        owner = player;
    }

    public void outfit(Mule mule) {
        mule = mule;
    }

    public int bonus() {
        return 0;
    }
}

package mule.model;

import javafx.scene.shape.Rectangle;

/**
 * Created by harrylane on 9/16/15.
 */
<<<<<<< HEAD
public class Plot {
    private Player owner;
    private static int VALUE = 100;

    public int getValue() {
        return VALUE;
    }

=======
public abstract class Plot {
    Player owner;
    Mule mule;
    Rectangle rep;

    public Plot(Rectangle rep) {
        this.rep = rep;
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

    public Rectangle getRep() {
        return rep;
    }
>>>>>>> 0ae40ed172f4077d16299c681ad06e0beb25a4ca
}

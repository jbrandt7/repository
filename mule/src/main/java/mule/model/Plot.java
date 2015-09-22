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
<<<<<<< HEAD
>>>>>>> 0ae40ed172f4077d16299c681ad06e0beb25a4ca
=======

    public int getValue() {
        return 1;
    }
>>>>>>> 8cc12c386949dff423e3843e2e8b546bf26bb492
}

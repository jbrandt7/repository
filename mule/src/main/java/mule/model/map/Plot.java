package mule.model.map;

import javafx.scene.shape.Rectangle;

import mule.model.*;

/**
 * Created by harrylane on 9/16/15.
 */
public class Plot implements Tradeable {
    private Player owner;
    private Mule mule;
    private Rectangle rep;
    private static int VALUE = 1;

    public Plot(Rectangle rep) {
        this.rep = rep;
    }

    public boolean buy(Player p) {
        if (p.getMoney() > getCost()) {
            p.addPlot(this);
            return true;
        }
        return false;
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

    public int getCost() {
        return VALUE;
    }

}

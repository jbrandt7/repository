package mule.model.map;

import javafx.scene.shape.Rectangle;

import mule.model.*;
import mule.model.resources.*;
import mule.model.player.*;

/**
 * Created by harrylane on 9/16/15.
 */
public abstract class Plot implements Tradeable {
    protected Player owner;
    private Mule mule;
    private Rectangle rep;
    private static int VALUE = 1;

    public Plot(Rectangle rep) {
        this.rep = rep;
    }

    public boolean buy(Player p) {
        if (p.getMoney() > getCost()) {
            p.addPlot(this);
            p.removeMoney(VALUE);
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

    public Player getOwner() {
        return owner;
    }

    public void outfit(Mule m) {
        mule = m;
        mule.draw(rep);
    }

    public boolean outfitted() {
        return mule != null;
    }

    public boolean notOutfitted() {
        return !outfitted();
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

    public abstract boolean produce();

}

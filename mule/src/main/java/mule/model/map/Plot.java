package mule.model.map;

import javafx.scene.canvas.Canvas;

import mule.model.*;
import mule.model.resources.*;
import mule.model.player.*;

/**
 * Created by harrylane on 9/16/15.
 */
public abstract class Plot implements Tradeable {
    protected Player owner;
    protected Mule mule;
    private Canvas rep;
    private int[] location;
    private static int VALUE = 300;

    public Plot(Canvas rep, int x, int y) {
        this.rep = rep;
        location = new int[] {x, y};
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

    public void draw(Player p) {
        rep.getGraphicsContext2D().setFill(p.getColor());
        rep.getGraphicsContext2D().fillRect(location[0], location[1], 75, 75);
    }

    public boolean hasOwner() {
        return owner != null;
    }

    public Player getOwner() {
        return owner;
    }

    public void outfit(Mule m) {
        mule = m;
        mule.draw(rep, location[0], location[1]);
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

    public Canvas getRep() {
        return rep;
    }

    public int getCost() {
        return VALUE;
    }

    public abstract boolean produce();

}

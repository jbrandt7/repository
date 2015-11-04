package mule.model.map;

import javafx.scene.canvas.Canvas;

import mule.model.resources.*;
import mule.model.player.*;

public abstract class Plot implements Tradeable, java.io.Serializable {

    private static final long serialVersionUID = 42L;

    protected Player owner;
    protected Mule mule;
    protected int[] location;
    protected transient Canvas rep;
    private static int value = 300;

    public Plot(Canvas canvas, int x, int y) {
        this.rep = canvas;
        location = new int[] {x, y};
    }

    public final boolean buy(Player p) {
        if (p.getMoney() > getCost()) {
            p.addPlot(this);
            p.removeMoney(value);
            return true;
        }
        return false;
    }

    public final void assignOwner(Player p) {
        owner = p;
    }

    public final void draw(Player p) {
        rep.getGraphicsContext2D().setFill(p.getColor());
        rep.getGraphicsContext2D().fillRect(location[0], location[1], 75, 75);
    }

    public final void redraw() {
        drawBackground();
        if (hasOwner()) {
            rep.getGraphicsContext2D().setGlobalAlpha(.5);
            draw(owner);
            rep.getGraphicsContext2D().setGlobalAlpha(1.0);
            if (outfitted()) {
                mule.draw(rep, location[0], location[1]);
            }
        }
    }

    public final boolean hasOwner() {
        return owner != null;
    }

    public final Player getOwner() {
        return owner;
    }

    public final void outfit(Mule m) {
        mule = m;
        mule.draw(rep, location[0], location[1]);
    }

    public final boolean outfitted() {
        return mule != null;
    }

    public final boolean notOutfitted() {
        return !outfitted();
    }

    public final int bonus() {
        return 0;
    }

    public final Canvas getRep() {
        return rep;
    }

    public final void setRep(Canvas canvas) {
        this.rep = canvas;
    }

    public int getCost() {
        return value;
    }

    public abstract boolean produce();

    public abstract void drawBackground();

}

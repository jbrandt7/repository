package mule.model.map;

import javafx.scene.canvas.Canvas;

import mule.model.resources.*;
import mule.model.player.*;

/**
 * Abstract class representing a plot of land to be bought,
 * outfitted with mules, and to produce resources
 */
public abstract class Plot implements java.io.Serializable {

    private static final long serialVersionUID = 42L;

    private Player owner;
    private Mule mule;
    private final int[] location;
    private transient Canvas rep;
    private static final int VALUE = 300;

    /**
     * Initializes plot
     * @param canvas The canvas the plot will draw its representation to
     * @param x The x coordinate of the plot on the canvas
     * @param y The y coordinate of the plot on the canvas
     */
    Plot(Canvas canvas, int x, int y) {
        this.rep = canvas;
        location = new int[] {x, y};
    }

    /**
     * Attempts to have a player buy a resource
     * @param p The player attempting to buy
     * @return true if buying was successful, false if it was unsuccessful
     */
    public final boolean buy(Player p) {
        if (p.getMoney() > getCost()) {
            p.addPlot(this);
            p.removeMoney(VALUE);
            return true;
        }
        return false;
    }

    /**
     * Sets the owner of this land to a player
     * @param p The player that now owns this land
     */
    public final void assignOwner(Player p) {
        owner = p;
    }

    /**
     * Draws a players color onto the land
     * @param p The player who owns the land
     */
    public final void draw(Player p) {
        rep.getGraphicsContext2D().setFill(p.getColor());
        rep.getGraphicsContext2D().fillRect(location[0], location[1], 75, 75);
    }

    /**
     * Redraws the plot when a saved game is loaded
     */
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

    /**
     * @return whether the plot is owned by anyone
     */
    public final boolean hasOwner() {
        return owner != null;
    }

    /**
     * @return the owner of this plot, can be null
     */
    public final Player getOwner() {
        return owner;
    }

    /**
     * Outfit a plot of land with a mule
     * @param m the mule to be used
     */
    public final void outfit(Mule m) {
        mule = m;
        mule.draw(rep, location[0], location[1]);
    }

    /**
     * @return returns whether the plot has a mule on it
     */
    public final boolean outfitted() {
        return mule != null;
    }

    /**
     * Sets the parent canvas the plots representation will be drawn onto
     * @param canvas the canvas to be used
     */
    public final void setRep(Canvas canvas) {
        this.rep = canvas;
    }

    /**
     * @return the cost of this plot
     */
    public final int getCost() {
        return VALUE;
    }

    /**
     * Attempts to produce resources
     * @return whether or not anything was produced
     */
    public abstract boolean produce();

    /**
     * Draws the graphical representation onto the parent canvas
     */
    protected abstract void drawBackground();

    public final Mule getMule() {
        return mule;
    }

    public final int[] getLocation() {
        return location;
    }

    public final Canvas getRep() {
        return rep;
    }

}

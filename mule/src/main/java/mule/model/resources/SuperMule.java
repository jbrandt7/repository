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
    public Mule() {
        super();
    }

    /**
     * Constructs a mule of a particular type
     * @param t The type of mule to construct
     */
    public Mule(Resource t) {
        super();
        this.type = t;
    }

    /**
     * Draws a mules representation on a canvas
     * @param rep The canvas to be drawn upon
     * @param x The x coordinate of the plot we are drawing on
     * @param y The y coordinate of the plot we are drawing on
     */
    public final void draw(Canvas rep, int x, int y) {
        rep.getGraphicsContext2D().setFill(Color.BLACK);
        rep.getGraphicsContext2D().fillRect(x, y, 15, 15);
    }

    @Override
    public final int getCost() {
        if (type != null) {
            return COST + type.getCost();
        }
        return COST;
    }

    /**
     * @return the type of mule
     */
    public final Resource getType() {
        return type;
    }

    public final boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (o == this) {
            return true;
        } else if (o instanceof Mule) {
            Mule that = (Mule) o;
            if (that.type == null || this.type == that.type) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        if (type == null) {
            return 31 * COST + 41;
        }
        return (31 * COST + 41) + type.hashCode();
    }

}

}

package mule.model.resources;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Mule extends Resource{

	private static final int COST = 100;

    private Resource type;

	public Mule() {
		super(COST);
	}

    public Mule(Resource t) {
		super(COST);
        this.type = t;
    }

    public final void draw(Canvas rep, int x, int y) {
        rep.getGraphicsContext2D().setFill(Color.BLACK);
		rep.getGraphicsContext2D().fillRect(x, y, 15, 15);
    }

	public final int getCost() {
		if (type != null) {
			return COST + type.getCost();
		}
		return COST;
	}

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

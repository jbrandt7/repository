package mule.model.resources;

import javafx.scene.shape.Rectangle;

/**
 * Created by The Boat on 9/22/2015.
 */
public class Mule extends Resource{

	private static final int COST = 100;

    private Resource type;
    private Rectangle rep;

	public Mule() {
		super(COST);
	}

    public Mule(Resource type) {
		super(COST);
        this.type = type;
    }

    public void draw(Rectangle rep) {
        int x = (int) rep.getX();
        int y = (int) rep.getY();

        this.rep = new Rectangle(x + 10, y + 10, 10, 10);
    }

    public Rectangle getRep() {
        return rep;
    }

	public int getCost() {
		if (type != null) {
			return COST + type.getCost();
		}
		return COST;
	}

    public Resource getType() {
        return type;
    }

	public void setType(Resource type) {
		this.type = type;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (o == this) {
			return true;
		} else if (o instanceof Mule) {
			Mule that = (Mule) o;
			if (that.type == null) {
				return true;
			} else {
				return this.type == that.type;
			}
		}
		return false;
	}

	public int hashCode() {
		if (type == null) {
			return 31 * COST + 41;
		}
		return (31 * COST + 41) + type.hashCode();
	}

}

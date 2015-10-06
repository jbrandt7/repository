package mule.model;

import javafx.scene.shape.Rectangle;

/**
 * Created by The Boat on 9/22/2015.
 */
public class Mule {
	private static final int COST = 100;
	public int getCost() {
		return COST;
	}

    private Resource type;
    private Rectangle rep;

    public Mule(Resource type) {
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
}

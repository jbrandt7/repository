package mule.model;

import javafx.scene.shape.Rectangle;

/**
<<<<<<< HEAD
 * Created by The Boat on 9/22/2015.
=======
 * Created by harrylane on 9/18/15.
>>>>>>> 0ae40ed172f4077d16299c681ad06e0beb25a4ca
 */
public class Mule<T extends Resource> {

    private Rectangle rep;

    public void draw(Rectangle rep) {
        int x = (int) rep.getX();
        int y = (int) rep.getY();

        this.rep = new Rectangle(x + 10, y + 10, 10, 10);
    }

    public Rectangle getRep() {
        return rep;
    }

}

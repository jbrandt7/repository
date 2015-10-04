package mule.model.town;

import javafx.scene.shape.Rectangle;

/**
 * Created by harrylane on 9/16/15.
 */
public abstract class Store {

    protected Rectangle rep;

    public Store(Rectangle rep) {
        this.rep = rep;
    }

    public Rectangle getRep() {
        return rep;
    }

}

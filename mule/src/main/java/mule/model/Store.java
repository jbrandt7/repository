package mule.model;

import javafx.scene.shape.Rectangle;
/**
 * Created by harrylane on 9/16/15. edited by Ryan Chiang 9/22/15
 */
public class Store {
    private String name;
    Rectangle rep;

    public Store(Rectangle rep) {
        this.rep = rep;
    }

    public Rectangle getRep() {
        return rep;
    }

}

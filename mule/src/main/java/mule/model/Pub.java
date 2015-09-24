package mule.model;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Pub extends Store {

    public Pub(Rectangle rep) {
        super(rep);
        rep.setFill(Color.RED);
    }

}
